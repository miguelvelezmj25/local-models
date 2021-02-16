package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.google.common.base.Objects;
import guru.nidi.graphviz.attribute.Color;

import java.util.*;

public class DotNode {

  private final String method;
  private final List<DotNode> ancestors = new ArrayList<>();
  private final Map<String, Double> configsToTimes = new HashMap<>();
  private final boolean isHotspot;

  public DotNode(String method, boolean isHotspot) {
    this.method = method;
    this.isHotspot = isHotspot;
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

  public boolean isHotspot() {
    return isHotspot;
  }

  public Color getBgColor(Set<String> configs) {
    List<Double> times = new ArrayList<>(this.configsToTimes.values());
    if (times.size() != configs.size()) {
      return Color.SANDYBROWN.fill();
    }

    Collections.sort(times);
    if (Math.abs(times.get(0) - times.get(times.size() - 1)) > 1.0) {
      return Color.SANDYBROWN.fill();
    }

    return Color.WHITE.fill();
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
    return isHotspot == dotNode.isHotspot
        && Objects.equal(method, dotNode.method)
        && Objects.equal(ancestors, dotNode.ancestors);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(method, ancestors, isHotspot);
  }
}
