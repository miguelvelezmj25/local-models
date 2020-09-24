package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BarInfluenceCallStackDiffTest {

  @Test
  public void bar_A_FALSE() throws IOException, DiffException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("829201a0-0566-4972-9970-fe001e2b640f.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("2df0a868-c84b-4b0b-9a14-78ae068c8c62.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.BarInfluence",
            "bar",
            "(I)");
    differ.diff();
  }
}
