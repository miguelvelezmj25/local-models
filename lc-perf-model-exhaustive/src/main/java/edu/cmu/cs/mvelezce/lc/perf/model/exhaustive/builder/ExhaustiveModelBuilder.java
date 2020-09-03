package edu.cmu.cs.mvelezce.lc.perf.model.exhaustive.builder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.results.processed.PerformanceEntry;
import edu.cmu.cs.mvelezce.lc.perf.model.builder.partition.BasePartitionPerformanceModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.e2e.builder.E2EModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.exhaustive.model.partition.ExhaustiveLocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class ExhaustiveModelBuilder extends E2EModelBuilder {

  public ExhaustiveModelBuilder(
      String programName,
      List<String> options,
      Set<PerformanceEntry> performanceEntries,
      String measuredTime) {
    super(programName, options, performanceEntries, measuredTime);

    this.addProgramRegionToData();
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
          new ExhaustiveLocalPerformanceModel(
              readLocalModel.getRegion(),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(readLocalModel.getModel()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(
                  readLocalModel.getModelToMin()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(
                  readLocalModel.getModelToMax()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(
                  readLocalModel.getModelToDiff()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(
                  readLocalModel.getModelToSampleVariance()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToCI(
                  readLocalModel.getModelToConfidenceInterval()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToData(
                  readLocalModel.getModelToCoefficientOfVariation()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToPerfHumanReadable()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToMinHumanReadable()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToMaxHumanReadable()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToDiffHumanReadable()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToSampleVarianceHumanReadble()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableCI(
                  readLocalModel.getModelToConfidenceIntervalHumanReadable()),
              BasePartitionPerformanceModelBuilder.parsePartitionsToHumanReadableData(
                  readLocalModel.getModelToCoefficientOfVariationHumanReadable()));
      localModels.add(localModel);
    }

    return new PerformanceModel<>(localModels);
  }

  @Override
  protected void populateLocalModel(LocalPerformanceModel<Partition> localModel) {
    UUID programRegion = localModel.getRegion();

    for (PerformanceEntry entry : this.getPerformanceEntries()) {
      Partition configPartition = this.getPerfEntryToExecConfigPartition().get(entry);

      for (UUID regionUUID : entry.getRegionsToPerf().keySet()) {
        if (!programRegion.equals(regionUUID)) {
          throw new RuntimeException(
              "Expected the performance entry to have thr program region '"
                  + programRegion
                  + "', but found '"
                  + regionUUID
                  + "'");
        }

        this.addEntry(
            localModel.getModel(), configPartition, entry.getRegionsToPerf().get(regionUUID));
        this.addEntry(
            localModel.getModelToMin(), configPartition, entry.getRegionsToMin().get(regionUUID));
        this.addEntry(
            localModel.getModelToMax(), configPartition, entry.getRegionsToMax().get(regionUUID));
        this.addEntry(
            localModel.getModelToDiff(), configPartition, entry.getRegionsToDiff().get(regionUUID));
        this.addEntry(
            localModel.getModelToSampleVariance(),
            configPartition,
            entry.getRegionsToSampleVariance().get(regionUUID));
        this.addEntry(
            localModel.getModelToConfidenceInterval(),
            configPartition,
            entry.getRegionsToConfidenceInterval().get(regionUUID));

        this.addEntry(
            localModel.getModelToPerfHumanReadable(),
            configPartition,
            entry.getRegionsToPerfHumanReadable().get(regionUUID));
        this.addEntry(
            localModel.getModelToMinHumanReadable(),
            configPartition,
            entry.getRegionsToMinHumanReadable().get(regionUUID));
        this.addEntry(
            localModel.getModelToMaxHumanReadable(),
            configPartition,
            entry.getRegionsToMaxHumanReadable().get(regionUUID));
        this.addEntry(
            localModel.getModelToDiffHumanReadable(),
            configPartition,
            entry.getRegionsToDiffHumanReadable().get(regionUUID));
        this.addEntry(
            localModel.getModelToSampleVarianceHumanReadble(),
            configPartition,
            entry.getRegionsToSampleVarianceHumanReadable().get(regionUUID));
        this.addEntryHuman(
            localModel.getModelToConfidenceIntervalHumanReadable(),
            configPartition,
            entry.getRegionsToConfidenceIntervalsHumanReadable().get(regionUUID));
      }
    }
  }

  private void addEntry(Map<Partition, String> model, Partition configPartition, String value) {
    if (!model.containsKey(configPartition)) {
      throw new RuntimeException("Could not find config partition " + configPartition);
    }

    if (!model.get(configPartition).equals(BasePartitionPerformanceModelBuilder.EMPTY_HUMAN)) {
      throw new RuntimeException(
          "Expected the entry of '"
              + configPartition
              + "' to be '"
              + BasePartitionPerformanceModelBuilder.EMPTY_HUMAN
              + "', but was '"
              + model.get(configPartition)
              + "' and was about to add "
              + value);
    }

    model.put(configPartition, value);
  }

  private void addEntry(Map<Partition, Double> model, Partition configPartition, double value) {
    if (!model.containsKey(configPartition)) {
      throw new RuntimeException("Could not find config partition " + configPartition);
    }

    if (model.get(configPartition) != BasePartitionPerformanceModelBuilder.EMPTY_DOUBLE) {
      throw new RuntimeException(
          "Expected the entry of '"
              + configPartition
              + "' to be '"
              + BasePartitionPerformanceModelBuilder.EMPTY_DOUBLE
              + "', but was '"
              + model.get(configPartition)
              + "' and was about to add "
              + value);
    }

    model.put(configPartition, value);
  }

  private void addEntry(
      Map<Partition, List<Double>> model, Partition configPartition, List<Double> values) {
    if (!model.containsKey(configPartition)) {
      throw new RuntimeException("Could not find config partition " + configPartition);
    }

    List<Double> entries = model.get(configPartition);

    if (!entries.isEmpty()) {
      throw new RuntimeException(
          "Expected the entry of '"
              + configPartition
              + "' to be empty, but found "
              + entries
              + " and was about to add "
              + values);
    }

    model.put(configPartition, values);
  }

  private void addEntryHuman(
      Map<Partition, List<String>> model, Partition configPartition, List<String> values) {
    if (!model.containsKey(configPartition)) {
      throw new RuntimeException("Could not find config partition " + configPartition);
    }

    List<String> entries = model.get(configPartition);

    if (!entries.isEmpty()) {
      throw new RuntimeException(
          "Expected the entry of '"
              + configPartition
              + "' to be empty, but found "
              + entries
              + " and was about to add "
              + values);
    }

    model.put(configPartition, values);
  }
}
