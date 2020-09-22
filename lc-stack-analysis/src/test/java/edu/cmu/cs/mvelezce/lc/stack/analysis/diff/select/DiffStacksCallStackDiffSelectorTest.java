package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.select;

import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import org.junit.Test;

import java.io.IOException;

public class DiffStacksCallStackDiffSelectorTest {

  @Test
  public void foo_A_FALSE() throws IOException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.DiffStacks", "foo", "(Z)");
    selector.select();
  }
}
