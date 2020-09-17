package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.diff.CommandVisitor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/*
 * Custom visitor for file comparison which stores comparison & also generates
 * HTML in the end.
 */
public class FileCommandsVisitor implements CommandVisitor<Character> {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/diff/java/programs";

  // Spans with red & green highlights to put highlighted characters in HTML
  private static final String DELETION = "<span style=\"background-color: #FB504B\">${text}</span>";
  private static final String INSERTION =
      "<span style=\"background-color: #45EA85\">${text}</span>";

  private final String programName;
  private final String optionValue1;
  private final String optionValue2;
  private final String methodName;

  private String left = "";
  private String right = "";

  public FileCommandsVisitor(
      String programName, String optionValue1, String optionValue2, String methodName) {
    this.programName = programName;
    this.optionValue1 = optionValue1;
    this.optionValue2 = optionValue2;
    this.methodName = methodName;
  }

  @Override
  public void visitKeepCommand(Character c) {
    // For new line use <br/> so that in HTML also it shows on next line.
    String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
    // KeepCommand means c present in both left & right. So add this to both without
    // any
    // highlight.
    this.left = this.left + toAppend;
    this.right = this.right + toAppend;
  }

  @Override
  public void visitInsertCommand(Character c) {
    // For new line use <br/> so that in HTML also it shows on next line.
    String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
    // InsertCommand means character is present in right file but not in left. Show
    // with green highlight on right.
    this.right = this.right + INSERTION.replace("${text}", "" + toAppend);
  }

  @Override
  public void visitDeleteCommand(Character c) {
    // For new line use <br/> so that in HTML also it shows on next line.
    String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
    // DeleteCommand means character is present in left file but not in right. Show
    // with red highlight on left.
    this.left = this.left + DELETION.replace("${text}", "" + toAppend);
  }

  public void generateHTML() throws IOException {
    // Get template & replace placeholders with left & right variables with actual
    // comparison
    String template =
        FileUtils.readFileToString(
            new File(
                "./src/main/java/edu/cmu/cs/mvelezce/lc/stack/analysis/diff/compare/difftemplate.html"),
            "utf-8");
    String output = template.replace("${left}", this.left);
    output = output.replace("${right}", this.right);

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
}
