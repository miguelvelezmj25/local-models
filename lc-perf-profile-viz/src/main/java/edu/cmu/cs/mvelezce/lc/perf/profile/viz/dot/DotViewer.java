package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorEntry;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorHotspotParser;
import edu.cmu.cs.mvelezce.utils.config.Options;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Rasterizer;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class DotViewer implements Analysis<Void> {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/dot/hotspots/java/programs";
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
  private final String programName;

  public DotViewer(String programName) {
    this.programName = programName;
  }

  @Override
  public Void analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    return this.analyze();
  }

  @Override
  public Void analyze() throws IOException {
    Map<String, Set<List<HotspotDiffEntry>>> hotspotsToDiffs = this.getHotspotsToDiffs();
    Set<String> configs = this.getConfigs(hotspotsToDiffs.values());
    for (Map.Entry<String, Set<List<HotspotDiffEntry>>> hotspotToDiffs :
        hotspotsToDiffs.entrySet()) {
      Set<DotNode> dotNodes = this.getDotNodes(hotspotToDiffs);
      MutableGraph graph =
          Factory.mutGraph(hotspotToDiffs.getKey())
              .setDirected(false)
              .graphAttrs()
              .add(Rank.dir(Rank.RankDir.BOTTOM_TO_TOP));
      for (DotNode dotNode : dotNodes) {
        if (!dotNode.getMethod().contains("main([Ljava/lang/String;)V")) {
          continue;
        }
        this.addNodes(graph, dotNode, configs, dotNodes);
      }
      for (DotNode dotNode : dotNodes) {
        if (dotNode.getMethod().contains("main([Ljava/lang/String;)V")) {
          continue;
        }
        this.addNodes(graph, dotNode, configs, dotNodes);
      }

      for (MutableNode graphNode : graph.nodes()) {
        DotNode dotNode = this.getDotNode(graphNode.name().toString(), dotNodes);
        if (dotNode.getAncestors().isEmpty()) {
          continue;
        }
        DotNode calleeDotNode = dotNode.getAncestors().get(dotNode.getAncestors().size() - 1);
        MutableNode calleeGraphNode = this.getGraphNode(calleeDotNode, graph.nodes());
        graphNode.addLink(
            Link.between(graphNode.port("method").port(), calleeGraphNode.port("method")));
      }

      String hotspot = hotspotToDiffs.getKey();
      hotspot = hotspot.substring(hotspot.lastIndexOf(".") + 1);
      hotspot = hotspot.substring(0, hotspot.indexOf("("));

      Graphviz.fromGraph(graph)
          .rasterize(Rasterizer.builtIn("pdf"))
          .toFile(
              new File(
                  Options.DIRECTORY
                      + "/dot/hotspots/java/programs/"
                      + this.programName
                      + "/"
                      + hotspot));
    }

    return null;
  }

  protected Set<String> getConfigs(Collection<Set<List<HotspotDiffEntry>>> allDiffs) {
    Set<String> configs = new HashSet<>();
    for (Set<List<HotspotDiffEntry>> diffs : allDiffs) {
      for (List<HotspotDiffEntry> diff : diffs) {
        for (HotspotDiffEntry diffEntry : diff) {
          configs.addAll(diffEntry.getConfigsToTimes().keySet());
        }
      }
    }
    return configs;
  }

  private void addNodes(
      MutableGraph graph, DotNode dotNode, Set<String> configs, Set<DotNode> dotNodes) {
    int i = 0;
    List<String> records = new ArrayList<>();
    if (dotNode.isHotspot()) {
      records.add(
          Records.rec(
              String.valueOf(i), Label.lines(Label.Justification.MIDDLE, "Self-time").toString()));
      i++;
    }

    String configRecords = this.getConfigRecords(dotNode, records, configs, i);
    records = new ArrayList<>();
    if (!configRecords.isEmpty()) {
      records.add(configRecords);
    }
    records.add(0, Records.rec("method", dotNode.getMethod()));

    Color bgColor = dotNode.getBgColor(configs, dotNodes);
    MutableNode graphNode =
        Factory.mutNode(
                String.valueOf(
                    Label.lines(
                        Label.Justification.LEFT, String.valueOf(Math.abs(dotNode.hashCode())))))
            .add(Records.of(records.toArray(new String[0])))
            .add(Style.FILLED, bgColor)
            .add(bgColor.equals(DotNode.NORMAL_BG) ? Color.BLACK : Color.LIGHTSLATEBLUE);
    graph.add(graphNode);
  }

  private String getConfigRecords(
      DotNode dotNode, List<String> records, Set<String> configs, int i) {
    Set<String> configsWithTime = new HashSet<>();
    for (Map.Entry<String, Double> configToTime : dotNode.getConfigsToTimes().entrySet()) {
      records.add(
          Records.rec(
              String.valueOf(i),
              Label.lines(
                      Label.Justification.LEFT,
                      configToTime.getKey() + " - " + configToTime.getValue())
                  .toString()));
      i++;
      configsWithTime.add(configToTime.getKey());
    }

    Set<String> configsMissed = new HashSet<>(configs);
    configsMissed.removeAll(configsWithTime);
    for (String config : configsMissed) {
      records.add(
          Records.rec(
              String.valueOf(i),
              Label.lines(Label.Justification.LEFT, config + " - X").toString()));
      i++;
    }

    return Records.turn(records.toArray(new String[0]));
  }

  private MutableNode getGraphNode(DotNode dotNode, Collection<MutableNode> nodes) {
    for (MutableNode mutableNode : nodes) {
      if ((Math.abs(dotNode.hashCode()) + "\\l").equals(mutableNode.name().toString())) {
        return mutableNode;
      }
    }
    throw new RuntimeException("Could not find graph node");
  }

  private DotNode getDotNode(String graphNodeName, Set<DotNode> dotNodes) {
    for (DotNode dotNode : dotNodes) {
      if (graphNodeName.equals(Math.abs(dotNode.hashCode()) + "\\l")) {
        return dotNode;
      }
    }
    throw new RuntimeException("Could not find dot node");
  }

  protected Set<DotNode> getDotNodes(Map.Entry<String, Set<List<HotspotDiffEntry>>> entry) {
    Map<DotNode, DotNode> dotNodes = new HashMap<>();
    for (List<HotspotDiffEntry> diff : entry.getValue()) {
      List<DotNode> ancestors = new ArrayList<>();
      for (HotspotDiffEntry diffEntry : diff) {
        DotNode dotNode =
            new DotNode(diffEntry.getMethod(), diffEntry.getMethod().equals(entry.getKey()));
        dotNode.getAncestors().addAll(new ArrayList<>(ancestors));
        dotNodes.putIfAbsent(dotNode, dotNode);
        ancestors.add(dotNode);
      }
    }

    for (List<HotspotDiffEntry> diff : entry.getValue()) {
      List<DotNode> ancestors = new ArrayList<>();
      for (HotspotDiffEntry diffEntry : diff) {
        DotNode dotNode =
            new DotNode(diffEntry.getMethod(), diffEntry.getMethod().equals(entry.getKey()));
        dotNode.getAncestors().addAll(new ArrayList<>(ancestors));
        DotNode existingDotNode = dotNodes.get(dotNode);
        existingDotNode.getConfigsToTimes().putAll(diffEntry.getConfigsToTimes());
        ancestors.add(dotNode);
      }
    }

    return dotNodes.keySet();
  }

  protected Map<String, Set<List<HotspotDiffEntry>>> getHotspotsToDiffs() throws IOException {
    File snapshotsDir =
        new File(
            "./lc-perf-profile-viz/" + TabulatorHotspotParser.OUTPUT_DIR + "/" + this.programName);
    Collection<File> tabulatorEntryFiles =
        FileUtils.listFiles(snapshotsDir, new String[] {"json"}, false);

    // Initialize optionsToProfilerEntries
    Map<String, List<TabulatorEntry>> configsToProfilerEntries = new HashMap<>();
    for (File tabularEntryFile : tabulatorEntryFiles) {
      configsToProfilerEntries.put(tabularEntryFile.getName(), new ArrayList<>());
    }

    for (File tabularEntryFile : tabulatorEntryFiles) {
      List<TabulatorEntry> profilerEntries =
          configsToProfilerEntries.get(tabularEntryFile.getName());
      profilerEntries.addAll(this.parseProfilerEntries(tabularEntryFile));
    }

    // Initialize hotspotsToPaths
    Map<String, Set<List<HotspotPathEntry>>> hotspotsToPaths = new HashMap<>();
    for (List<TabulatorEntry> profilerEntries : configsToProfilerEntries.values()) {
      Set<String> hotspots = this.getHotspots(profilerEntries);
      for (String hotspot : hotspots) {
        hotspotsToPaths.put(hotspot, new HashSet<>());
      }
    }

    for (List<TabulatorEntry> entries : configsToProfilerEntries.values()) {
      for (TabulatorEntry entry : entries) {
        String hotspot = entry.getMethod();
        Set<List<HotspotPathEntry>> paths = hotspotsToPaths.get(hotspot);
        paths.addAll(this.getPaths(entry));
      }
    }

    // Initialize configsToHotspotMethodEntries
    Map<String, Map<String, Set<List<HotspotMethodEntry>>>> configsToHotspotMethodEntries =
        new HashMap<>();
    for (String config : configsToProfilerEntries.keySet()) {
      Map<String, Set<List<HotspotMethodEntry>>> hotspotsToMethodEntries = new HashMap<>();
      for (String hotspot : hotspotsToPaths.keySet()) {
        hotspotsToMethodEntries.put(hotspot, new HashSet<>());
      }
      configsToHotspotMethodEntries.put(config, hotspotsToMethodEntries);
    }

    for (Map.Entry<String, List<TabulatorEntry>> configToProfilerEntries :
        configsToProfilerEntries.entrySet()) {
      for (TabulatorEntry entry : configToProfilerEntries.getValue()) {
        Set<List<HotspotPathEntry>> hotspotPaths = hotspotsToPaths.get(entry.getMethod());
        Set<List<HotspotMethodEntry>> hotspotMethodEntries =
            this.getHotspotMethodEntries(entry, hotspotPaths);
        Map<String, Set<List<HotspotMethodEntry>>> hotspotsToMethodEntries =
            configsToHotspotMethodEntries.get(configToProfilerEntries.getKey());
        hotspotsToMethodEntries.get(entry.getMethod()).addAll(hotspotMethodEntries);
      }
    }

    // Initialize hotspotsToDiffs
    Map<String, Set<List<HotspotDiffEntry>>> hotspotsToDiffs = new HashMap<>();
    for (String hotspot : hotspotsToPaths.keySet()) {
      hotspotsToDiffs.put(hotspot, new HashSet<>());
    }

    // Add HotspotPathEntries
    for (Map.Entry<String, Set<List<HotspotPathEntry>>> entry : hotspotsToPaths.entrySet()) {
      for (List<HotspotPathEntry> path : entry.getValue()) {
        Set<List<HotspotDiffEntry>> diffs = hotspotsToDiffs.get(entry.getKey());
        diffs.add(this.getDiffEntries(path));
      }
    }

    for (Map.Entry<String, Set<List<HotspotDiffEntry>>> entry : hotspotsToDiffs.entrySet()) {
      for (List<HotspotDiffEntry> diff : entry.getValue()) {
        this.populateDiffs(diff, configsToHotspotMethodEntries);
      }
    }

    return hotspotsToDiffs;
  }

  private void populateDiffs(
      List<HotspotDiffEntry> diff,
      Map<String, Map<String, Set<List<HotspotMethodEntry>>>> configsToHotspotMethodEntries) {
    for (Map.Entry<String, Map<String, Set<List<HotspotMethodEntry>>>>
        configToHotspotMethodEntries : configsToHotspotMethodEntries.entrySet()) {
      for (Map.Entry<String, Set<List<HotspotMethodEntry>>> hotspotsToMethodEntries :
          configToHotspotMethodEntries.getValue().entrySet()) {
        if (!diff.get(0).getMethod().equals(hotspotsToMethodEntries.getKey())) {
          continue;
        }

        for (List<HotspotMethodEntry> methodEntries : hotspotsToMethodEntries.getValue()) {
          if (!this.isSamePath(diff, methodEntries)) {
            continue;
          }

          String config = configToHotspotMethodEntries.getKey();
          config = config.replace(".json", "");
          for (int i = 0; i < diff.size(); i++) {
            diff.get(i).getConfigsToTimes().put(config, methodEntries.get(i).getTime());
          }
        }
      }
    }
  }

  private boolean isSamePath(
      List<HotspotDiffEntry> hotspotDiffPath, List<HotspotMethodEntry> hotspotMethodEntryPath) {
    if (hotspotDiffPath.size() != hotspotMethodEntryPath.size()) {
      return false;
    }

    for (int i = 0; i < hotspotDiffPath.size(); i++) {
      if (!hotspotDiffPath.get(i).getMethod().equals(hotspotMethodEntryPath.get(i).getMethod())) {
        return false;
      }
    }

    return true;
  }

  private Set<List<HotspotMethodEntry>> getHotspotMethodEntries(
      TabulatorEntry entry, Set<List<HotspotPathEntry>> hotspotPaths) {
    Set<List<HotspotMethodEntry>> hotspotMethodEntries = new HashSet<>();
    for (List<HotspotPathEntry> path : hotspotPaths) {
      if (!this.hasPath(entry, path)) {
        continue;
      }

      TabulatorEntry current = entry;
      List<HotspotMethodEntry> methodEntries = new ArrayList<>();
      for (int i = 0; i < path.size(); i++) {
        HotspotPathEntry pathEntry = path.get(i);
        HotspotMethodEntry methodEntry = HotspotMethodEntry.from(pathEntry, current.getTime());
        methodEntries.add(methodEntry);

        if (i == (path.size() - 1)) {
          continue;
        }

        boolean foundNext = false;
        HotspotPathEntry next = path.get(i + 1);
        for (TabulatorEntry nextEntry : current.getTabulatorEntries()) {
          if (nextEntry.getMethod().equals(next.getMethod())) {
            current = nextEntry;
            foundNext = true;
            break;
          }
        }

        if (!foundNext) {
          throw new RuntimeException("Could not find next entry in path");
        }
      }
      hotspotMethodEntries.add(methodEntries);
    }
    return hotspotMethodEntries;
  }

  private boolean hasPath(TabulatorEntry entry, List<HotspotPathEntry> path) {
    TabulatorEntry current = entry;
    for (int i = 0; i < path.size(); i++) {
      HotspotPathEntry pathEntry = path.get(i);
      if (!pathEntry.getMethod().equals(current.getMethod())) {
        return false;
      }
      if (i == (path.size() - 1)) {
        return true;
      }

      boolean foundNext = false;
      HotspotPathEntry nextPathEntry = path.get(i + 1);
      for (TabulatorEntry next : current.getTabulatorEntries()) {
        if (nextPathEntry.getMethod().equals(next.getMethod())) {
          current = next;
          foundNext = true;
          break;
        }
      }
      if (!foundNext) {
        return false;
      }
    }

    throw new RuntimeException("Could not determine if the entry has the path");
  }

  private List<HotspotDiffEntry> getDiffEntries(List<HotspotPathEntry> path) {
    List<HotspotDiffEntry> diffEntries = new ArrayList<>();
    for (HotspotPathEntry pathEntry : path) {
      diffEntries.add(HotspotDiffEntry.from(pathEntry));
    }
    return diffEntries;
  }

  private Set<List<HotspotPathEntry>> getPaths(TabulatorEntry hotspotTabulatorEntry) {
    Set<List<HotspotPathEntry>> paths = new HashSet<>();
    Deque<TabulatorEntry> stack = new ArrayDeque<>();
    stack.addFirst(hotspotTabulatorEntry);
    Map<TabulatorEntry, TabulatorEntry> entriesToParents = new HashMap<>();
    entriesToParents.put(hotspotTabulatorEntry, null);
    while (!stack.isEmpty()) {
      TabulatorEntry tabulatorEntry = stack.removeFirst();
      if (tabulatorEntry.getTabulatorEntries().isEmpty()) {
        paths.add(this.getHotspotPath(tabulatorEntry, entriesToParents));
      } else {
        for (TabulatorEntry childTabulatorEntries : tabulatorEntry.getTabulatorEntries()) {
          stack.addFirst(childTabulatorEntries);
          entriesToParents.put(childTabulatorEntries, tabulatorEntry);
        }
      }
    }
    return paths;
  }

  private List<HotspotPathEntry> getHotspotPath(
      TabulatorEntry tabulatorEntry, Map<TabulatorEntry, TabulatorEntry> entriesToParents) {
    Deque<HotspotPathEntry> stack = new ArrayDeque<>();
    stack.addFirst(HotspotPathEntry.from(tabulatorEntry));
    TabulatorEntry current = tabulatorEntry;
    while ((current = entriesToParents.get(current)) != null) {
      stack.addFirst(HotspotPathEntry.from(current));
    }

    List<HotspotPathEntry> hotspotPath = new ArrayList<>();
    while (!stack.isEmpty()) {
      hotspotPath.add(stack.removeFirst());
    }
    return hotspotPath;
  }

  private Set<String> getHotspots(List<TabulatorEntry> hotspotTrees) {
    Set<String> hotspots = new HashSet<>();
    for (TabulatorEntry hotspotTree : hotspotTrees) {
      hotspots.add(hotspotTree.getMethod());
    }
    return hotspots;
  }

  private List<TabulatorEntry> parseProfilerEntries(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(file, new TypeReference<List<TabulatorEntry>>() {});
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }

  public String getProgramName() {
    return programName;
  }

  @Override
  public void writeToFile(Void unused) {
    throw new UnsupportedOperationException("Method should not be called");
  }

  @Override
  public Void readFromFile(File file) {
    throw new UnsupportedOperationException("Method should not be called");
  }
}
