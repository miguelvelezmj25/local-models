package edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs;

import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.DotNode;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.DotViewer;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot.HotspotDiffEntry;
import edu.cmu.cs.mvelezce.utils.config.Options;

import java.io.IOException;
import java.util.*;

public class VSViewer extends DotViewer {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/vs/hotspots/java/programs";

  public VSViewer(String programName) {
    super(programName);
  }

  @Override
  public Void analyze() throws IOException {
    Map<String, Set<List<HotspotDiffEntry>>> hotspotsToDiffs = this.getHotspotsToDiffs();
    Set<String> configs = this.getConfigs(hotspotsToDiffs.values());
    for (Map.Entry<String, Set<List<HotspotDiffEntry>>> hotspotToDiffs :
        hotspotsToDiffs.entrySet()) {
      Set<DotNode> dotNodes = this.getDotNodes(hotspotToDiffs);
      Map<TabulatorNode, TabulatorNode> tabulatorNodes = this.getTabulatorNodes(dotNodes);
      this.something(tabulatorNodes, new ArrayList<>(configs));
      throw new UnsupportedOperationException("implement");
    }

    throw new UnsupportedOperationException("implement");
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

  private String something(
      Map<TabulatorNode, TabulatorNode> tabulatorNodes, ArrayList<String> configs) {
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
    StringBuilder result = new StringBuilder("[");
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
          configsToTimes.containsKey(configs.get(0)) ? configsToTimes.get(configs.get(0)) : "\"\"");
      result.append(", config2: ");
      result.append(
          configsToTimes.containsKey(configs.get(1)) ? configsToTimes.get(configs.get(1)) : "\"\"");
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

    result.setLength(result.length() - 2);
    result.append(";");
    return result.toString();
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName();
  }
}
