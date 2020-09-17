package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.text.diff.StringsComparator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class CallStackDiff {

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

  public void diff() throws IOException {
    File outputFile =
        new File(
            FileCommandsVisitor.OUTPUT_DIR
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
        LineIterator lineIterator1 = FileUtils.lineIterator(prettyCallStack1);
        LineIterator lineIterator2 = FileUtils.lineIterator(prettyCallStack2);
        this.diffCallStacks(lineIterator1, lineIterator2);
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

  public void diffCallStacks(LineIterator lineIterator1, LineIterator lineIterator2)
      throws IOException {
    // Initialize visitor.
    FileCommandsVisitor fileCommandsVisitor =
        new FileCommandsVisitor(
            this.programName, this.optionValue1, this.optionValue2, this.methodName);

    // Read file line by line so that comparison can be done line by line.
    while (lineIterator1.hasNext() || lineIterator2.hasNext()) {
      /*
       * In case both files have different number of lines, fill in with empty
       * strings. Also append newline char at end so next line comparison moves to
       * next line.
       */
      String left = (lineIterator1.hasNext() ? lineIterator1.nextLine() : "") + "\n";
      String right = (lineIterator2.hasNext() ? lineIterator2.nextLine() : "") + "\n";

      // Prepare diff comparator with lines from both files.
      StringsComparator comparator = new StringsComparator(left, right);

      if (comparator.getScript().getLCSLength()
          > (Integer.max(left.length(), right.length()) * 0.4)) {
        /*
         * If both lines have atleast 40% commonality then only compare with each other
         * so that they are aligned with each other in final diff HTML.
         */
        comparator.getScript().visit(fileCommandsVisitor);
      } else {
        /*
         * If both lines do not have 40% commanlity then compare each with empty line so
         * that they are not aligned to each other in final diff instead they show up on
         * separate lines.
         */
        StringsComparator leftComparator = new StringsComparator(left, "\n");
        leftComparator.getScript().visit(fileCommandsVisitor);
        StringsComparator rightComparator = new StringsComparator("\n", right);
        rightComparator.getScript().visit(fileCommandsVisitor);
      }
    }

    fileCommandsVisitor.generateHTML();
  }
}
