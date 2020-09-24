package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MooInfluenceCallStackDiffTest {

  @Test
  public void moo_A_FALSE_1() throws IOException, DiffException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("89e3c312-c8be-4984-82ef-24df19ce5cb4.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b9bec95b-b62c-4a4a-9fb5-5271ef6f128e.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.MooInfluence",
            "moo",
            "(I)");
    differ.diff();
  }

  @Test
  public void moo_A_FALSE_2() throws IOException, DiffException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("1701c4bf-d152-4721-92bd-2ed349875d21.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("a95a46ea-77fc-4dac-a3ab-925fbeefdd35.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.MooInfluence",
            "moo",
            "(I)");
    differ.diff();
  }
}
