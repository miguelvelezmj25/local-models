package edu.cmu.cs.mvelezce.lc.perf.model.builder.idta;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.explorer.idta.partition.TotalPartition;
import edu.cmu.cs.mvelezce.java.results.processed.PerformanceEntry;
import edu.cmu.cs.mvelezce.lc.perf.model.builder.partition.BasePartitionPerformanceModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.IDTALocalPerformanceModel;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.region.manager.RegionsManager;
import edu.cmu.cs.mvelezce.utils.config.Options;
import edu.cmu.cs.mvelezce.utils.stats.DescriptiveStatisticsMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class IDTAPerformanceModelBuilder extends BasePartitionPerformanceModelBuilder {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0000000");
  private static final String OUTPUT_DIR =
      "../lc-perf-model/" + Options.DIRECTORY + "/model/java/idta/programs";

  public IDTAPerformanceModelBuilder(String programName, String measuredTime) {
    this(programName, new ArrayList<>(), new HashMap<>(), new HashSet<>(), measuredTime);
  }

  IDTAPerformanceModelBuilder(
      String programName,
      List<String> options,
      Map<JavaRegion, Partitioning> regionsToData,
      Set<PerformanceEntry> performanceEntries,
      String measuredTime) {
    super(programName, options, regionsToData, performanceEntries, measuredTime);

    regionsToData.put(RegionsManager.PROGRAM_REGION, new TotalPartition());
  }

  @Override
  protected void populateLocalModel(LocalPerformanceModel<Partition> localModel) {
    System.out.println(localModel.getRegion());
    DescriptiveStatisticsMap<Partition> modelWithStats =
        this.getModelWithStats(localModel.getModel().keySet());
    this.addPerfEntries(localModel, modelWithStats);

    localModel.getModel().putAll(modelWithStats.getEntriesToData());
    Map<Partition, Double> regionsToMin = modelWithStats.getEntriesToMin();
    localModel.getModelToMin().putAll(regionsToMin);
    Map<Partition, Double> regionsToMax = modelWithStats.getEntriesToMax();
    localModel.getModelToMax().putAll(regionsToMax);
    localModel.getModelToDiff().putAll(modelWithStats.getEntriesToDiff(regionsToMin, regionsToMax));
    localModel.getModelToSampleVariance().putAll(modelWithStats.getEntriesToSampleVariance());
    localModel
        .getModelToConfidenceInterval()
        .putAll(modelWithStats.getEntriesToConfidenceInterval());
    localModel
        .getModelToCoefficientOfVariation()
        .putAll(modelWithStats.getCoefficientsOfVariation());

    localModel.getModelToPerfHumanReadable().putAll(toHumanReadable(localModel.getModel()));
    localModel.getModelToMinHumanReadable().putAll(toHumanReadable(localModel.getModelToMin()));
    localModel.getModelToMaxHumanReadable().putAll(toHumanReadable(localModel.getModelToMax()));
    localModel.getModelToDiffHumanReadable().putAll(toHumanReadable(localModel.getModelToDiff()));
    localModel
        .getModelToSampleVarianceHumanReadble()
        .putAll(toHumanReadableSampleVariance(localModel.getModelToSampleVariance()));
    localModel
        .getModelToConfidenceIntervalHumanReadable()
        .putAll(toHumanReadableCI(localModel.getModelToConfidenceInterval()));
    localModel
        .getModelToCoefficientOfVariationHumanReadable()
        .putAll(
            toHumanReadableCoefficientsOfVariation(localModel.getModelToCoefficientOfVariation()));
  }

  private Map<Partition, String> toHumanReadableSampleVariance(
      Map<Partition, Double> PartitionsToSampleVariance) {
    Map<Partition, String> PartitionsToHumanReadableData = new HashMap<>();

    for (Map.Entry<Partition, Double> entry : PartitionsToSampleVariance.entrySet()) {
      double data = entry.getValue();
      data = data / 1E18;
      PartitionsToHumanReadableData.put(entry.getKey(), DECIMAL_FORMAT.format(data));
    }

    return PartitionsToHumanReadableData;
  }

  private Map<Partition, String> toHumanReadableCoefficientsOfVariation(
      Map<Partition, Double> partitionsToData) {
    Map<Partition, String> PartitionsToHumanReadableData = new HashMap<>();

    for (Map.Entry<Partition, Double> entry : partitionsToData.entrySet()) {
      double data = entry.getValue();
      data = data;
      PartitionsToHumanReadableData.put(entry.getKey(), DECIMAL_FORMAT.format(data));
    }

    return PartitionsToHumanReadableData;
  }

  private Map<Partition, String> toHumanReadable(Map<Partition, Double> partitionsToData) {
    Map<Partition, String> PartitionsToHumanReadableData = new HashMap<>();

    for (Map.Entry<Partition, Double> entry : partitionsToData.entrySet()) {
      double data = entry.getValue();
      data = data / 1E9;
      PartitionsToHumanReadableData.put(entry.getKey(), DECIMAL_FORMAT.format(data));
    }

    return PartitionsToHumanReadableData;
  }

  private Map<Partition, List<String>> toHumanReadableCI(
      Map<Partition, List<Double>> partitionsToConfidenceIntervals) {
    Map<Partition, List<String>> PartitionsToHumanReadableCI = new HashMap<>();

    for (Map.Entry<Partition, List<Double>> entry : partitionsToConfidenceIntervals.entrySet()) {
      List<Double> confidenceInterval = entry.getValue();

      if (confidenceInterval.isEmpty()) {
        continue;
      }

      double lower = confidenceInterval.get(0) / 1E9;
      double higher = confidenceInterval.get(1) / 1E9;
      List<String> confidenceIntervalHumanReadable = new ArrayList<>();
      confidenceIntervalHumanReadable.add(DECIMAL_FORMAT.format(lower));
      confidenceIntervalHumanReadable.add(DECIMAL_FORMAT.format(higher));
      PartitionsToHumanReadableCI.put(entry.getKey(), confidenceIntervalHumanReadable);
    }

    return PartitionsToHumanReadableCI;
  }

  private void addPerfEntries(
      LocalPerformanceModel<Partition> localModel,
      DescriptiveStatisticsMap<Partition> modelWithStats) {
    for (PerformanceEntry entry : this.getPerformanceEntries()) {
      Map<UUID, Double> regionsToPerfs = entry.getRegionsToPerf();
      UUID region = localModel.getRegion();

      if (!regionsToPerfs.containsKey(region)) {
        for (Map.Entry<Partition, DescriptiveStatistics> problemEntry :
            modelWithStats.getMap().entrySet()) {
          problemEntry.getValue().addValue(0.0);
        }

        System.err.println(
            "There might be multiple regions in a method. The region "
                + region
                + " was not recorded in config "
                + entry.getConfiguration());
        continue;
      }

      double time = regionsToPerfs.get(region);
      Partition configPartition = this.getPerfEntryToExecConfigPartition().get(entry);

      for (Map.Entry<Partition, Double> partitionToTime : localModel.getModel().entrySet()) {
        if (!configPartition
            .getFeatureExpr()
            .implies(partitionToTime.getKey().getFeatureExpr())
            .isTautology()) {
          continue;
        }

        modelWithStats.get(partitionToTime.getKey()).addValue(time);
      }
    }
  }

  private DescriptiveStatisticsMap<Partition> getModelWithStats(Set<Partition> partitions) {
    DescriptiveStatisticsMap<Partition> modelWithStats = new DescriptiveStatisticsMap<>();

    for (Partition partition : partitions) {
      modelWithStats.putIfAbsent(partition);
    }

    return modelWithStats;
  }

  @Override
  public PerformanceModel<Partition> readFromFile(File dir) throws IOException {
    Collection<File> files = FileUtils.listFiles(dir, new String[] {"json"}, false);
    Set<LocalPerformanceModel<Partition>> localModels = new HashSet<>();

    for (File file : files) {
      ObjectMapper mapper = new ObjectMapper();
      LocalPerformanceModel<String> readLocalModel =
          mapper.readValue(file, new TypeReference<LocalPerformanceModel<String>>() {});
      LocalPerformanceModel<Partition> localModel =
          new IDTALocalPerformanceModel(
              readLocalModel.getRegion(),
              this.parsePartitionsToData(readLocalModel.getModel()),
              this.parsePartitionsToData(readLocalModel.getModelToMin()),
              this.parsePartitionsToData(readLocalModel.getModelToMax()),
              this.parsePartitionsToData(readLocalModel.getModelToDiff()),
              this.parsePartitionsToData(readLocalModel.getModelToSampleVariance()),
              this.parsePartitionsToCI(readLocalModel.getModelToConfidenceInterval()),
              this.parsePartitionsToData(readLocalModel.getModelToCoefficientOfVariation()),
              this.parsePartitionsToHumanReadableData(readLocalModel.getModelToPerfHumanReadable()),
              this.parsePartitionsToHumanReadableData(readLocalModel.getModelToMinHumanReadable()),
              this.parsePartitionsToHumanReadableData(readLocalModel.getModelToMaxHumanReadable()),
              this.parsePartitionsToHumanReadableData(readLocalModel.getModelToDiffHumanReadable()),
              this.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToSampleVarianceHumanReadble()),
              this.parsePartitionsToHumanReadableCI(
                  readLocalModel.getModelToConfidenceIntervalHumanReadable()),
              this.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToCoefficientOfVariationHumanReadable()));
      localModels.add(localModel);
    }

    return new PerformanceModel<>(localModels);
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName() + "/" + this.getMeasuredTime();
  }
}
