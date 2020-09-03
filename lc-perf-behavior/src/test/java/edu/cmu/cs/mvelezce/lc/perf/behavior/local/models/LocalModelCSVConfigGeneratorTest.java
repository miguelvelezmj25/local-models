package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.behavior.AbstractCSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.Berkeley.BerkeleyIDTAPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.Convert.ConvertIDTAPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.Lucene.LuceneIDTAPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.RunBenchC.RunBenchCIDTAPerformanceModel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class LocalModelCSVConfigGeneratorTest {

  @Test
  public void berkeleyDB() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    List<String> options = BaseMeasureDiskOrderedScanAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    model = BerkeleyIDTAPerformanceModel.toBerkeleyIDTAPerformanceModel(model);

    AbstractCSVConfigGenerator generator =
        new LocalModelCSVConfigGenerator(programName, options, model, BaseExecutor.REAL);
    generator.generateCSVFile();
  }

  @Test
  public void lucene() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    List<String> options = BaseIndexFilesAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    model = LuceneIDTAPerformanceModel.toLuceneIDTAPerformanceModel(model);

    AbstractCSVConfigGenerator generator =
        new LocalModelCSVConfigGenerator(programName, options, model, BaseExecutor.REAL);
    generator.generateCSVFile();
  }

  @Test
  public void convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    List<String> options = BaseConvertAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    model = ConvertIDTAPerformanceModel.toConvertIDTAPerformanceModel(model);

    AbstractCSVConfigGenerator generator =
        new LocalModelCSVConfigGenerator(programName, options, model, BaseExecutor.USER);
    generator.generateCSVFile();
  }

  @Test
  public void runBenchC() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    List<String> options = BaseRunBenchCAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    model = RunBenchCIDTAPerformanceModel.toRunBenchCIDTAPerformanceModel(model);

    AbstractCSVConfigGenerator generator =
        new LocalModelCSVConfigGenerator(programName, options, model, BaseExecutor.USER);
    generator.generateCSVFile();
  }
}
