package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import org.junit.Test;

public class DiffStacksUniqueCallStackGetterTest {

  @Test
  public void foo_FALSE() {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "FALSE", "edu.cmu.cs.mvelezce.perf.DiffStacks", "foo", "(Z)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void foo_A() {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "A", "edu.cmu.cs.mvelezce.perf.DiffStacks", "foo", "(Z)");
    getter.getUniqueCallStacks();
  }
}
