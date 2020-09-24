package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

public class UniqueCallStackGetter {

  private final String programName;
  private final String option;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public UniqueCallStackGetter(
      String programName,
      String option,
      String className,
      String methodName,
      String methodSignature) {
    this.programName = programName;
    this.option = option;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public void getUniqueCallStacks() {
    Collection<File> prettyCallStacks = this.getPrettyCallStacks();
    for (File prettyCallStack : prettyCallStacks) {
      System.out.println(prettyCallStack.getName());
    }
  }

  private Collection<File> getPrettyCallStacks() {
    File dir =
        new File(
            CallStackSelector.OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + this.option);
    return FileUtils.listFiles(dir, new String[] {"csv"}, false);
  }
}
