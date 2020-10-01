package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.CallTree;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.Node;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CallTreeBuilder {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/tree/java/programs";

  private static final String REGION_NODE_COLOR = "#00dcff";
  private static final Random RANDOM_EDGE_COLOR = new Random(25);

  private final CallTree callTree = new CallTree();
  private final Set<CallTree> allCallTrees = new HashSet<>();

  private final String programName;
  private final String optionValue;
  private final Set<JavaRegion> regions;
  private final String className;
  private final String methodName;
  private final String methodSignature;

  public CallTreeBuilder(
      String programName,
      String optionValue,
      Set<JavaRegion> regions,
      String className,
      String methodName,
      String methodSignature) {
    this.programName = programName;
    this.optionValue = optionValue;
    this.regions = regions;
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public CallTree buildCallTree() throws IOException {
    if (this.callTree.getNodes().size() > 2) {
      return this.callTree;
    }

    Collection<File> prettyCallStacks = this.getPrettyCallStacks(this.optionValue);
    this.buildAllCallTrees(prettyCallStacks);
    this.buildCallTree(prettyCallStacks);
    return this.callTree;
  }

  private void buildCallTree(Collection<File> prettyCallStacks) throws IOException {
    for (File prettyCallStack : prettyCallStacks) {
      List<String> entries = FileUtils.readLines(prettyCallStack, (String) null);
      Node currentNode = this.callTree.getEndNode();
      for (String entry : entries) {
        Node node = Node.from(entry, this.regions);
        this.callTree.addNode(node);
        this.callTree.addEdge(currentNode, node);
        currentNode = node;
      }
      this.callTree.addEdge(currentNode, this.callTree.getStartNode());
    }
  }

  private void buildAllCallTrees(Collection<File> prettyCallStacks) throws IOException {
    for (File prettyCallStack : prettyCallStacks) {
      CallTree callTree = new CallTree();
      List<String> entries = FileUtils.readLines(prettyCallStack, (String) null);
      Node currentNode = callTree.getEndNode();
      for (String entry : entries) {
        Node node = Node.from(entry, this.regions);
        callTree.addNode(node);
        callTree.addEdge(currentNode, node);
        currentNode = node;
      }
      callTree.addEdge(currentNode, callTree.getStartNode());
      this.allCallTrees.add(callTree);
    }
  }

  public String toDotString() {
    StringBuilder dotString = new StringBuilder("digraph " + this.methodName + " {\n");
    dotString.append("node [shape=record];\n");

    Set<Node> nodes = this.callTree.getNodes();
    for (Node node : nodes) {
      dotString.append("\"");
      dotString.append(node.getShortMethodName());
      dotString.append("\"");

      if (node.isRegion()) {
        dotString.append("[");
        dotString.append("style=filled, fillcolor=\"");
        dotString.append(REGION_NODE_COLOR);
        dotString.append("\"");
        dotString.append("]");
      }

      dotString.append("\n");
    }

    for (CallTree callTree : this.allCallTrees) {
      String edgeColor = getRandomColor();
      for (Node node : callTree.getNodes()) {
        for (Node calleer : node.getCallers()) {
          dotString.append("\"");
          dotString.append(node.getShortMethodName());
          dotString.append("\"");
          dotString.append(" -> ");
          dotString.append("\"");
          dotString.append(calleer.getShortMethodName());
          dotString.append("\"");
          dotString.append(" [");
          dotString.append(" dir=back ");
          dotString.append(" color=\"");
          dotString.append(edgeColor);
          dotString.append("\" ");

          if (!calleer.getTime().isEmpty()) {
            dotString.append(" label=\"  ");
            dotString.append(calleer.getTime());
            dotString.append("s ");
            dotString.append(this.optionValue);
            dotString.append("\" ");
          }

          dotString.append("];");
          dotString.append("\n");
        }
      }
    }

    dotString.append("}");

    return dotString.toString();
  }

  private String getRandomColor() {
    int nextInt = RANDOM_EDGE_COLOR.nextInt(0xffffff + 1);
    return String.format("#%06x", nextInt);
  }

  public String saveGraph() {
    throw new UnsupportedOperationException("Implement");
  }

  private Collection<File> getPrettyCallStacks(String optionValue) {
    File dir =
        new File(
            CallStackSelector.OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.methodName
                + this.methodSignature
                + "/"
                + optionValue);
    return FileUtils.listFiles(dir, new String[] {"csv"}, false);
  }
}
