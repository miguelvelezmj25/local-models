package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class JProfilerCallTreeBuilderTest {

  @Test
  public void BarInfluence() throws IOException, InterruptedException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder = new JProfilerCallTreeBuilder(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void DiffStacks() throws IOException, InterruptedException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder = new JProfilerCallTreeBuilder(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void EarlyReturn() throws IOException, InterruptedException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder = new JProfilerCallTreeBuilder(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void MooInfluence() throws IOException, InterruptedException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder = new JProfilerCallTreeBuilder(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }
}
