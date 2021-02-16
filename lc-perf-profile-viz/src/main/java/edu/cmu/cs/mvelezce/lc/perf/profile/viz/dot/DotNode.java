package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.google.common.base.Objects;
import guru.nidi.graphviz.attribute.Color;

import java.util.*;

public class DotNode {

  public static final Color NORMAL_BG = Color.WHITE.fill();
  public static final Color DIFF_BG = Color.KHAKI1.fill();

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

  public Color getBgColor(Set<String> configs, Set<DotNode> dotNodes) {
    if (!this.isHotspot && !this.ancestorHasMultipleCallers(this, dotNodes)) {
      return NORMAL_BG;
    }

    List<Double> times = new ArrayList<>(this.configsToTimes.values());
    if (times.size() != configs.size()) {
      return DIFF_BG;
    }

    Collections.sort(times);
    if (Math.abs(times.get(0) - times.get(times.size() - 1)) > 1.0) {
      return DIFF_BG;
    }

    return NORMAL_BG;
  }

  private boolean ancestorHasMultipleCallers(DotNode dotNode, Set<DotNode> dotNodes) {
    if (dotNode.isHotspot()) {
      return false;
    }
    DotNode ancestor = dotNode.getAncestors().get(dotNode.getAncestors().size() - 1);
    for (DotNode otherNode : dotNodes) {
      if (dotNode.equals(otherNode)) {
        continue;
      }
      List<DotNode> otherNodeAncestors = otherNode.getAncestors();
      if (otherNodeAncestors.isEmpty()) {
        continue;
      }
      if (otherNodeAncestors.get(otherNodeAncestors.size() - 1).equals(ancestor)) {
        return true;
      }
    }
    return false;
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
