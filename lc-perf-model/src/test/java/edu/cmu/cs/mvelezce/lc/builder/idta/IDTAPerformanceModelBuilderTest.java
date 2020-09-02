package edu.cmu.cs.mvelezce.lc.builder.idta;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.multithread.BaseMultithreadAdapter;
import edu.cmu.cs.mvelezce.adapters.performance.BasePerformanceAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.adapters.trivial.BaseTrivialAdapter;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.java.processor.aggregator.sampling.profiler.jprofiler.idta.IDTAPerfAggregatorProcessor;
import edu.cmu.cs.mvelezce.java.results.processed.PerformanceEntry;
import edu.cmu.cs.mvelezce.lc.builder.BasePerformanceModelBuilder;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IDTAPerformanceModelBuilderTest {

  @Test
  public void trivial_real() throws IOException, InterruptedException {
    String programName = BaseTrivialAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.REAL);

    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseTrivialAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.REAL);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void berkeleyDB_real() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseMeasureDiskOrderedScanAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.REAL);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void lucene_real() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseIndexFilesAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.REAL);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void performance_real() throws IOException, InterruptedException {
    String programName = BasePerformanceAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BasePerformanceAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.REAL);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void multithread_user() throws IOException, InterruptedException {
    String programName = BaseMultithreadAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.USER);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseMultithreadAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.USER);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void convert_user() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.USER);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseConvertAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.USER);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void runBenchC_user() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Map<JavaRegion, Partitioning> regionsToPartitions = instrumenter.getProcessedRegionsToData();

    Analysis<Set<PerformanceEntry>> perfAggregatorProcessor =
        new IDTAPerfAggregatorProcessor(programName, BaseExecutor.USER);
    String[] args = new String[0];
    Set<PerformanceEntry> performanceEntries = perfAggregatorProcessor.analyze(args);

    List<String> options = BaseRunBenchCAdapter.getListOfOptions();
    BasePerformanceModelBuilder<Partitioning, Partition> builder =
        new IDTAPerformanceModelBuilder(
            programName, options, regionsToPartitions, performanceEntries, BaseExecutor.USER);

    args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }
}
