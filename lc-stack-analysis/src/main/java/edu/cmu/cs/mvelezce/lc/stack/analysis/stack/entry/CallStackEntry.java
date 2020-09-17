package edu.cmu.cs.mvelezce.lc.stack.analysis.stack.entry;

import java.text.DecimalFormat;

public class CallStackEntry {

  private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

  private final String className;
  private final String methodName;
  private final String methodSignature;
  private final double selfTime;

  // Dummy constructor
  private CallStackEntry() {
    this.className = "";
    this.methodName = "";
    this.methodSignature = "";
    this.selfTime = 0.0;
  }

  public CallStackEntry(
      String className, String methodName, String methodSignature, double selfTime) {
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
    this.selfTime = selfTime;
  }

  public String getClassName() {
    return className;
  }

  public String getMethodName() {
    return methodName;
  }

  public String getMethodSignature() {
    return methodSignature;
  }

  public double getSelfTime() {
    return selfTime;
  }

  public String prettyPrint() {
    return this.className
        + "."
        + this.getMethodName()
        + this.methodSignature
        + ","
        + DECIMAL_FORMAT.format(this.selfTime / 1E6);
  }
}
