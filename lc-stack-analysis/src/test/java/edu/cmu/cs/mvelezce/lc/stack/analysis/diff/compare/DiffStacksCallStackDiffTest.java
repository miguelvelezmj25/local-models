package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import org.junit.Test;

import java.io.IOException;

public class DiffStacksCallStackDiffTest {

  @Test
  public void foo_A_FALSE() throws IOException, DiffException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "FALSE", "edu.cmu.cs.mvelezce.perf.DiffStacks", "foo", "(Z)");
    differ.diff();
  }
}
