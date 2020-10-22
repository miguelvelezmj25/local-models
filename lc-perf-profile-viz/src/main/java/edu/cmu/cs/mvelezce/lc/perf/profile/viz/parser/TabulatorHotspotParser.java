package edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.snapshot.Hotspot;
import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.snapshot.JProfilerSnapshotEntry;
import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.snapshot.Node;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.hotspot.JProfilerHotspotExporter;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TabulatorHotspotParser implements Analysis<Void> {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/tabulator/hotspots/java/programs";
  private static final String OPEN_TREE = "<tree";
  private static final String CLOSE_TREE = "</tree>";
  private static final String OPEN_NODE = "<node";
  private static final String CLOSE_NODE = "</node>";
  private static final String OPEN_HOTSPOT = "<hotspot";
  private static final String CLOSE_HOTSPOT = "</hotspot>";

  private final String programName;

  public TabulatorHotspotParser(String programName) {
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
    File snapshotsDir = new File(JProfilerHotspotExporter.OUTPUT_DIR + "/" + this.programName);
    Collection<File> hotspotFiles = FileUtils.listFiles(snapshotsDir, new String[] {"xml"}, false);
    for (File hotspotFile : hotspotFiles) {
      List<Hotspot> hotspots = this.parseHotspots(hotspotFile);
      List<TabulatorEntry> entries = this.parseTabulatorEntries(hotspots);
      this.saveEntries(hotspotFile, entries);
    }

    return null;
  }

  private void saveEntries(File hotspotFile, List<TabulatorEntry> entries) throws IOException {
    String outputFile =
        this.outputDir() + "/" + FilenameUtils.removeExtension(hotspotFile.getName()) + ".json";
    File file = new File(outputFile);
    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent dirs");
    }
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.writeValue(file, entries);
  }

  private List<TabulatorEntry> parseTabulatorEntries(List<Hotspot> hotspots) {
    List<TabulatorEntry> entries = new ArrayList<>(hotspots.size());
    for (Hotspot hotspot : hotspots) {
      TabulatorEntry entry = TabulatorEntry.from(hotspot);
      entries.add(entry);
    }

    return entries;
  }

  private List<Hotspot> parseHotspots(File hotspotFile) throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    BufferedReader reader = new BufferedReader(new FileReader(hotspotFile));
    Deque<JProfilerSnapshotEntry> stack = new ArrayDeque<>(10_000);
    List<Hotspot> hotspots = new ArrayList<>(1_000);

    String line;
    while ((line = reader.readLine()) != null) {
      if (line.contains(CLOSE_TREE)) {
        if (!stack.isEmpty()) {
          throw new RuntimeException("The stack of nodes is not empty");
        }
      } else if (line.contains(OPEN_HOTSPOT)) {
        Hotspot hotspot =
            xmlMapper.readValue(line + CLOSE_HOTSPOT, new TypeReference<Hotspot>() {});
        stack.push(hotspot);
        hotspots.add(hotspot);

        if (hotspot.getLeaf()) {
          stack.pop();
        }
      } else if (line.contains(OPEN_NODE)) {
        Node node = xmlMapper.readValue(line + CLOSE_NODE, new TypeReference<Node>() {});
        JProfilerSnapshotEntry top = stack.peek();
        if (top == null) {
          throw new RuntimeException("The stack of nodes and hotpots is empty");
        }
        top.getNodes().add(node);
        stack.push(node);

        if (node.getLeaf()) {
          stack.pop();
        }
      } else if (line.contains(CLOSE_NODE)) {
        if (stack.isEmpty()) {
          throw new RuntimeException("The stack of nodes and hotpots is empty");
        }

        if (!(stack.peek() instanceof Node)) {
          throw new RuntimeException(
              "Expected to pop a Node, but popped a " + stack.peek().getClass());
        }

        stack.pop();
      } else if (line.contains(CLOSE_HOTSPOT)) {
        if (stack.isEmpty()) {
          throw new RuntimeException("The stack of nodes and hotpots is empty");
        }

        if (!(stack.peek() instanceof Hotspot)) {
          throw new RuntimeException(
              "Expected to pop a Hotspot, but popped a " + stack.peek().getClass());
        }

        stack.pop();
      }
    }
    reader.close();

    return hotspots;
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
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
