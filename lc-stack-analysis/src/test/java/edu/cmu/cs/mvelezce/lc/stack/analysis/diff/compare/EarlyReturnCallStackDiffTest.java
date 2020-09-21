package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;

public class EarlyReturnCallStackDiffTest {

  @Test
  public void moo_A_FALSE() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    differ.diff();
  }

  @Test
  public void foo_A_FALSE() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    differ.diff();
  }
}
