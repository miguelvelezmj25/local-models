package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.tree;

import edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree.CallTreeBuilder;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.CallTree;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.DiffTree;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.Node;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.utils.config.Options;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizCmdLineEngine;
import guru.nidi.graphviz.engine.Rasterizer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TreeDiffer {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/tree/diff/java/programs";

  private final CallTreeBuilder callTreeBuilder1;
  private final CallTreeBuilder callTreeBuilder2;
  private final DiffTree diffTree = new DiffTree();

  public TreeDiffer(
      String programName,
      String optionValue1,
      String optionValue2,
      Set<JavaRegion> regions,
      String className,
      String methodName,
      String methodSignature) {
    this.callTreeBuilder1 =
        new CallTreeBuilder(
            programName, optionValue1, regions, className, methodName, methodSignature);
    this.callTreeBuilder2 =
        new CallTreeBuilder(
            programName, optionValue2, regions, className, methodName, methodSignature);
  }

  public void diff() throws IOException {
    CallTree callTree1 = this.callTreeBuilder1.buildCallTree();
    CallTree callTree2 = this.callTreeBuilder2.buildCallTree();
    this.buildDiffTree(callTree1);
    this.buildDiffTree(callTree2);
    this.saveGraph();
  }

  public void saveGraph() throws IOException {
    this.clearRootDir();

    Graphviz.useEngine(new GraphvizCmdLineEngine());
    String dotString = this.toDotString();
    Graphviz graphviz = Graphviz.fromString(dotString);
    File rootDir =
        new File(
            OUTPUT_DIR
                + "/"
                + this.callTreeBuilder1.getProgramName()
                + "/"
                + this.callTreeBuilder1.getMethodName()
                + this.callTreeBuilder1.getMethodSignature()
                + "/"
                + this.callTreeBuilder1.getOptionValue()
                + " - "
                + this.callTreeBuilder2.getOptionValue());
    graphviz
        .basedir(rootDir)
        .rasterize(Rasterizer.builtIn("pdf"))
        .toFile(new File(rootDir, this.callTreeBuilder1.getMethodName()));
  }

  private void clearRootDir() throws IOException {
    File rootDit =
        new File(
            OUTPUT_DIR
                + "/"
                + this.callTreeBuilder1.getProgramName()
                + "/"
                + this.callTreeBuilder1.getMethodName()
                + this.callTreeBuilder1.getMethodSignature()
                + "/"
                + this.callTreeBuilder1.getOptionValue()
                + " - "
                + this.callTreeBuilder2.getOptionValue());
    if (rootDit.exists()) {
      FileUtils.cleanDirectory(rootDit);
    }
  }

  private void buildDiffTree(CallTree callTree) {
    Map<Node, Node> callNodes2DiffNodes = this.getEquivNodes(this.diffTree, callTree);
    for (Node node : callNodes2DiffNodes.values()) {
      if (node.isSpecial()) {
        continue;
      }
      this.diffTree.addNode(node);
    }
    for (Node callNode : callTree.getNodes()) {
      Node diffNode = callNodes2DiffNodes.get(callNode);
      for (Node calleer : callNode.getCallers()) {
        this.diffTree.addEdge(callNodes2DiffNodes.get(calleer), diffNode);
      }
    }
  }

  public String toDotString() {
    StringBuilder dotString =
        new StringBuilder("digraph " + this.callTreeBuilder1.getMethodName() + " {\n");
    Set<Node> nodes = this.diffTree.getNodes();
    for (Node node : nodes) {
      dotString.append("\"");
      dotString.append(node.getShortMethodName());
      dotString.append("\"");
      dotString.append(" [");
      dotString.append(" shape=record ");
      if (node.isRegion()) {
        dotString.append("style=filled, fillcolor=\"");
        dotString.append(CallTreeBuilder.REGION_NODE_COLOR);
        dotString.append("\"");
      }
      dotString.append("]");

      dotString.append("\n");
    }

    for (CallTree callTree : this.callTreeBuilder1.getAllCallTrees()) {
      dotString.append(this.processEdges(callTree, this.callTreeBuilder1.getOptionValue()));
    }
    for (CallTree callTree : this.callTreeBuilder2.getAllCallTrees()) {
      dotString.append(this.processEdges(callTree, this.callTreeBuilder2.getOptionValue()));
    }

    dotString.append("}");

    return dotString.toString();
  }

  private String processEdges(CallTree callTree, String optionValue) {
    StringBuilder dotString = new StringBuilder();
    String edgeColor = CallTreeBuilder.getRandomColor(callTree.hashCode());
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
          dotString.append(optionValue);
          dotString.append("\" ");
        }

        dotString.append("];");
        dotString.append("\n");
      }
    }
    return dotString.toString();
  }

  private Map<Node, Node> getEquivNodes(DiffTree diffTree, CallTree callTree) {
    Map<Node, Node> callNodes2DiffNodes = new HashMap<>();
    for (Node callNode : callTree.getNodes()) {
      if (callNode.equals(callTree.getEndNode())) {
        callNodes2DiffNodes.put(callNode, diffTree.getEndNode());
      } else if (callNode.equals(callTree.getStartNode())) {
        callNodes2DiffNodes.put(callNode, diffTree.getStartNode());
      } else {
        Node diffNode = Node.from(callNode);
        callNodes2DiffNodes.put(callNode, diffNode);
      }
    }
    return callNodes2DiffNodes;
  }
}
