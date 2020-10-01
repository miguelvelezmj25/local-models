package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.CallTree;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree.Node;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CallTreeBuilder {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/tree/java/programs";

  private final CallTree callTree = new CallTree();

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

    return this.callTree;
  }

  public String toDotString() {
    throw new UnsupportedOperationException("implement");
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
