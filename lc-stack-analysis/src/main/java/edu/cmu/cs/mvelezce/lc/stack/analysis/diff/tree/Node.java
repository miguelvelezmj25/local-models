package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree;

import edu.cmu.cs.mvelezce.region.java.JavaRegion;

import java.util.HashSet;
import java.util.Set;

public class Node {

  private final String id;
  private final String method;
  private final String time;
  private final boolean isRegion;
  private final Set<Node> callers = new HashSet<>();
  private final Set<Node> callees = new HashSet<>();

  private Node(Builder builder) {
    this.id = builder.id;
    this.method = builder.method;
    this.time = builder.time;
    this.isRegion = builder.isRegion;
  }

  public static Node from(String callStackEntry, Set<JavaRegion> regions) {
    String[] entries = callStackEntry.split(",");
    String method = entries[0];
    String time = entries[1];
    return new Builder(method, time, isRegion(method, regions)).build();
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

  public static class Builder {
    private final String id;
    private final String method;
    private final String time;
    private final boolean isRegion;

    public Builder(String id) {
      this.id = id;
      this.method = "";
      this.time = "";
      this.isRegion = false;
    }

    public Builder(String method, String time, boolean isRegion) {
      this.method = method;
      this.time = time;
      this.id = String.valueOf(this.method.hashCode());
      this.isRegion = isRegion;
    }

    public Node build() {
      return new Node(this);
    }
  }
}
