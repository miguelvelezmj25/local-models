package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree;

import edu.cmu.cs.mvelezce.region.java.JavaRegion;

import java.util.HashSet;
import java.util.Set;

public class Node {

  private final String id;
  private final String method;
  private final String time;
  private final boolean isRegion;
  private final boolean isSpecial;
  private final Set<Node> callers = new HashSet<>();
  private final Set<Node> callees = new HashSet<>();

  private Node(Builder builder) {
    this.id = builder.id;
    this.method = builder.method;
    this.time = builder.time;
    this.isRegion = builder.isRegion;
    this.isSpecial = builder.isSpecial;
  }

  public static Node from(String callStackEntry, Set<JavaRegion> regions) {
    String[] entries = callStackEntry.split(",");
    String method = entries[0];
    String time = entries[1];
    return new Builder(method, time, isRegion(method, regions)).build();
  }

  public static Node from(Node callNode) {
    return new Builder(callNode.method, callNode.time, callNode.isRegion).build();
  }

  public static boolean isRegion(String method, Set<JavaRegion> regions) {
    for (JavaRegion region : regions) {
      if (!method.startsWith(region.getRegionPackage())) {
        continue;
      }

      String className = method.replace(region.getRegionPackage() + ".", "");
      if (!className.startsWith(region.getRegionClass())) {
        continue;
      }

      String methodSignature = className.replace(region.getRegionClass() + ".", "");
      if (methodSignature.equals(region.getRegionMethodSignature())) {
        return true;
      }
    }

    return false;
  }

  public void addCalleer(Node calleer) {
    this.callers.add(calleer);
  }

  public void addCallee(Node callee) {
    this.callees.add(callee);
  }

  public String getShortMethodName() {
    if (this.isSpecial) {
      return id;
    }

    String[] entries = this.method.split("\\.");
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < (entries.length - 2); i++) {
      result.append(entries[i].charAt(0)).append(".");
    }

    String className = entries[entries.length - 2];
    className = className.substring(0, Math.min(className.length(), 30));
    result.append(className).append(".");

    String methodName = entries[entries.length - 1];
    int methodSignatureEncoded = methodName.hashCode();
    methodName = methodName.substring(0, Math.min(methodName.indexOf("("), 30));
    methodName = methodName.replaceAll("<", "[");
    methodName = methodName.replaceAll(">", "]");
    result.append(methodName).append("(").append(methodSignatureEncoded).append(")");

    return result.toString();
  }

  public String getTime() {
    return time;
  }

  public double getTimeDouble() {
    return Double.parseDouble(time);
  }

  public Set<Node> getCallers() {
    return callers;
  }

  public boolean isSpecial() {
    return isSpecial;
  }

  public boolean isRegion() {
    return isRegion;
  }

  public String getMethod() {
    return method;
  }

  public String getId() {
    return id;
  }

  public static class Builder {
    private final String id;
    private final String method;
    private final String time;
    private final boolean isRegion;
    private final boolean isSpecial;

    public Builder(String id) {
      this.id = id;
      this.method = "";
      this.time = "";
      this.isRegion = false;
      this.isSpecial = true;
    }

    public Builder(String method, String time, boolean isRegion) {
      this.method = method;
      this.time = time;
      this.id = String.valueOf(this.method.hashCode());
      this.isRegion = isRegion;
      this.isSpecial = false;
    }

    public Node build() {
      return new Node(this);
    }
  }
}
