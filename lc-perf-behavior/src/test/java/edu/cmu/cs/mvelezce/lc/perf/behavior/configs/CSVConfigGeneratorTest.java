package edu.cmu.cs.mvelezce.lc.perf.behavior.configs;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.analysis.BaseAnalysis;
import edu.cmu.cs.mvelezce.exhaustive.builder.gt.GroundTruthExhaustiveModelBuilder;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.model.PerformanceModel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CSVConfigGeneratorTest {

  @Test
  public void berkeleyDB() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    List<String> options = BaseMeasureDiskOrderedScanAdapter.getListOfOptions();
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new GroundTruthExhaustiveModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    CSVConfigGenerator generator =
        new CSVConfigGenerator(programName, options, model, BaseExecutor.REAL);
    generator.generateCSVFile();
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    List<String> options = BaseIndexFilesAdapter.getListOfOptions();
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new GroundTruthExhaustiveModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    CSVConfigGenerator generator =
        new CSVConfigGenerator(programName, options, model, BaseExecutor.REAL);
    generator.generateCSVFile();
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    List<String> options = BaseConvertAdapter.getListOfOptions();
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new GroundTruthExhaustiveModelBuilder(programName, BaseExecutor.USER);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    CSVConfigGenerator generator =
        new CSVConfigGenerator(programName, options, model, BaseExecutor.USER);
    generator.generateCSVFile();
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    List<String> options = BaseRunBenchCAdapter.getListOfOptions();
    BaseAnalysis<PerformanceModel<Partition>> builder =
        new GroundTruthExhaustiveModelBuilder(programName, BaseExecutor.REAL);
    String[] args = new String[0];
    PerformanceModel<Partition> model = builder.analyze(args);

    CSVConfigGenerator generator =
        new CSVConfigGenerator(programName, options, model, BaseExecutor.REAL);
    generator.generateCSVFile();
  }
}
