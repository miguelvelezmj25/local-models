package edu.cmu.cs.mvelezce.lc.stack.analysis.stack.entry;

public class CallStackEntry {

  private final String className;
  private final String methodName;
  private final String methodSignature;
  private final double selfTime;

  public CallStackEntry(
      String className, String methodName, String methodSignature, double selfTime) {
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
    this.selfTime = selfTime;
  }
}
