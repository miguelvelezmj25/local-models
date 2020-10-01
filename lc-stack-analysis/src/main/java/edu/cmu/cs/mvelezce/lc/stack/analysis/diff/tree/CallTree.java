package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.tree;

import java.util.HashSet;
import java.util.Set;

public class CallTree {

  private final Set<Node> nodes = new HashSet<>();
  private final Node end = new Node.Builder("end").build();
  private final Node start = new Node.Builder("start").build();

  public CallTree() {
    this.nodes.add(this.end);
    this.nodes.add(this.start);
  }

  public void addNode(Node node) {
    if (this.nodes.contains(node)) {
      throw new UnsupportedOperationException("Node " + node + "is already in the call tree");
    }

    this.nodes.add(node);
  }

  public void addEdge(Node fromNode, Node toNode) {
    if (!this.nodes.contains(fromNode)) {
      throw new RuntimeException("Node " + fromNode + " is not in the call tree");
    }
    if (!this.nodes.contains(toNode)) {
      throw new RuntimeException("Node " + toNode + " is not in the call tree");
    }

    fromNode.addCalleer(toNode);
    toNode.addCallee(fromNode);
  }

  public Set<Node> getNodes() {
    return nodes;
  }

  public Node getEndNode() {
    return end;
  }

  public Node getStartNode() {
    return start;
  }
}
