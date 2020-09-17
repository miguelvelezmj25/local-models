package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mijecu25.meme.utils.execute.Executor;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.snapshot.CallTreeNode;
import edu.cmu.cs.mvelezce.lc.stack.analysis.stack.CallStack;
import edu.cmu.cs.mvelezce.lc.stack.analysis.stack.entry.CallStackEntry;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

public class JProfilerCallTreeBuilder implements Analysis<Void> {

  private static final String OUTPUT_DIR = Options.DIRECTORY + "/jprofiler/java/programs";
  private static final String SNAPSHOTS_ROOT =
      "../lc-profiler-analysis/src/main/resources/jprofiler/snapshots/java/programs/";
  private static final String JPROFILER_11_EXPORT_CMD_OSX =
      "/Applications/JProfiler.app/Contents/Resources/app/bin/jpexport";
  private static final String JPROFILER_CALL_TREE_FILE = "calltree.xml";
  private static final String OPEN_TREE = "<tree";
  private static final String CLOSE_TREE = "</tree>";
  private static final String OPEN_NODE = "<node";
  private static final String CLOSE_NODE = "</node>";
  private static final String CLOSE_TAG = "/>";

  private final String programName;

  public JProfilerCallTreeBuilder(String programName) {
    this.programName = programName;
  }

  @Override
  public Void analyze(String[] args) throws IOException, InterruptedException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    return this.analyze();
  }

  @Override
  public Void analyze() throws IOException, InterruptedException {
    File snapshotsDir = new File(SNAPSHOTS_ROOT + "/" + this.programName);
    Collection<File> snapshots = FileUtils.listFiles(snapshotsDir, new String[] {"jps"}, false);
    for (File snapshot : snapshots) {
      this.export(snapshot.getPath());
      Collection<CallStack<CallStackEntry>> callStacks = this.getCallStacks();
      this.saveCallStacks(snapshot.getName(), callStacks);
      this.savePrettyCallStacks(snapshot.getName(), callStacks);
    }

    return null;
  }

  private void savePrettyCallStacks(
      String snapshotName, Collection<CallStack<CallStackEntry>> callStacks) throws IOException {
    snapshotName = snapshotName.replaceAll(".jps", "");
    for (CallStack<CallStackEntry> callStack : callStacks) {
      StringBuilder prettyCallStack = new StringBuilder();
      for (CallStackEntry entry : callStack.getStack()) {
        prettyCallStack.append(entry.prettyPrint());
        prettyCallStack.append("\n");
      }

      String outputFile = this.outputDir() + "/" + snapshotName + "/" + UUID.randomUUID() + ".csv";
      File file = new File(outputFile);
      if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent file dirs");
      }

      try (PrintWriter out = new PrintWriter(outputFile)) {
        out.println(prettyCallStack.toString());
      }
    }
  }

  private void saveCallStacks(String snapshotName, Collection<CallStack<CallStackEntry>> callStacks)
      throws IOException {
    snapshotName = snapshotName.replaceAll(".jps", "");
    for (CallStack<CallStackEntry> callStack : callStacks) {
      String outputFile =
          this.outputDir() + "/" + snapshotName + "/" + UUID.randomUUID() + Options.DOT_JSON;
      File file = new File(outputFile);
      if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent file dirs");
      }

      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.writeValue(file, callStack);
    }
  }

  private void export(String snapshotFile) throws IOException, InterruptedException {
    List<String> commandList = new ArrayList<>();
    commandList.add(JPROFILER_11_EXPORT_CMD_OSX);
    commandList.add(snapshotFile);
    commandList.add("CallTree");
    commandList.add("-aggregation=method");
    commandList.add("-viewmode=tree");
    commandList.add("-threadstatus=running");
    commandList.add("-format=xml");
    commandList.add(JPROFILER_CALL_TREE_FILE);
    Executor.executeCommand(commandList);
  }

  private Collection<CallStack<CallStackEntry>> getCallStacks() throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    BufferedReader reader = new BufferedReader(new FileReader(JPROFILER_CALL_TREE_FILE));

    CallStack<CallTreeNode> callStack = new CallStack<>();
    Collection<CallStack<CallStackEntry>> callStacks = new HashSet<>();
    String line;

    while ((line = reader.readLine()) != null) {
      if (line.contains(OPEN_TREE)) {
        callStack = new CallStack<>();
      } else if (line.contains(CLOSE_TREE)) {
        if (callStack.getStack().isEmpty()) {
          throw new RuntimeException("The stack of nodes is empty");
        }
        callStack.getStack().removeFirst();
      } else if (line.contains(OPEN_NODE)) {
        CallTreeNode node =
            xmlMapper.readValue(line + CLOSE_NODE, new TypeReference<CallTreeNode>() {});
        callStack.getStack().addFirst(node);
      } else if (line.contains(CLOSE_NODE)) {
        if (callStack.getStack().peek() == null) {
          throw new UnsupportedOperationException("Handle case");
        } else if (callStack.getStack().peek().getLeaf()) {
          callStacks.add(this.getCallStack(callStack));
        }

        callStack.getStack().removeFirst();
      }
    }
    reader.close();

    return callStacks;
  }

  private CallStack<CallStackEntry> getCallStack(CallStack<CallTreeNode> callStackNodes) {
    CallStack<CallStackEntry> tmp = new CallStack<>();
    for (CallTreeNode node : callStackNodes.getStack()) {
      CallStackEntry entry =
          new CallStackEntry(
              node.getClassName(),
              node.getMethodName(),
              node.getMethodSignature(),
              node.getSelfTime());
      tmp.getStack().addFirst(entry);
    }

    CallStack<CallStackEntry> callStack = new CallStack<>();
    for (CallStackEntry entry : tmp.getStack()) {
      callStack.getStack().addFirst(entry);
    }
    return callStack;
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }

  @Override
  public void writeToFile(Void result) {
    throw new UnsupportedOperationException("Method should not be called");
  }

  @Override
  public Void readFromFile(File file) {
    throw new UnsupportedOperationException("Method should not be called");
  }
}
