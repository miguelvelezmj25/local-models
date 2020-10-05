package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.tree;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
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
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class TreeDiffer {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/tree/diff/java/programs";
  public static final String UNIQUE_TRACE_COLOR = "#FF9E9B";
  public static final String DIFF_TIME_COLOR = "#FFDC7D";
  public static final String OPTION_VALUE_1_COLOR = "blue";
  public static final String OPTION_VALUE_2_COLOR = "red";

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

  public void diff() throws IOException, DiffException {
    CallTree callTree1 = this.callTreeBuilder1.buildCallTree();
    CallTree callTree2 = this.callTreeBuilder2.buildCallTree();
    this.buildDiffTree(callTree1);
    this.buildDiffTree(callTree2);
    Set<Pair<CallTree, CallTree>> equalStacks = this.getEqualStacks();
    this.saveGraph(equalStacks);
  }

  private Set<Pair<CallTree, CallTree>> getEqualStacks() throws DiffException {
    Set<Pair<CallTree, CallTree>> equalStacks = new HashSet<>();
    Set<CallTree> equalCallTrees = new HashSet<>();
    for (CallTree callTree1 : this.callTreeBuilder1.getAllCallTrees2Times().keySet()) {
      if (equalCallTrees.contains(callTree1)) {
        continue;
      }

      List<String> callStack1 = callTree1.getCallStack();
      for (CallTree callTree2 : this.callTreeBuilder2.getAllCallTrees2Times().keySet()) {
        if (equalCallTrees.contains(callTree2)) {
          continue;
        }

        List<String> callStack2 = callTree2.getCallStack();
        if (!this.sameCallStackRoot(callStack1, callStack2)) {
          continue;
        }

        List<DiffRow> diffs = this.diffCallStacks(callStack1, callStack2);
        boolean equalTraces = true;
        for (DiffRow diff : diffs) {
          if (!diff.getTag().equals(DiffRow.Tag.EQUAL)) {
            equalTraces = false;
            break;
          }
        }

        if (equalTraces) {
          equalStacks.add(Pair.of(callTree1, callTree2));
          equalCallTrees.add(callTree1);
          equalCallTrees.add(callTree2);
        }
      }
    }

    return equalStacks;
  }

  private boolean sameCallStackRoot(List<String> lines1, List<String> lines2) {
    String root1 = lines1.get(lines1.size() - 1).split(",")[0];
    String root2 = lines2.get(lines2.size() - 1).split(",")[0];
    return root1.equals(root2);
  }

  private List<DiffRow> diffCallStacks(List<String> callStack1, List<String> callStack2)
      throws DiffException {
    DiffRowGenerator generator =
        DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            //            .oldTag(f -> OLD_TAG)
            //            .newTag(f -> NEW_TAG)
            .build();

    return generator.generateDiffRows(callStack1, callStack2);
  }

  public void saveGraph(Set<Pair<CallTree, CallTree>> equalStacks) throws IOException {
    this.clearRootDir();

    Graphviz.useEngine(new GraphvizCmdLineEngine());
    String dotString = this.toDotString(equalStacks);
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

  public String toDotString(Set<Pair<CallTree, CallTree>> equalStacks) {
    StringBuilder dotString =
        new StringBuilder("digraph " + this.callTreeBuilder1.getMethodName() + " {\n");
    Set<Node> nodes = this.diffTree.getNodes();
    for (Node node : nodes) {
      dotString.append("\"");
      dotString.append(node.getShortMethodName());
      dotString.append("\"");
      dotString.append(" [");
      dotString.append(" shape=box ");
      if (node.isRegion()) {
        dotString.append("style=filled fillcolor=\"");
        dotString.append(CallTreeBuilder.REGION_NODE_COLOR);
        dotString.append("\"");
      } else if (node.isSpecial() && node.equals(this.diffTree.getEndNode())) {
        String totalTime1 =
            this.getCallStackTime(this.callTreeBuilder1.getAllCallTrees2Times().values());
        String totalTime2 =
            this.getCallStackTime(this.callTreeBuilder2.getAllCallTrees2Times().values());

        dotString.append("shape=plain label=<");
        dotString.append("<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">");
        dotString.append(
            "<TR><TD ALIGN=\"CENTER\" BGCOLOR=\"white\" COLSPAN=\"2\"><FONT color=\"black\">");
        dotString.append(node.getId());
        dotString.append("</FONT></TD></TR>");
        dotString.append("<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"white\"><FONT color=\"");
        dotString.append(OPTION_VALUE_1_COLOR);
        dotString.append("\">");
        dotString.append(DECIMAL_FORMAT.format(Double.parseDouble(totalTime1)));
        dotString.append("s " + this.callTreeBuilder1.getOptionValue() + "</FONT></TD>");
        dotString.append("<TD ALIGN=\"LEFT\" BGCOLOR=\"white\"><FONT color=\"");
        dotString.append(OPTION_VALUE_2_COLOR);
        dotString.append("\">");
        dotString.append(DECIMAL_FORMAT.format(Double.parseDouble(totalTime2)));
        dotString.append("s " + this.callTreeBuilder2.getOptionValue() + "</FONT></TD></TR>");
        dotString.append("</TABLE>>");
      } else if (node.isSpecial() && node.equals(this.diffTree.getStartNode())) {
        dotString.append("shape=plain label=<");
        dotString.append("<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">");
        dotString.append(
            "<TR><TD ALIGN=\"CENTER\" BGCOLOR=\"white\" COLSPAN=\"2\"><FONT color=\"black\">");
        dotString.append(node.getId());
        dotString.append("</FONT></TD></TR>");
        dotString.append("<TR><TD ALIGN=\"LEFT\" BGCOLOR=\"white\"><FONT color=\"");
        dotString.append(OPTION_VALUE_1_COLOR);
        dotString.append("\">");
        dotString.append(this.callTreeBuilder1.getAllCallTrees2Times().size());
        dotString.append(" callstacks " + this.callTreeBuilder1.getOptionValue() + "</FONT></TD>");
        dotString.append("<TD ALIGN=\"LEFT\" BGCOLOR=\"white\"><FONT color=\"");
        dotString.append(OPTION_VALUE_2_COLOR);
        dotString.append("\">");
        dotString.append(this.callTreeBuilder2.getAllCallTrees2Times().size());
        dotString.append(
            " callstacks " + this.callTreeBuilder2.getOptionValue() + "</FONT></TD></TR>");
        dotString.append("</TABLE>>");
      }

      dotString.append("]");

      dotString.append("\n");
    }

    Set<CallTree> processedCallTrees = new HashSet<>();
    for (CallTree callTree : this.callTreeBuilder1.getAllCallTrees2Times().keySet()) {
      if (processedCallTrees.contains(callTree)) {
        continue;
      }
      dotString.append(
          this.processEdges(
              callTree,
              equalStacks,
              this.callTreeBuilder1.getOptionValue(),
              this.callTreeBuilder2.getOptionValue(),
              OPTION_VALUE_1_COLOR,
              OPTION_VALUE_2_COLOR,
              processedCallTrees));
    }

    for (CallTree callTree : this.callTreeBuilder2.getAllCallTrees2Times().keySet()) {
      if (processedCallTrees.contains(callTree)) {
        continue;
      }
      dotString.append(
          this.processEdges(
              callTree,
              equalStacks,
              this.callTreeBuilder2.getOptionValue(),
              this.callTreeBuilder1.getOptionValue(),
              OPTION_VALUE_2_COLOR,
              OPTION_VALUE_1_COLOR,
              processedCallTrees));
    }

    dotString.append("}");

    return dotString.toString();
  }

  private String getCallStackTime(Collection<Double> times) {
    double totalTime = 0;
    for (Double time : times) {
      totalTime += time;
    }

    return DECIMAL_FORMAT.format(totalTime);
  }

  private String processEdges(
      CallTree callTree,
      Set<Pair<CallTree, CallTree>> equalStacks,
      String optionValue1,
      String optionValue2,
      String optionValueColor1,
      String optionValueColor2,
      Set<CallTree> processedCallTrees) {
    StringBuilder dotString = new StringBuilder();
    CallTree equalCallTree = this.getEqualCallTree(callTree, equalStacks);
    Map<Node, Node> equalNodes = this.getEqualNodes(callTree, equalCallTree);
    String edgeColor = CallTreeBuilder.getRandomColor(callTree.hashCode());
    for (Node node : callTree.getNodes()) {
      for (Node calleer : node.getCallers()) {
        UUID timeInfoNode = null;
        if (!calleer.getTime().isEmpty()) {
          timeInfoNode = UUID.randomUUID();

          int maxOptionLength = Math.max(optionValue1.length(), optionValue2.length());

          int maxLength = DECIMAL_FORMAT.format(calleer.getTimeDouble()).length();
          if (equalCallTree != null) {
            maxLength =
                Math.max(
                    maxLength,
                    DECIMAL_FORMAT.format(equalNodes.get(calleer).getTimeDouble()).length());
          }

          String bgColor = "white";
          if (equalCallTree == null) {
            bgColor = UNIQUE_TRACE_COLOR;
          } else {
            double nodeTime = calleer.getTimeDouble();
            double equalNodeTime = equalNodes.get(calleer).getTimeDouble();
            if (Math.abs(nodeTime - equalNodeTime) > 0.1) {
              bgColor = DIFF_TIME_COLOR;
            }
          }

          dotString.append("\"");
          dotString.append(timeInfoNode);
          dotString.append("\"");
          dotString.append(" [");
          dotString.append(
              " shape=plain label=<\n"
                  + "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"
                  + "  <TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
          dotString.append(bgColor);
          dotString.append("\" STYLE=\"dashed\"><FONT color=\"");
          dotString.append(optionValueColor1);
          dotString.append("\">");
          dotString.append(
              String.format("%" + maxLength + "s", DECIMAL_FORMAT.format(calleer.getTimeDouble())));
          dotString.append("s ");
          dotString.append(String.format("%" + maxOptionLength + "s", optionValue1));
          dotString.append("</FONT></TD></TR>\n");
          if (equalCallTree != null) {
            dotString.append("  <TR><TD ALIGN=\"LEFT\" BGCOLOR=\"");
            dotString.append(bgColor);
            dotString.append("\" STYLE=\"dashed\"><FONT color=\"");
            dotString.append(optionValueColor2);
            dotString.append("\">");
            dotString.append(
                String.format(
                    "%" + maxLength + "s",
                    DECIMAL_FORMAT.format(equalNodes.get(calleer).getTimeDouble())));
            dotString.append("s ");
            dotString.append(String.format("%" + maxOptionLength + "s", optionValue2));
            dotString.append("</FONT></TD></TR>\n");
          }

          dotString.append("</TABLE>> ];\n");
        }

        if (timeInfoNode == null) {
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
          dotString.append("];");
        } else {
          dotString.append("\"");
          dotString.append(node.getShortMethodName());
          dotString.append("\"");
          dotString.append(" -> ");
          dotString.append("\"");
          dotString.append(timeInfoNode);
          dotString.append("\"");
          dotString.append(" [");
          dotString.append(" dir=back ");
          dotString.append(" color=\"");
          dotString.append(edgeColor);
          dotString.append("\" ");
          dotString.append("];\n");

          dotString.append("\"");
          dotString.append(timeInfoNode);
          dotString.append("\"");
          dotString.append(" -> ");
          dotString.append("\"");
          dotString.append(calleer.getShortMethodName());
          dotString.append("\"");
          dotString.append(" [");
          dotString.append(" style=dashed dir=back ");
          dotString.append(" color=\"");
          dotString.append(edgeColor);
          dotString.append("\" ");
          dotString.append("];");
        }

        dotString.append("\n");
      }
    }

    processedCallTrees.add(callTree);
    if (equalCallTree != null) {
      processedCallTrees.add(equalCallTree);
    }

    return dotString.toString();
  }

  private Map<Node, Node> getEqualNodes(CallTree callTree, @Nullable CallTree equalCallTree) {
    if (equalCallTree == null) {
      return new HashMap<>();
    }
    Map<Node, Node> equalNodes = new HashMap<>();
    for (Node node : callTree.getNodes()) {
      if (node.equals(callTree.getStartNode())) {
        equalNodes.put(node, equalCallTree.getStartNode());
      } else if (node.equals(callTree.getEndNode())) {
        equalNodes.put(node, equalCallTree.getEndNode());
      } else {
        for (Node equalNode : equalCallTree.getNodes()) {
          if (equalNodes.containsValue(equalNode)) {
            continue;
          }

          if (node.getId().equals(equalNode.getId())) {
            equalNodes.put(node, equalNode);
          }
        }
      }
    }

    if (equalNodes.size() != callTree.getNodes().size()
        || equalNodes.size() != equalCallTree.getNodes().size()) {
      throw new RuntimeException("There are some nodes that we could not find their equal nodes");
    }

    return equalNodes;
  }

  @Nullable
  private CallTree getEqualCallTree(CallTree callTree, Set<Pair<CallTree, CallTree>> equalStacks) {
    for (Pair<CallTree, CallTree> pair : equalStacks) {
      if (pair.getLeft().equals(callTree)) {
        return pair.getRight();
      }

      if (pair.getRight().equals(callTree)) {
        return pair.getLeft();
      }
    }

    return null;
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
