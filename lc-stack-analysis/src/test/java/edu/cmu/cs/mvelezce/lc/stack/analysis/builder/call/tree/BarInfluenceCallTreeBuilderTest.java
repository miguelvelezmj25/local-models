package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class BarInfluenceCallTreeBuilderTest {

  @Test
  public void bar_FALSE() throws IOException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallTreeBuilder builder =
        new CallTreeBuilder(
            programName, "FALSE", regions, "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    builder.buildCallTree();
  }

  @Test
  public void bar_A() throws IOException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    CallTreeBuilder builder =
        new CallTreeBuilder(
            programName, "A", regions, "edu.cmu.cs.mvelezce.perf.BarInfluence", "bar", "(I)");
    builder.buildCallTree();
  }
}
