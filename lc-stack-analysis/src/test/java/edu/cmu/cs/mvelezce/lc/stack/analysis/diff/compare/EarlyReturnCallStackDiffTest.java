package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EarlyReturnCallStackDiffTest {

  @Test
  public void moo_A_FALSE() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("8d1fe1e7-a3f0-49bd-bf0e-4a69b79a918f.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.EarlyReturn",
            "moo",
            "(Z)");
    differ.diff();
  }

  @Test
  public void foo_A_FALSE() throws IOException, DiffException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;

    String option1 = "A";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("4157206e-75b9-4e9b-8fe4-3f37efd15c90.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("ad3b320a-83f4-4708-b536-5c89a98ea124.csv");
    stacks2.add("ebe075ea-47fd-418d-a251-91facd4522d1.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "edu.cmu.cs.mvelezce.perf.EarlyReturn",
            "foo",
            "(Z)");
    differ.diff();
  }
}
