package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class EarlyReturnCallTreeBuilderTest {

  @Test
  public void getFactors_FALSE() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallTreeBuilder builder =
        new CallTreeBuilder(
            programName,
            "FALSE",
            regions,
            "edu.cmu.cs.mvelezce.perf.ExpensiveCalls",
            "getFactors",
            "()");
    builder.buildCallTree();
    System.out.println(builder.toDotString());
  }

  @Test
  public void getFactors_A() throws IOException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallTreeBuilder builder =
        new CallTreeBuilder(
            programName,
            "A",
            regions,
            "edu.cmu.cs.mvelezce.perf.ExpensiveCalls",
            "getFactors",
            "()");
    builder.buildCallTree();
    System.out.println(builder.toDotString());
  }
}
