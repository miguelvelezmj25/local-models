package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree.JProfilerCallTreeBuilder;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class CallStackSelector {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/relevant/callstacks/java/programs";

  private final String programName;
  private final String optionValue1;
  private final String optionValue2;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallStackSelector(
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
    Collection<File> prettyCallStacks1 = this.getPrettyCallStacks(this.optionValue1);
    this.savePrettyCallStacks(this.optionValue1, prettyCallStacks1);
    Collection<File> prettyCallStacks2 = this.getPrettyCallStacks(this.optionValue2);
    this.savePrettyCallStacks(this.optionValue2, prettyCallStacks2);
  }

  private void savePrettyCallStacks(String optionValue, Collection<File> prettyCallStacks)
      throws IOException {
    File dir =
        new File(OUTPUT_DIR + "/" + this.programName + "/" + this.methodName + "/" + optionValue);
    if (dir.exists()) {
      FileUtils.cleanDirectory(dir);
    } else if (!dir.mkdirs()) {
      throw new RuntimeException("Could not create directories");
    }

    for (File file : prettyCallStacks) {
      String prettyCallStack = FileUtils.readFileToString(file, (String) null).trim();
      File outputFile = new File(dir + "/" + UUID.randomUUID() + ".csv");
      try (PrintWriter out = new PrintWriter(outputFile)) {
        out.println(prettyCallStack);
      }
    }
  }

  private Collection<File> getPrettyCallStacks(String optionValue) throws IOException {
    Collection<File> relevantePrettyCallStacks = new HashSet<>();
    File programDir = new File(JProfilerCallTreeBuilder.OUTPUT_DIR + "/" + this.programName);
    for (File callStacksDir : Objects.requireNonNull(programDir.listFiles())) {
      if (!callStacksDir.isDirectory() || !callStacksDir.getName().equals(optionValue)) {
        continue;
      }

      Collection<File> prettyCallStacks =
          FileUtils.listFiles(callStacksDir, new String[] {"csv"}, false);
      for (File prettyCallStack : prettyCallStacks) {
        if (FileUtils.readFileToString(prettyCallStack, (String) null)
            .contains(this.className + "." + this.methodName + this.methodSignature)) {
          relevantePrettyCallStacks.add(prettyCallStack);
        }
      }
    }

    return relevantePrettyCallStacks;
  }
}
