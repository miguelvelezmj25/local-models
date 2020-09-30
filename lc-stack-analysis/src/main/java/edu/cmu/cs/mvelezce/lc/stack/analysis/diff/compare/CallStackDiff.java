package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class CallStackDiff {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.000");

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/diff/java/programs";
  public static final double TIME_DIFF_THRESHOLD = 0.1;

  private static final String DELETION = "<span style=\"background-color: #00dcff\">${text}</span>";
  private static final String INSERTION =
      "<span style=\"background-color: #45EA85\">${text}</span>";
  private static final String DELTA_TIME_INCREASE =
      "<span style=\"background-color: #2de7ff\">${text}</span>";
  private static final String DELTA_TIME_DECREASE =
      "<span style=\"background-color: #ff9800\">${text}</span>";
  private static final String REGION = "<span style=\"background-color: #00ff89\">${text}</span>";

  private static final String OLD_TAG = "~";
  private static final String NEW_TAG = "!";

  private final String programName;
  private final String optionValue1;
  private final String optionValue2;
  private final Set<JavaRegion> regions;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallStackDiff(
      String programName,
      String optionValue1,
      String optionValue2,
      Set<JavaRegion> regions,
      String className,
      String methodName,
      String methodSignature) {
    this.programName = programName;
    this.optionValue1 = optionValue1;
    this.optionValue2 = optionValue2;
    this.regions = regions;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public CallStackDiff(
      String programName,
      int diffId,
      String optionValue1,
      Set<String> s1,
      String optionValue2,
      Set<String> s2,
      String className,
      String methodName,
      String methodSignature) {
    throw new UnsupportedOperationException("delete this constructor");
  }

  public CallStackDiff(
      String programName,
      String defrag_always,
      String aFalse,
      String s,
      String compact,
      String s1) {
    throw new UnsupportedOperationException("delete this constructor");
  }

  public void diff() throws IOException, DiffException {
    this.clearRootDir();

    System.err.println(
        "Figure out how to compare across option values more efficiently than everything with everything");
    Collection<File> prettyCallStacks1 = this.getPrettyCallStacks(this.optionValue1);
    Collection<File> prettyCallStacks2 = this.getPrettyCallStacks(this.optionValue2);
    Map<File, File> treesToDiff = this.getTreesToDiff(prettyCallStacks1, prettyCallStacks2);

    int i = 0;
    for (Map.Entry<File, File> entry : treesToDiff.entrySet()) {
      List<String> lines1 = FileUtils.readLines(entry.getKey(), (String) null);
      List<String> lines2 = FileUtils.readLines(entry.getValue(), (String) null);
      Map<String, Pair<String, String>> allMethodsToTimes = getAllMethodsToTimes(lines1, lines2);
      List<DiffRow> diff = diffCallStacks(lines1, lines2);
      this.generateHTML(
          i,
          this.optionValue1,
          entry.getKey().getName(),
          this.optionValue2,
          entry.getValue().getName(),
          diff,
          allMethodsToTimes);
      i++;
    }
  }

  private Map<File, File> getTreesToDiff(
      Collection<File> prettyCallStacks1, Collection<File> prettyCallStacks2)
      throws IOException, DiffException {
    Map<File, File> treesToDiff = new HashMap<>();
    for (File prettyCallStack1 : prettyCallStacks1) {
      if (treesToDiff.containsKey(prettyCallStack1)) {
        continue;
      }

      List<String> lines1 = FileUtils.readLines(prettyCallStack1, (String) null);
      for (File prettyCallStack2 : prettyCallStacks2) {
        if (treesToDiff.containsValue(prettyCallStack2)) {
          continue;
        }

        List<String> lines2 = FileUtils.readLines(prettyCallStack2, (String) null);
        if (!sameCallStackRoot(lines1, lines2)) {
          continue;
        }

        List<DiffRow> diffs = diffCallStacks(lines1, lines2);
        boolean equalTraces = true;
        for (DiffRow diff : diffs) {
          if (!diff.getTag().equals(DiffRow.Tag.EQUAL)) {
            equalTraces = false;
            break;
          }
        }

        if (equalTraces) {
          treesToDiff.put(prettyCallStack1, prettyCallStack2);
        }
      }
    }

    return treesToDiff;
  }

  private void clearRootDir() throws IOException {
    File outputFile =
        new File(
            OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + this.optionValue1
                + "-"
                + this.optionValue2);
    if (outputFile.exists()) {
      FileUtils.cleanDirectory(outputFile);
    }
  }

  private boolean sameCallStackRoot(List<String> lines1, List<String> lines2) {
    String root1 = lines1.get(lines1.size() - 1).split(",")[0];
    String root2 = lines2.get(lines2.size() - 1).split(",")[0];
    return root1.equals(root2);
  }

  private Map<String, Pair<String, String>> getAllMethodsToTimes(
      List<String> lines1, List<String> lines2) {
    Map<String, Pair<String, String>> allMethods = new HashMap<>();
    populateAllMethods(allMethods, lines1);
    populateAllMethods(allMethods, lines2);

    addPairs(allMethods, lines1, true);
    addPairs(allMethods, lines2, false);

    return allMethods;
  }

  private void populateAllMethods(
      Map<String, Pair<String, String>> allMethods, List<String> entries) {
    for (String entry : entries) {
      allMethods.putIfAbsent(entry.split(",")[0], Pair.of("", ""));
    }
  }

  private void addPairs(
      Map<String, Pair<String, String>> allMethods, List<String> entries, boolean leftEntry) {
    for (String line : entries) {
      String[] elements = line.split(",");
      Pair<String, String> entry = allMethods.get(elements[0]);
      Pair<String, String> pair;
      if (leftEntry) {
        pair = Pair.of(elements[1], entry.getRight());
      } else {
        pair = Pair.of(entry.getLeft(), elements[1]);
      }
      allMethods.put(elements[0], pair);
    }
  }

  private Collection<File> getPrettyCallStacks(String optionValue) {
    File dir =
        new File(
            CallStackSelector.OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + optionValue);
    return FileUtils.listFiles(dir, new String[] {"csv"}, false);
  }

  private static List<DiffRow> diffCallStacks(List<String> lines1, List<String> lines2)
      throws DiffException {
    DiffRowGenerator generator =
        DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            .oldTag(f -> OLD_TAG)
            .newTag(f -> NEW_TAG)
            .build();

    List<String> methods1 = new ArrayList<>();
    for (String line : lines1) {
      methods1.add(line.split(",")[0]);
    }
    List<String> methods2 = new ArrayList<>();
    for (String line : lines2) {
      methods2.add(line.split(",")[0]);
    }

    return generator.generateDiffRows(methods1, methods2);
  }

  private void generateHTML(
      int comparison,
      String optionValue1,
      String fileName1,
      String optionValue2,
      String fileName2,
      List<DiffRow> rows,
      Map<String, Pair<String, String>> allMethodsToTimes)
      throws IOException {
    String template =
        FileUtils.readFileToString(
            new File(
                "./src/main/java/edu/cmu/cs/mvelezce/lc/stack/analysis/diff/compare/difftemplate.html"),
            "utf-8");
    String output =
        template.replace(
            "${data}",
            getTable(rows, optionValue1, fileName1, optionValue2, fileName2, allMethodsToTimes));
    File outputFile =
        new File(
            OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + this.optionValue1
                + "-"
                + this.optionValue2
                + "/"
                + comparison);
    if (!outputFile.exists() && !outputFile.mkdirs()) {
      throw new RuntimeException("Could not create directories");
    }
    outputFile = new File(outputFile, UUID.randomUUID() + ".html");
    try (PrintWriter out = new PrintWriter(outputFile)) {
      out.println(output);
    }
  }

  private String getTable(
      List<DiffRow> rows,
      String optionValue1,
      String fileName1,
      String optionValue2,
      String fileName2,
      Map<String, Pair<String, String>> allMethodsToTimes) {
    StringBuilder table = new StringBuilder();
    table.append("<p style=\"font-weight:bold\">");
    table.append(fileName1);
    table.append(" vs ");
    table.append(fileName2);
    table.append("</p>");
    table.append("\n");
    table.append("<p style=\"font-weight:bold\">");
    table.append(optionValue1);
    table.append(" vs ");
    table.append(optionValue2);
    table.append("</p>");
    table.append("\n");
    table.append("<table style=\"width: 100%\">");
    table.append("\n");
    for (int i = 0; i < rows.size(); i++) {
      DiffRow row = rows.get(i);
      table.append("<tr class=\"border_bottom\">");
      table.append("\n");
      String line = row.getOldLine();
      //      if (!line.isEmpty()) {
      //        String[] entries = line.split(",");
      //        entries[0] = entries[0].replaceAll("&lt;", "<");
      //        entries[0] = entries[0].replaceAll("&gt;", ">");
      String method = addBackground(compressMethod(line));
      table.append("<td>");
      method = this.addRegionBackground(i, line, method);
      table.append(method);
      table.append("</td>");
      table.append("\n");
      table.append("<td align =\"right\">");
      Pair<String, String> times = allMethodsToTimes.get(removeTags(line));
      DiffRow timesDiff = compareTimes(times);
      String time = addTimeBackground(timesDiff);
      table.append(time);
      table.append("</td>");
      table.append("\n");
      //      } else {
      //        table.append("<td>&nbsp</td>");
      //        table.append("<td>&nbsp</td>");
      //      }
      table.append("</tr>");
      table.append("\n");
    }
    table.append("</table>");
    table.append("\n");
    return table.toString();
  }

  private boolean isRegion(String method) {
    for (JavaRegion region : this.regions) {
      if (!method.startsWith(region.getRegionPackage())) {
        continue;
      }

      String className = method.replace(region.getRegionPackage() + ".", "");
      if (!className.startsWith(region.getRegionClass())) {
        continue;
      }

      String methodSignature = className.replace(region.getRegionClass() + ".", "");
      if (methodSignature.equals(region.getRegionMethodSignature())) {
        return true;
      }
    }

    return false;
  }

  private static DiffRow compareTimes(Pair<String, String> times) {
    String timeLeft = times.getLeft();
    double time1 = timeLeft.isEmpty() ? 0.0 : Double.parseDouble(timeLeft);
    String timeRight = times.getRight();
    double time2 = timeRight.isEmpty() ? 0.0 : Double.parseDouble(timeRight);

    DiffRow.Tag tag = DiffRow.Tag.EQUAL;
    if (Math.abs(time1 - time2) >= TIME_DIFF_THRESHOLD) {
      tag = DiffRow.Tag.CHANGE;
    }

    return new DiffRow(tag, timeLeft, timeRight);
  }

  private static String compressMethod(String fullyQualifiedMethod) {
    String[] entries = fullyQualifiedMethod.split("\\.");
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < (entries.length - 2); i++) {
      if (entries[i].startsWith(OLD_TAG) || entries[i].startsWith(NEW_TAG)) {
        result.append(entries[i], 0, 2).append(".");
      } else {
        result.append(entries[i].charAt(0)).append(".");
      }
    }

    String className = entries[entries.length - 2];
    className = className.substring(0, Math.min(className.length(), 30));
    result.append(className).append(".");

    String methodName = entries[entries.length - 1];
    int methodSignatureEncoded = methodName.hashCode();
    methodName = methodName.substring(0, Math.min(methodName.indexOf("("), 30));
    methodName = methodName.replaceAll("<", "[");
    methodName = methodName.replaceAll(">", "]");
    result.append(methodName).append("(").append(methodSignatureEncoded).append(")");

    return result.toString();
  }

  private static String removeTags(String entry) {
    if (entry.contains(OLD_TAG)) {
      return entry.replaceAll(OLD_TAG, "");
    }

    if (entry.contains(NEW_TAG)) {
      return entry.replaceAll(NEW_TAG, "");
    }

    return entry;
  }

  private String addRegionBackground(int iter, String line, String method) {
    for (int i = 0; i < iter; i++) {
      method = " | " + method;
    }

    if (this.isRegion(line)) {
      return REGION.replace("${text}", method);
    }

    return method;
  }

  private static String addBackground(String entry) {
    if (entry.contains(OLD_TAG)) {
      entry = removeTags(entry);
      return DELETION.replace("${text}", "" + entry);
    }

    if (entry.contains(NEW_TAG)) {
      entry = removeTags(entry);
      return INSERTION.replace("${text}", "" + entry);
    }

    return entry;
  }

  private static String addTimeBackground(DiffRow diffRow) {
    if (!diffRow.getTag().equals(DiffRow.Tag.CHANGE)) {
      return diffRow.getOldLine();
    }

    String baseTime = diffRow.getOldLine();
    String newTime = diffRow.getNewLine();

    String symbol = "+";
    String stringToReplace = DELTA_TIME_INCREASE;
    if (Double.parseDouble(baseTime) > Double.parseDouble(newTime)) {
      symbol = "-";
      stringToReplace = DELTA_TIME_DECREASE;
    }

    String delta =
        DECIMAL_FORMAT.format(Math.abs(Double.parseDouble(baseTime) - Double.parseDouble(newTime)));
    return stringToReplace.replace("${text}", diffRow.getOldLine() + " Δ" + symbol + delta);
  }
}
