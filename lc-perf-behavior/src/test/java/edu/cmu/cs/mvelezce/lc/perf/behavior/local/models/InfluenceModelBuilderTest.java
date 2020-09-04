package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.explorer.idta.IDTA;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.FeatureExprUtils;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.pretty.BasePrettyBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class InfluenceModelBuilderTest {

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
    List<String> options = new ArrayList<>();
    options.add("A");
    options.add("B");

    BasePrettyBuilder<Partition> analysis =
        new InfluenceModelBuilder(programName, options, localModel);
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

    Map<Partition, Set<String>> partitionsToConfigs = new HashMap<>();
    partitionsToConfigs.put(partition1, config1);
    partitionsToConfigs.put(partition2, config2);
    partitionsToConfigs.put(partition3, config3);
    partitionsToConfigs.put(partition4, config4);

    for (Map.Entry<Partition, Set<String>> entry : partitionsToConfigs.entrySet()) {
      double time = influenceModel.evaluate(entry.getValue(), options);
      double expected = perfModel.get(entry.getKey());

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
        UUID.randomUUID(),
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
