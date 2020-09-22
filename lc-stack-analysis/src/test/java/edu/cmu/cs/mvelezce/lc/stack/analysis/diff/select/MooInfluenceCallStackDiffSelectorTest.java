package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.select;

import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region.CallStackSelector;
import org.junit.Test;

import java.io.IOException;

public class MooInfluenceCallStackDiffSelectorTest {

  @Test
  public void moo_A_FALSE() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.MooInfluence", "moo", "(I)");
    selector.select();
  }
}
