package edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabulatorNode {

  private final String method;
  private final List<TabulatorNode> children = new ArrayList<>();
  private final Map<String, Double> configsToTimes;
  private final boolean isHotspot;

  public TabulatorNode(String method, Map<String, Double> configsToTimes, boolean isHotspot) {
    this.method = method;
    this.configsToTimes = configsToTimes;
    this.isHotspot = isHotspot;
  }

  public String getMethod() {
    return method;
  }

  public Map<String, Double> getConfigsToTimes() {
    return configsToTimes;
  }

  public boolean isHotspot() {
    return isHotspot;
  }

  public List<TabulatorNode> getChildren() {
    return children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TabulatorNode that = (TabulatorNode) o;
    return isHotspot == that.isHotspot
        && Objects.equal(method, that.method)
        && Objects.equal(configsToTimes, that.configsToTimes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(method, configsToTimes, isHotspot);
  }
}
