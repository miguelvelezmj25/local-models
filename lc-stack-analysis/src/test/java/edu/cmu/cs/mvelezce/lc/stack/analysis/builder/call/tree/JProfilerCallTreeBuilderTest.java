package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
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
}
