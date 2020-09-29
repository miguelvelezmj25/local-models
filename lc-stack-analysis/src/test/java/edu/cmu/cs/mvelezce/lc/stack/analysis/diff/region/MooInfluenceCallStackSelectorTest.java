package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class MooInfluenceCallStackSelectorTest {

  @Test
  public void getFactors_A_FALSE() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
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
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.MooInfluence", "moo", "(I)");
    selector.select();
  }
}
