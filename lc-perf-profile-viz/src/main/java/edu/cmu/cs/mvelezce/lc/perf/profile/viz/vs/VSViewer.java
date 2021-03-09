package edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs;

import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.DotNode;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.DotViewer;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.HotspotDiffEntry;
import edu.cmu.cs.mvelezce.utils.config.Options;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class VSViewer extends DotViewer {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/vs/hotspots/java/programs";
  public static final String HOTSPOT_DIFF_FILE = "./hotspotdiff.txt";
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.000");

  private final String config1;
  private final String config2;

  public VSViewer(String programName, String config1, String config2) {
    super(programName);

    this.config1 = config1;
    this.config2 = config2;
  }

  @Override
  public Void analyze() throws IOException {
    Map<String, Set<List<HotspotDiffEntry>>> hotspotsToDiffs = this.getHotspotsToDiffs();
    StringBuilder tabulatorData = new StringBuilder("");
    for (Map.Entry<String, Set<List<HotspotDiffEntry>>> hotspotToDiffs :
        hotspotsToDiffs.entrySet()) {
      this.removeOtherConfigs(hotspotToDiffs.getValue());
      if (hotspotToDiffs.getValue().isEmpty()) {
        continue;
      }
      Set<DotNode> dotNodes = this.getDotNodes(hotspotToDiffs);
      Map<TabulatorNode, TabulatorNode> tabulatorNodes = this.getTabulatorNodes(dotNodes);
      tabulatorData.append(this.getTabulatorData(tabulatorNodes));
      tabulatorData.append(",\n");
    }
    tabulatorData.setLength(tabulatorData.length() - 2);

    PrintWriter result = new PrintWriter(HOTSPOT_DIFF_FILE);
    result.println(tabulatorData);
    result.close();
    return null;
  }

  private void removeOtherConfigs(Set<List<HotspotDiffEntry>> hotspotDiffs) {
    for (List<HotspotDiffEntry> hotspotDiff : hotspotDiffs) {
      for (HotspotDiffEntry diffEntry : hotspotDiff) {
        Set<String> entriesToRemove = new HashSet<>();
        for (String config : diffEntry.getConfigsToTimes().keySet()) {
          if (!config.equals(this.config1) && !config.equals(this.config2)) {
            entriesToRemove.add(config);
          }
        }
        for (String entry : entriesToRemove) {
          diffEntry.getConfigsToTimes().remove(entry);
        }
      }
    }

    Set<List<HotspotDiffEntry>> pathsToRemove = new HashSet<>();
    for (List<HotspotDiffEntry> hotspotDiff : hotspotDiffs) {
      if (hotspotDiff.get(0).getConfigsToTimes().isEmpty()) {
        pathsToRemove.add(hotspotDiff);
      }
    }
    hotspotDiffs.removeAll(pathsToRemove);
  }

  private Map<TabulatorNode, TabulatorNode> getTabulatorNodes(Set<DotNode> dotNodes) {
    Map<TabulatorNode, TabulatorNode> tabulatorNodes = new HashMap<>();
    for (DotNode dotNode : dotNodes) {
      for (DotNode potentialChild : dotNodes) {
        if (dotNode.equals(potentialChild)) {
          continue;
        }
        List<DotNode> ancestors = potentialChild.getAncestors();
        if (ancestors.isEmpty() || !ancestors.get(ancestors.size() - 1).equals(dotNode)) {
          continue;
        }

        TabulatorNode child =
            new TabulatorNode(
                potentialChild.getMethod(),
                potentialChild.getConfigsToTimes(),
                potentialChild.isHotspot());
        if (!tabulatorNodes.containsKey(child)) {
          tabulatorNodes.put(child, child);
        }
        TabulatorNode tabulatorNode =
            new TabulatorNode(
                dotNode.getMethod(), dotNode.getConfigsToTimes(), dotNode.isHotspot());
        if (tabulatorNodes.containsKey(tabulatorNode)) {
          tabulatorNode = tabulatorNodes.get(tabulatorNode);
        }
        tabulatorNode.getChildren().add(child);
        tabulatorNodes.put(tabulatorNode, tabulatorNode);
      }
    }
    return tabulatorNodes;
  }

  private String getTabulatorData(Map<TabulatorNode, TabulatorNode> tabulatorNodes) {
    Deque<TabulatorNode> stack = new ArrayDeque<>();
    for (TabulatorNode tabulatorNode : tabulatorNodes.keySet()) {
      if (tabulatorNode.isHotspot()) {
        stack.addFirst(tabulatorNode);
        break;
      }
    }

    Set<TabulatorNode> added = new HashSet<>();
    Map<TabulatorNode, Set<TabulatorNode>> children = new HashMap<>();
    Map<TabulatorNode, TabulatorNode> haveSiblings = new HashMap<>();
    StringBuilder result = new StringBuilder();
    while (!stack.isEmpty()) {
      TabulatorNode current = stack.peekFirst();
      if (added.contains(current)) {
        if (haveSiblings.containsKey(current)) {
          TabulatorNode parent = haveSiblings.remove(current);
          children.get(parent).remove(current);
          if (children.get(parent).isEmpty()) {
            result.append(" }],\n");
            children.remove(parent);
          } else {
            result.append(" },\n");
          }
        } else {
          result.append(" }],\n");
        }
        stack.removeFirst();
        continue;
      }

      result.append("{ method: \"");
      result.append(current.getMethod());
      Map<String, Double> configsToTimes = current.getConfigsToTimes();
      result.append("\", config1: ");
      result.append(
          configsToTimes.containsKey(this.config1)
              ? DECIMAL_FORMAT.format(configsToTimes.get(this.config1))
              : "\"X\"");
      result.append(", config2: ");
      result.append(
          configsToTimes.containsKey(this.config2)
              ? DECIMAL_FORMAT.format(configsToTimes.get(this.config2))
              : "\"X\"");
      added.add(current);
      if (current.getChildren().isEmpty()) {
        continue;
      }

      result.append(", _children: \n[");
      for (TabulatorNode child : current.getChildren()) {
        stack.addFirst(tabulatorNodes.get(child));
      }
      if (current.getChildren().size() > 1) {
        children.put(current, new HashSet<>());
        for (TabulatorNode child : current.getChildren()) {
          children.get(current).add(child);
          haveSiblings.put(child, current);
        }
      }
    }

    result.setLength(result.length() - 3);
    return result.toString();
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName();
  }
}
