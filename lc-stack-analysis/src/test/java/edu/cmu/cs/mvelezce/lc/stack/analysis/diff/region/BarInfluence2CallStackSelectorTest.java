package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.barInfluence2.BaseBarInfluence2Adapter;
import org.junit.Test;

import java.io.IOException;

public class BarInfluence2CallStackSelectorTest {

  @Test
  public void getFactors_A_notA() throws IOException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.ExpensiveCalls", "getFactors", "()");
    selector.select();
  }

  @Test
  public void bar_A_notA() throws IOException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.BarInfluence2", "bar", "(I)");
    selector.select();
  }
}
