package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.tree;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class BarInfluenceTreeDifferTest {

  @Test
  public void bar_A_FALSE() throws IOException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    String option1 = "FALSE";
    String option2 = "A";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "edu.cmu.cs.mvelezce.perf.BarInfluence",
            "bar",
            "(I)");
    differ.diff();
  }
}
