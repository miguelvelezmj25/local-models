package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.compress.BaseCompression;
import edu.cmu.cs.mvelezce.compress.gt.GTCompression;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.behavior.AbstractCSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class GlobalModelCSVConfigGeneratorTest {

  @Test
  public void berkeleyDB() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    List<String> options = BaseMeasureDiskOrderedScanAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    BaseCompression gtCompression = new GTCompression(programName);
    args = new String[0];
    Set<Set<String>> configs = gtCompression.analyze(args);

    AbstractCSVConfigGenerator generator =
        new GlobalModelCSVConfigGenerator(programName, options, model, BaseExecutor.REAL, configs);
    generator.generateCSVFile();
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    List<String> options = BaseIndexFilesAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    BaseCompression gtCompression = new GTCompression(programName);
    args = new String[0];
    Set<Set<String>> configs = gtCompression.analyze(args);

    AbstractCSVConfigGenerator generator =
        new GlobalModelCSVConfigGenerator(programName, options, model, BaseExecutor.REAL, configs);
    generator.generateCSVFile();
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    List<String> options = BaseConvertAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    BaseCompression gtCompression = new GTCompression(programName);
    args = new String[0];
    Set<Set<String>> configs = gtCompression.analyze(args);

    AbstractCSVConfigGenerator generator =
        new GlobalModelCSVConfigGenerator(programName, options, model, BaseExecutor.USER, configs);
    generator.generateCSVFile();
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    List<String> options = BaseRunBenchCAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);
    PerformanceModel<Partition> model = new PerformanceModel<>(relevantLocalModels);
    BaseCompression gtCompression = new GTCompression(programName);
    args = new String[0];
    Set<Set<String>> configs = gtCompression.analyze(args);

    AbstractCSVConfigGenerator generator =
        new GlobalModelCSVConfigGenerator(programName, options, model, BaseExecutor.USER, configs);
    generator.generateCSVFile();
  }
}
