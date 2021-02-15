package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.google.common.base.Objects;

public class HotspotMethodEntry {

  private final String method;
  private final double time;

  public HotspotMethodEntry(String method, double time) {
    this.method = method;
    this.time = time;
  }

  public static HotspotMethodEntry from(HotspotPathEntry pathEntry, double time) {
    return new HotspotMethodEntry(pathEntry.getMethod(), time);
  }

  public String getMethod() {
    return method;
  }

  public double getTime() {
    return time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HotspotMethodEntry that = (HotspotMethodEntry) o;
    return Double.compare(that.time, time) == 0 && Objects.equal(method, that.method);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(method, time);
  }
}
