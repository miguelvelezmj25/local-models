package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DiffStacksCallStackDiffTest {

  @Test
  public void foo_A_FALSE() throws IOException, DiffException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("8785e4f1-b759-4623-8ab8-5faad81267bb.csv");
    stacks1.add("d00dfdcc-382f-4286-a493-51c4fde7fdb4.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("39ada003-1a1b-4cca-8fa1-1f9635930980.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.DiffStacks",
            "foo",
            "(Z)");
    differ.diff();
  }
}
