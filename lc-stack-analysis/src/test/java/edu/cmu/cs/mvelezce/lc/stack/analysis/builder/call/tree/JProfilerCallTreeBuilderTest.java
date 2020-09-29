package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence2.BaseBarInfluence2Adapter;
import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.dummyRegion.BaseDummyRegionAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.multiplePaths.BaseMultiplePathsAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.needSlicing.BaseNeedSlicingAdapter;
import org.junit.Test;

import java.io.IOException;

public class JProfilerCallTreeBuilderTest {

  @Test
  public void BarInfluence() throws IOException, InterruptedException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void BarInfluence2() throws IOException, InterruptedException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void DiffStacks() throws IOException, InterruptedException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void DummyRegion() throws IOException, InterruptedException {
    String programName = BaseDummyRegionAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void EarlyReturn() throws IOException, InterruptedException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void MooInfluence() throws IOException, InterruptedException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void MultiplePaths() throws IOException, InterruptedException {
    String programName = BaseMultiplePathsAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void NeedSlicing() throws IOException, InterruptedException {
    String programName = BaseNeedSlicingAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void Convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.RUNNING_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void MeasureDiskOrderedScan() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void RunBenchC() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.RUNNING_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void IndesFiles() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    JProfilerCallTreeBuilder builder =
        new JProfilerCallTreeBuilder(programName, JProfilerCallTreeBuilder.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }
}
