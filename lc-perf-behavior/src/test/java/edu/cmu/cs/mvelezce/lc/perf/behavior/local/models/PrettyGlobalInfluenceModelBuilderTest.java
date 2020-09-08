package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import org.junit.Test;

import java.io.IOException;

public class PrettyGlobalInfluenceModelBuilderTest {

  @Test
  public void berkeleyDB() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    PrettyGlobalInfluenceModelBuilder builder =
        new PrettyGlobalInfluenceModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    PrettyGlobalInfluenceModelBuilder builder =
        new PrettyGlobalInfluenceModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    PrettyGlobalInfluenceModelBuilder builder =
        new PrettyGlobalInfluenceModelBuilder(programName, BaseExecutor.USER);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    PrettyGlobalInfluenceModelBuilder builder =
        new PrettyGlobalInfluenceModelBuilder(programName, BaseExecutor.USER);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }
}
