package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class MooInfluenceCallStackDiffTest {

  @Test
  public void moo() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.MooInfluence", "moo", "(I)");
    differ.diff();
  }
}
