package edu.cmu.cs.mvelezce.lc.perf.behavior.scripts.matlab.configs.local.models.local;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.behavior.local.models.LocalModelFilterer;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class PlotLocalModelsPerfBehaviorTest {

  @Test
  public void berkeleyDB() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    PlotLocalModelsPerfBehavior plotter =
        new PlotLocalModelsPerfBehavior(programName, BaseExecutor.REAL, relevantLocalModels);
    plotter.generatePlotScript();
  }

  @Test
  public void lucene() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    PlotLocalModelsPerfBehavior plotter =
        new PlotLocalModelsPerfBehavior(programName, BaseExecutor.REAL, relevantLocalModels);
    plotter.generatePlotScript();
  }

  @Test
  public void convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    PlotLocalModelsPerfBehavior plotter =
        new PlotLocalModelsPerfBehavior(programName, BaseExecutor.USER, relevantLocalModels);
    plotter.generatePlotScript();
  }

  @Test
  public void runBenchC() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    PlotLocalModelsPerfBehavior plotter =
        new PlotLocalModelsPerfBehavior(programName, BaseExecutor.USER, relevantLocalModels);
    plotter.generatePlotScript();
  }
}
