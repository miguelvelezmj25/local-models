package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import org.junit.Test;

import java.io.IOException;

public class DiffStacksCallStackSelectorTest {

  @Test
  public void foo_A_notA() throws IOException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.DiffStacks", "foo", "(Z)");
    selector.select();
  }

  @Test
  public void getFactors_A_notA() throws IOException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.ExpensiveCalls", "getFactors", "()");
    selector.select();
  }
}
