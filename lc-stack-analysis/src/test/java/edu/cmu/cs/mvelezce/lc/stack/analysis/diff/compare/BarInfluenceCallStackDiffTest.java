package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class BarInfluenceCallStackDiffTest {

  @Test
  public void bar_A_FALSE() throws IOException, DiffException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    String option1 = "FALSE";
    String option2 = "A";

    CallStackDiff differ =
        new CallStackDiff(
            programName, option1, option2, "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    differ.diff();
  }
}
