package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

public class MooInfluenceUniqueCallStackGetterTest {

  @Test
  public void moo_FALSE() {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "FALSE", "edu.cmu.cs.mvelezce.perf.MooInfluence", "moo", "(I)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void moo_A() {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "A", "edu.cmu.cs.mvelezce.perf.MooInfluence", "moo", "(I)");
    getter.getUniqueCallStacks();
  }
}
