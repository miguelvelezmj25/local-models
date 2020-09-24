package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CallStackDiff {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/diff/java/programs";
  public static final double TIME_DIFF_THRESHOLD = 0.1;

  private static final String DELETION = "<span style=\"background-color: #00dcff\">${text}</span>";
  private static final String INSERTION =
      "<span style=\"background-color: #45EA85\">${text}</span>";
  private static final String TIME_DIFF =
      "<span style=\"background-color: #ffc061\">${text}</span>";
  private static final String OLD_TAG = "~";
  private static final String NEW_TAG = "!";

  private final String programName;
  private final int diffId;
  private final String optionValue1;
  private final Set<String> stacks1;
  private final String optionValue2;
  private final Set<String> stacks2;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallStackDiff(
      String programName,
      int diffId,
      String optionValue1,
      Set<String> stacks1,
      String optionValue2,
      Set<String> stacks2,
      String className,
      String methodName,
      String methodSignature) {
    this.programName = programName;
    this.diffId = diffId;
    this.optionValue1 = optionValue1;
    this.stacks1 = stacks1;
    this.optionValue2 = optionValue2;
    this.stacks2 = stacks2;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public CallStackDiff(
      String programName, String a, String aFalse, String s, String moo, String s1) {
    throw new UnsupportedOperationException("implement");
  }

  public void diff() throws IOException, DiffException {
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
                + this.diffId);
    if (outputFile.exists()) {
      FileUtils.cleanDirectory(outputFile);
    }

    System.err.println(
        "Figure out how to compare across option values more efficiently than everything with everything");
    Collection<File> prettyCallStacks1 = this.getPrettyCallStacks(this.optionValue1);
    Collection<File> prettyCallStacks2 = this.getPrettyCallStacks(this.optionValue2);

    for (File prettyCallStack1 : prettyCallStacks1) {
      if (!this.stacks1.contains(prettyCallStack1.getName())) {
        continue;
      }
      List<String> lines1 = FileUtils.readLines(prettyCallStack1, (String) null);
      for (File prettyCallStack2 : prettyCallStacks2) {
        if (!this.stacks2.contains(prettyCallStack2.getName())) {
          continue;
        }
        List<String> lines2 = FileUtils.readLines(prettyCallStack2, (String) null);
        if (!sameCallStackRoot(lines1, lines2)) {
          continue;
        }
        Map<String, Pair<String, String>> allMethodsToTimes = getAllMethodsToTimes(lines1, lines2);
        List<DiffRow> diff = diffCallStacks(lines1, lines2);
        this.generateHTML(
            this.optionValue1,
            prettyCallStack1.getName(),
            this.optionValue2,
            prettyCallStack2.getName(),
            diff,
            allMethodsToTimes);
      }
    }
  }

  private boolean sameCallStackRoot(List<String> lines1, List<String> lines2) {
    String root1 = lines1.get(lines1.size() - 1).split(",")[0];
    String root2 = lines2.get(lines2.size() - 1).split(",")[0];
    return root1.equals(root2);
  }

  private double getMaxSelfTime(Collection<Pair<String, String>> times) {
    double max = 0.0;
    for (Pair<String, String> entry : times) {
      if (!entry.getLeft().isEmpty()) {
        double time = Double.parseDouble(entry.getLeft());
        max = Math.max(max, time);
      }
      if (!entry.getRight().isEmpty()) {
        double time = Double.parseDouble(entry.getRight());
        max = Math.max(max, time);
      }
    }
    return max;
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
      String optionValue1,
      String fileName1,
      String optionValue2,
      String fileName2,
      List<DiffRow> rows,
      Map<String, Pair<String, String>> allMethodsToTimes)
      throws IOException, DiffException {
    // Get template & replace placeholders with left & right variables with actual
    // comparison
    String template =
        FileUtils.readFileToString(
            new File(
                "./src/main/java/edu/cmu/cs/mvelezce/lc/stack/analysis/diff/compare/difftemplate.html"),
            "utf-8");
    String output =
        template.replace(
            "${left}", getTable(rows, optionValue1, fileName1, false, allMethodsToTimes));
    output =
        output.replace(
            "${right}", getTable(rows, optionValue2, fileName2, true, allMethodsToTimes));

    // Write file to disk.
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
                + this.diffId);
    if (!outputFile.exists() && !outputFile.mkdirs()) {
      throw new RuntimeException("Could not create directories");
    }
    outputFile = new File(outputFile, UUID.randomUUID() + ".html");
    try (PrintWriter out = new PrintWriter(outputFile)) {
      out.println(output);
    }
  }

  private static String getTable(
      List<DiffRow> rows,
      String optionValue,
      String fileName,
      boolean right,
      Map<String, Pair<String, String>> allMethodsToTimes)
      throws DiffException {
    StringBuilder table = new StringBuilder();
    table.append("<span style=\"font-weight:bold\">");
    table.append(fileName);
    table.append("</span>");
    table.append("\n");
    table.append("<table>");
    table.append("<tr>");
    table.append("<td>");
    table.append("<span style=\"font-weight:bold\">");
    table.append(optionValue);
    table.append("</span>");
    table.append("</td>");
    table.append("</tr>");
    table.append("\n");
    for (DiffRow row : rows) {
      table.append("<tr class=\"border_bottom\">");
      table.append("\n");
      String line = right ? row.getNewLine() : row.getOldLine();
      if (!line.isEmpty()) {
        String[] entries = line.split(",");
        entries[0] = entries[0].replaceAll("&lt;", "<");
        entries[0] = entries[0].replaceAll("&gt;", ">");
        String method = addBackground(compressMethod(entries[0]));
        Pair<String, String> times = allMethodsToTimes.get(removeTags(entries[0]));
        DiffRow timesDiff = compareTimes(times);
        String time =
            right
                ? addTimeBackground(timesDiff.getNewLine(), timesDiff.getTag())
                : addTimeBackground(timesDiff.getOldLine(), timesDiff.getTag());
        table
            .append("<td>")
            .append(method)
            .append("</td><td align =\"right\">")
            .append(time)
            .append("</td>")
            .append("\n");
      } else {
        table.append("<td>&nbsp</td>");
        table.append("<td>&nbsp</td>");
      }
      table.append("</tr>");
      table.append("\n");
    }
    table.append("</table>");
    table.append("\n");
    return table.toString();
  }

  private static DiffRow compareTimes(Pair<String, String> times) throws DiffException {
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

    if (entries[0].startsWith(OLD_TAG) || entries[0].startsWith(NEW_TAG)) {
      result.append(entries[0], 0, 2).append(".");
    } else {
      result.append(entries[0].charAt(0)).append(".");
    }

    for (int i = 1; i < (entries.length - 2); i++) {
      result.append(entries[i].charAt(0)).append(".");
    }

    String className = entries[entries.length - 2];
    className = className.substring(0, Math.min(className.length(), 30));
    result.append(className).append(".");

    String methodName = entries[entries.length - 1];
    int methodSignatureEncoded = methodName.hashCode();
    methodName = methodName.substring(0, Math.min(methodName.indexOf("("), 30));
    methodName = methodName.replaceAll("<", "[");
    methodName = methodName.replaceAll(">", "]");
    result.append(methodName).append("(" + methodSignatureEncoded + ")");

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

  private static String addTimeBackground(String time, DiffRow.Tag tag) {
    if (tag.equals(DiffRow.Tag.CHANGE)) {
      return TIME_DIFF.replace("${text}", "" + time);
    }

    return time;
  }
}
