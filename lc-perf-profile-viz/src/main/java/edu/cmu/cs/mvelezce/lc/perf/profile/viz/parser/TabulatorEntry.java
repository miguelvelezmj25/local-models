package edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser;

import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.snapshot.JProfilerSnapshotEntry;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TabulatorEntry {

  private final String method;
  private final double time;
  private final List<TabulatorEntry> tabulatorEntries;

  // Dummy constructor for jackson xml
  private TabulatorEntry() {
    this.method = "";
    this.time = -1.0;
    this.tabulatorEntries = new ArrayList<>();
  }

  public TabulatorEntry(String method, double time, List<TabulatorEntry> tabulatorEntries) {
    this.method = method;
    this.time = time;
    this.tabulatorEntries = tabulatorEntries;
  }

  public static TabulatorEntry from(JProfilerSnapshotEntry entry) {
    String method = getMethod(entry);
    double time = getTime(entry);
    List<TabulatorEntry> tabulatorEntries = getTabulatorEntries(entry);
    return new TabulatorEntry(method, time, tabulatorEntries);
  }

  private static String getMethod(JProfilerSnapshotEntry jProfilerSnapshotEntry) {
    return jProfilerSnapshotEntry.getClassName()
        + "."
        + jProfilerSnapshotEntry.getMethodName()
        + jProfilerSnapshotEntry.getMethodSignature();
  }

  private static double getTime(JProfilerSnapshotEntry jProfilerSnapshotEntry) {
    return jProfilerSnapshotEntry.getTime() / 1E6;
  }

  private static List<TabulatorEntry> getTabulatorEntries(
      JProfilerSnapshotEntry jProfilerSnapshotEntry) {
    List<TabulatorEntry> entries = new ArrayList<>();
    Queue<JProfilerSnapshotEntry> queue = new ArrayDeque<>(jProfilerSnapshotEntry.getNodes());

    while (!queue.isEmpty()) {
      JProfilerSnapshotEntry entry = queue.remove();
      entries.add(TabulatorEntry.from(entry));
    }

    return entries;
  }

  public String getMethod() {
    return method;
  }

  public double getTime() {
    return time;
  }

  public List<TabulatorEntry> getTabulatorEntries() {
    return tabulatorEntries;
  }
}
