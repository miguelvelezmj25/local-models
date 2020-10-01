package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence2.BaseBarInfluence2Adapter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class BarInfluence2CallStackDiffTest {

  @Test
  public void getFactors_A_FALSE() throws IOException, DiffException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    String option1 = "FALSE";
    String option2 = "A";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            option1,
            option2,
            regions,
            "edu.cmu.cs.mvelezce.perf.ExpensiveCalls",
            "getFactors",
            "()");
    differ.diff();
  }

  @Test
  public void bar_A_FALSE() throws IOException, DiffException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    String option1 = "FALSE";
    String option2 = "A";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            option1,
            option2,
            regions,
            "edu.cmu.cs.mvelezce.perf.BarInfluence2",
            "bar",
            "(I)");
    differ.diff();
  }
}