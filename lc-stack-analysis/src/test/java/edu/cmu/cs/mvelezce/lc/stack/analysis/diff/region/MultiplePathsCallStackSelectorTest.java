package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.multiplePaths.BaseMultiplePathsAdapter;
import org.junit.Test;

import java.io.IOException;

public class MultiplePathsCallStackSelectorTest {

  @Test
  public void getFactors_A_notA() throws IOException {
    String programName = BaseMultiplePathsAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.ExpensiveCalls", "getFactors", "()");
    selector.select();
  }
}
