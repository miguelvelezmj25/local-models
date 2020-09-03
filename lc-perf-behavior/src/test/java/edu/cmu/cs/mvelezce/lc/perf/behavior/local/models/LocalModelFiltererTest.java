package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.analysis.BaseAnalysis;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.model.builder.idta.IDTAPerformanceModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import org.junit.Test;

import java.io.IOException;

public class LocalModelFiltererTest {

  @Test
  public void MeasureDiskOrderedScan_real() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new IDTAPerformanceModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).model(model).build();
    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    filterer.analyze(args);
  }

  @Test
  public void Convert_user() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new IDTAPerformanceModelBuilder(programName, BaseExecutor.USER);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).model(model).build();
    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    filterer.analyze(args);
  }

  @Test
  public void IndexFiles_real() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new IDTAPerformanceModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).model(model).build();
    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    filterer.analyze(args);
  }

  @Test
  public void RunBenchC_user() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new IDTAPerformanceModelBuilder(programName, BaseExecutor.USER);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).model(model).build();
    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    filterer.analyze(args);
  }
}
