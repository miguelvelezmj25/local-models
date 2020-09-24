package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.select;

import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.CallStackDiff;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CallStackDiffSelector {

  public static final double TIME_DIFF_THRESHOLD = 0.1;

  private static final String DELETION = "<span style=\"background-color: #00dcff\">";
  private static final String INSERTION = "<span style=\"background-color: #45EA85\">";

  private final String programName;
  private final String optionValue1;
  private final String optionValue2;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallStackDiffSelector(
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

  public void select() throws IOException {
    File rootDir =
        new File(
            CallStackDiff.OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + this.optionValue1
                + "-"
                + this.optionValue2);
    Collection<File> diffs = FileUtils.listFiles(rootDir, new String[] {"html"}, false);
    LinkedHashMap<String, Integer> filesToDiffs = this.getFilesToDiffs(diffs);
    for (Map.Entry<String, Integer> entry : filesToDiffs.entrySet()) {
      System.out.println(entry.getKey() + " - callstack diffs: " + entry.getValue());
    }
  }

  private LinkedHashMap<String, Integer> getFilesToDiffs(Collection<File> diffs)
      throws IOException {
    Map<String, Integer> filesToDiffs = new HashMap<>();
    for (File diff : diffs) {
      filesToDiffs.put(diff.getName(), this.getDiffCount(diff));
    }

    List<Map.Entry<String, Integer>> entries = new ArrayList<>(filesToDiffs.entrySet());
    entries.sort(Map.Entry.comparingByValue());
    LinkedHashMap<String, Integer> sortedFilesToDiff = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : entries) {
      sortedFilesToDiff.put(entry.getKey(), entry.getValue());
    }
    return sortedFilesToDiff;
  }

  private int getDiffCount(File diff) throws IOException {
    int diffCount = 0;
    List<String> lines = FileUtils.readLines(diff, (String) null);
    for (String line : lines) {
      if (line.contains(INSERTION) || line.contains(DELETION)) {
        diffCount++;
      }
    }
    return diffCount;
  }
}
