package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import java.util.HashMap;
import java.util.Map;

public class HotspotDiffEntry {

  private final String method;
  private final Map<String, Double> configsToTimes = new HashMap<>();

  public HotspotDiffEntry(String method) {
    this.method = method;
  }

  public static HotspotDiffEntry from(HotspotPathEntry pathEntry) {
    return new HotspotDiffEntry(pathEntry.getMethod());
  }

  public String getMethod() {
    return method;
  }

  public Map<String, Double> getConfigsToTimes() {
    return configsToTimes;
  }
}
