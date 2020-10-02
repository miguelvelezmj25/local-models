package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.lc.adapters.needSlicing.BaseNeedSlicingAdapter;
import org.junit.Test;

import java.io.IOException;

public class NeedSlicingCallStackSelectorTest {

  @Test
  public void getFactors_A_notA() throws IOException {
    String programName = BaseNeedSlicingAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "A", "¬A", "edu.cmu.cs.mvelezce.perf.ExpensiveCalls", "getFactors", "()");
    selector.select();
  }
}
