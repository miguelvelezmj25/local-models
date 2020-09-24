package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import org.junit.Test;

public class EarlyReturnUniqueCallStackGetterTest {

  @Test
  public void moo_A() {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void moo_FALSE() {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "moo", "(Z)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void foo_A() {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "A", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void foo_FALSE() {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "FALSE", "edu.cmu.cs.mvelezce.perf.EarlyReturn", "foo", "(Z)");
    getter.getUniqueCallStacks();
  }
}
