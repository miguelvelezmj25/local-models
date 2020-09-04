package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.IDTA;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.FeatureExprUtils;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.partition.PartitionLocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.pretty.BasePrettyBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class InfluenceModelBuilderTest {

  @Test
  public void berkeleyDB() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      BasePrettyBuilder<Partition> analysis =
          new InfluenceModelBuilder(programName, BaseExecutor.REAL, localModel);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      analysis.analyze(args);
      PartitionLocalPerformanceModel.getConfigToPartition().clear();
    }
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      BasePrettyBuilder<Partition> analysis =
          new InfluenceModelBuilder(programName, BaseExecutor.REAL, localModel);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      analysis.analyze(args);
      PartitionLocalPerformanceModel.getConfigToPartition().clear();
    }
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      BasePrettyBuilder<Partition> analysis =
          new InfluenceModelBuilder(programName, BaseExecutor.USER, localModel);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      analysis.analyze(args);
      PartitionLocalPerformanceModel.getConfigToPartition().clear();
    }
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      BasePrettyBuilder<Partition> analysis =
          new InfluenceModelBuilder(programName, BaseExecutor.USER, localModel);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      analysis.analyze(args);
      PartitionLocalPerformanceModel.getConfigToPartition().clear();
    }
  }

  @Test
  public void test1() throws IOException, InterruptedException {
    Partition partition1 = getPartition("!A && !B");
    Partition partition2 = getPartition("!A && B");
    Partition partition3 = getPartition("A && !B");
    Partition partition4 = getPartition("A && B");

    Map<Partition, Double> perfModel = new HashMap<>();
    perfModel.put(partition1, toNano(3.0));
    perfModel.put(partition2, toNano(5.0));
    perfModel.put(partition3, toNano(2.0));
    perfModel.put(partition4, toNano(2.0));
    LocalPerformanceModel<Partition> localModel = getLocalModel(perfModel);

    String programName = "test1";
    BasePrettyBuilder<Partition> analysis =
        new InfluenceModelBuilder(programName, BaseExecutor.REAL, localModel);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    PerformanceModel<Set<String>> influenceModel = analysis.analyze(args);

    Set<String> config1 = new HashSet<>();
    Set<String> config2 = new HashSet<>();
    config2.add("B");
    Set<String> config3 = new HashSet<>();
    config3.add("A");
    Set<String> config4 = new HashSet<>();
    config4.add("A");
    config4.add("B");

    Map<Set<String>, Double> configsToTimes = new HashMap<>();
    configsToTimes.put(config1, toNano(3.0));
    configsToTimes.put(config2, toNano(5.0));
    configsToTimes.put(config3, toNano(2.0));
    configsToTimes.put(config4, toNano(2.0));

    List<String> options = new ArrayList<>();
    options.add("A");
    options.add("B");
    assertEqualPredictions(influenceModel, configsToTimes, options);
  }

  @Test
  public void test2() throws IOException, InterruptedException {
    Partition partition1 = getPartition("!A && !B");
    Partition partition2 = getPartition("!A && B");
    Partition partition3 = getPartition("A");

    Map<Partition, Double> perfModel = new HashMap<>();
    perfModel.put(partition1, toNano(3.0));
    perfModel.put(partition2, toNano(5.0));
    perfModel.put(partition3, toNano(2.0));
    LocalPerformanceModel<Partition> localModel = getLocalModel(perfModel);

    String programName = "test2";
    BasePrettyBuilder<Partition> analysis =
        new InfluenceModelBuilder(programName, BaseExecutor.REAL, localModel);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    PerformanceModel<Set<String>> influenceModel = analysis.analyze(args);

    Set<String> config1 = new HashSet<>();
    Set<String> config2 = new HashSet<>();
    config2.add("B");
    Set<String> config3 = new HashSet<>();
    config3.add("A");
    Set<String> config4 = new HashSet<>();
    config4.add("A");
    config4.add("B");

    Map<Set<String>, Double> configsToTimes = new HashMap<>();
    configsToTimes.put(config1, toNano(3.0));
    configsToTimes.put(config2, toNano(5.0));
    configsToTimes.put(config3, toNano(2.0));
    configsToTimes.put(config4, toNano(2.0));

    List<String> options = new ArrayList<>();
    options.add("A");
    options.add("B");
    assertEqualPredictions(influenceModel, configsToTimes, options);
  }

  @Test
  public void test3() throws IOException, InterruptedException {
    Partition partition1 = getPartition("!A && !B & C");
    Partition partition2 = getPartition("!A && B");
    Partition partition3 = getPartition("A");
    Partition partition4 = getPartition("!A && !B & !C");

    Map<Partition, Double> perfModel = new HashMap<>();
    perfModel.put(partition1, toNano(3.0));
    perfModel.put(partition2, toNano(5.0));
    perfModel.put(partition3, toNano(2.0));
    perfModel.put(partition4, toNano(0.0));
    LocalPerformanceModel<Partition> localModel = getLocalModel(perfModel);

    String programName = "test3";
    BasePrettyBuilder<Partition> analysis =
        new InfluenceModelBuilder(programName, BaseExecutor.REAL, localModel);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    PerformanceModel<Set<String>> influenceModel = analysis.analyze(args);

    Set<String> config1 = new HashSet<>();
    Set<String> config2 = new HashSet<>();
    config2.add("C");
    Set<String> config3 = new HashSet<>();
    config3.add("B");
    Set<String> config4 = new HashSet<>();
    config4.add("B");
    config4.add("C");
    Set<String> config5 = new HashSet<>();
    config5.add("A");
    Set<String> config6 = new HashSet<>();
    config6.add("A");
    config6.add("C");
    Set<String> config7 = new HashSet<>();
    config7.add("A");
    config7.add("B");
    Set<String> config8 = new HashSet<>();
    config8.add("A");
    config8.add("B");
    config8.add("C");

    Map<Set<String>, Double> configsToTimes = new HashMap<>();
    configsToTimes.put(config1, toNano(0.0));
    configsToTimes.put(config2, toNano(3.0));
    configsToTimes.put(config3, toNano(5.0));
    configsToTimes.put(config4, toNano(5.0));
    configsToTimes.put(config5, toNano(2.0));
    configsToTimes.put(config6, toNano(2.0));
    configsToTimes.put(config7, toNano(2.0));
    configsToTimes.put(config8, toNano(2.0));

    List<String> options = new ArrayList<>();
    options.add("A");
    options.add("B");
    options.add("C");
    assertEqualPredictions(influenceModel, configsToTimes, options);
  }

  private void assertEqualPredictions(
      PerformanceModel<Set<String>> influenceModel,
      Map<Set<String>, Double> configsToTimes,
      List<String> options) {
    for (Map.Entry<Set<String>, Double> entry : configsToTimes.entrySet()) {
      double time = influenceModel.evaluate(entry.getKey(), options);
      double expected = entry.getValue();

      Assert.assertEquals(expected, time, 0.0);
    }
  }

  private Partition getPartition(String partition) {
    return new Partition(FeatureExprUtils.parseAsFeatureExpr(IDTA.USE_BDD, partition));
  }

  private double toNano(double value) {
    return value * 1E9;
  }

  private LocalPerformanceModel<Partition> getLocalModel(Map<Partition, Double> perfModel) {
    return new LocalPerformanceModel<>(
        UUID.fromString("f7569165-c46d-4b4d-ada6-9e23736b1608"),
        perfModel,
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>(),
        new HashMap<>());
  }
}
