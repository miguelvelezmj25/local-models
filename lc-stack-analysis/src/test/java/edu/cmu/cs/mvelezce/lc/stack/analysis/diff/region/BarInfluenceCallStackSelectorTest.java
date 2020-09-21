package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class BarInfluenceCallStackSelectorTest {

  @Test
  public void bar_A_FALSE() throws IOException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    selector.select();
  }
}
