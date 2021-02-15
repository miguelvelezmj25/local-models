package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotNode {

  private final String method;
  private final List<DotNode> ancestors = new ArrayList<>();
  private final Map<String, Double> configsToTimes = new HashMap<>();

  public DotNode(String method) {
    this.method = method;
  }

  public String getMethod() {
    return method;
  }

  public List<DotNode> getAncestors() {
    return ancestors;
  }

  public Map<String, Double> getConfigsToTimes() {
    return configsToTimes;
  }

  @Override
  public String toString() {
    return this.method;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DotNode dotNode = (DotNode) o;
    return Objects.equal(method, dotNode.method) && Objects.equal(ancestors, dotNode.ancestors);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(method, ancestors);
  }
}
