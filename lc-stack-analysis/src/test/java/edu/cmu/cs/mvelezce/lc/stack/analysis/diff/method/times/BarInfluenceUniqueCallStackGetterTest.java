package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import org.junit.Test;

public class BarInfluenceUniqueCallStackGetterTest {

  @Test
  public void bar_FALSE() {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "FALSE", "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    getter.getUniqueCallStacks();
  }

  @Test
  public void bar_A() {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName, "A", "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    getter.getUniqueCallStacks();
  }
}
