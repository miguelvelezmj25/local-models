package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.google.common.base.Objects;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorEntry;

public class HotspotPathEntry {

  private final String method;

  public HotspotPathEntry(String method) {
    this.method = method;
  }

  public static HotspotPathEntry from(TabulatorEntry entry) {
    return new HotspotPathEntry(entry.getMethod());
  }

  public String getMethod() {
    return method;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HotspotPathEntry that = (HotspotPathEntry) o;
    return Objects.equal(method, that.method);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(method);
  }
}
