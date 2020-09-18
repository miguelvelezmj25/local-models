package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;

public class EarlyReturnCallStackSelectorTest {

  @Test
  public void moo() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    selector.select();
  }

  @Test
  public void foo() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    selector.select();
  }
}
