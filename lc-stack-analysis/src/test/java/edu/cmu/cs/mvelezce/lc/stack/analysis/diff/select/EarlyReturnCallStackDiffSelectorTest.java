package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.select;

import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;

public class EarlyReturnCallStackDiffSelectorTest {

  @Test
  public void moo_A_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    selector.select();
  }

  @Test
  public void foo_A_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    selector.select();
  }
}
