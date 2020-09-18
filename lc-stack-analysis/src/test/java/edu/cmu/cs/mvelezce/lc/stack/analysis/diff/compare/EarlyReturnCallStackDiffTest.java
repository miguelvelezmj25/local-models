package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;

public class EarlyReturnCallStackDiffTest {

  @Test
  public void moo() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    differ.diff();
  }

  @Test
  public void foo() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    differ.diff();
  }
}
