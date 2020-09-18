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

  private static final String DELETION = "<span style=\"background-color: #00dcff\">${text}</span>";
  private static final String INSERTION =
      "<span style=\"background-color: #45EA85\">${text}</span>";
  private static final String OLD_TAG = "~";
  private static final String NEW_TAG = "!";

  private final String programName;
  private final String optionValue1;
  private final String optionValue2;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallStackDiff(
      String programName,
      String optionValue1,
      String optionValue2,
      String className,
      String methodName,
      String methodSignature) {
    this.programName = programName;
    this.optionValue1 = optionValue1;
    this.optionValue2 = optionValue2;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public void diff() throws IOException, DiffException {
    File outputFile =
        new File(
            OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + "/"
                + this.optionValue1
                + "-"
                + this.optionValue2);
    if (outputFile.exists()) {
      FileUtils.cleanDirectory(outputFile);
    }

    System.err.println(
        "Figure out how to compare across option values more efficiently than everything with everything");
    Collection<File> prettyCallStacks1 = this.getPrettyCallStacks(this.optionValue1);
    Collection<File> prettyCallStacks2 = this.getPrettyCallStacks(this.optionValue2);

    for (File prettyCallStack1 : prettyCallStacks1) {
      List<String> lines1 = FileUtils.readLines(prettyCallStack1, (String) null);
      for (File prettyCallStack2 : prettyCallStacks2) {
        List<String> lines2 = FileUtils.readLines(prettyCallStack2, (String) null);
        Map<String, Pair<String, String>> allMethodsToTimes = getAllMethodsToTimes(lines1, lines2);
        //        if (getMaxSelfTime(allMethodsToTimes.values()) < 3.55) {
        //          continue;
        //        }

        List<DiffRow> diff = diffCallStacks(lines1, lines2);
        this.generateHTML(this.optionValue1, this.optionValue2, diff, allMethodsToTimes);
      }
    }
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
      String optionValue2,
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
        template.replace("${left}", getTable(rows, optionValue1, false, allMethodsToTimes));
    output = output.replace("${right}", getTable(rows, optionValue2, true, allMethodsToTimes));

    // Write file to disk.
    File outputFile =
        new File(
            OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + "/"
                + this.optionValue1
                + "-"
                + this.optionValue2);
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
      boolean right,
      Map<String, Pair<String, String>> allMethodsToTimes)
      throws DiffException {
    StringBuilder table = new StringBuilder();
    table.append("<table>");
    table.append("<tr>");
    table.append("<td>");
    table.append("<span style=\"font-weight:bold\">");
    table.append(optionValue);
    table.append("</span>");
    table.append("</td>");
    table.append("</tr>");
    for (DiffRow row : rows) {
      table.append("<tr>");
      String line = right ? row.getNewLine() : row.getOldLine();
      if (!line.isEmpty()) {
        String[] entries = line.split(",");
        entries[0] = entries[0].replaceAll("&lt;", "<");
        entries[0] = entries[0].replaceAll("&gt;", ">");
        String method = addBackground(compressMethod(entries[0]));
        Pair<String, String> times = allMethodsToTimes.get(removeTags(entries[0]));
        DiffRow timesDiff = compareTimes(times);
        String time =
            right ? addBackground(timesDiff.getNewLine()) : addBackground(timesDiff.getOldLine());
        table
            .append("<td>")
            .append(method)
            .append("</td><td align =\"right\">")
            .append(time)
            .append("</td>")
            .append("\n");
      } else {
        table.append("<td>&nbsp</td>");
      }
      table.append("</tr>");
    }
    table.append("</table>");
    return table.toString();
  }

  private static DiffRow compareTimes(Pair<String, String> times) throws DiffException {
    DiffRowGenerator generator =
        DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            .oldTag(f -> OLD_TAG)
            .newTag(f -> NEW_TAG)
            .build();
    return generator
        .generateDiffRows(
            Collections.singletonList(times.getLeft()), Collections.singletonList(times.getRight()))
        .get(0);
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
    className = className.substring(0, Math.min(className.length(), 10));
    result.append(className).append(".");

    String methodName = entries[entries.length - 1];
    methodName = methodName.substring(0, Math.min(methodName.indexOf("("), 10));
    result.append(methodName).append("()");

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
}
