package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;

public class EarlyReturnCallStackSelectorTest {

  @Test
  public void getFactors_A_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "A",
            "FALSE",
            "edu.cmu.cs.mvelezce.perf.ExpensiveCalls",
            "getFactors",
            "()");
    selector.select();
  }

  @Test
  public void moo_A_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    selector.select();
  }

  @Test
  public void foo_A_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    selector.select();
  }
}
