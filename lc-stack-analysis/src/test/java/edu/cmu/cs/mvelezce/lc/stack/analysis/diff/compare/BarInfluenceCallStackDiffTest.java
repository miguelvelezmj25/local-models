package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class BarInfluenceCallStackDiffTest {

  @Test
  public void bar() throws IOException, DiffException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "A", "!A", "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    differ.diff();
  }
}
