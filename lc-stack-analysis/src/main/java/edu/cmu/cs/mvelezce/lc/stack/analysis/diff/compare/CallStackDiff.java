package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
      for (File prettyCallStack2 : prettyCallStacks2) {
        List<String> lines1 = FileUtils.readLines(prettyCallStack1, (String) null);
        List<String> lines2 = FileUtils.readLines(prettyCallStack2, (String) null);
        List<DiffRow> diff = this.diffCallStacks(lines1, lines2);
        this.generateHTML(this.optionValue1, this.optionValue2, diff);
      }
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

  private List<DiffRow> diffCallStacks(List<String> lines1, List<String> lines2)
      throws DiffException, IOException {
    DiffRowGenerator generator =
        DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            .oldTag(f -> OLD_TAG)
            .newTag(f -> NEW_TAG)
            .build();
    return generator.generateDiffRows(lines1, lines2);
  }

  private void generateHTML(String optionValue1, String optionValue2, List<DiffRow> rows)
      throws IOException {
    // Get template & replace placeholders with left & right variables with actual
    // comparison
    String template =
        FileUtils.readFileToString(
            new File(
                "./src/main/java/edu/cmu/cs/mvelezce/lc/stack/analysis/diff/compare/difftemplate.html"),
            "utf-8");
    String output = template.replace("${left}", this.getTable(rows, optionValue1, false));
    output = output.replace("${right}", this.getTable(rows, optionValue2, true));

    // Write file to disk.
    File outputFile =
        new File(
            OUTPUT_DIR
                + "/"
                + this.programName
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

  private String getTable(List<DiffRow> rows, String optionValue, boolean right) {
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
      String[] entries = line.split(",");
      String method = this.addBackground(entries[0]);
      String time = this.addBackground(entries[1]);
      table
          .append("<td>")
          .append(method)
          .append("</td><td align =\"right\">")
          .append(time)
          .append("</td>")
          .append("\n");
      table.append("</tr>");
    }
    table.append("</table>");
    return table.toString();
  }

  private String addBackground(String entry) {
    if (entry.contains(OLD_TAG)) {
      entry = entry.replaceAll(OLD_TAG, "");
      entry = DELETION.replace("${text}", "" + entry);
    }

    if (entry.contains(NEW_TAG)) {
      entry = entry.replaceAll(NEW_TAG, "");
      entry = INSERTION.replace("${text}", "" + entry);
    }

    return entry;
  }
}
