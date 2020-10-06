package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.tree;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class BerkeleyTreeDifferTest {

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_notSEQUENTIAL() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    String option1 = "¬SEQUENTIAL";
    String option2 = "SEQUENTIAL";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void log_SEQUENTIAL_notSEQUENTIAL() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    String option1 = "¬SEQUENTIAL";
    String option2 = "SEQUENTIAL";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }
}
