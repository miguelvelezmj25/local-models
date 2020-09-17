package edu.cmu.cs.mvelezce.lc.stack.analysis.stack.entry;

public class StackEntry {

  private final String packageName;
  private final String className;
  private final String methodName;
  private final String methodSignature;
  private final double selfTime;

  public StackEntry(
      String packageName,
      String className,
      String methodName,
      String methodSignature,
      double selfTime) {
    this.packageName = packageName;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
    this.selfTime = selfTime;
  }
}
