package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.analysis.BaseAnalysis;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.lc.perf.model.builder.partition.BasePartitionPerformanceModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.IDTALocalPerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LocalModelFilterer extends BaseAnalysis<Set<LocalPerformanceModel<Partition>>> {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
  private static final String OUTPUT_DIR =
      "../lc-perf-behavior/" + Options.DIRECTORY + "/local/models/java/idta/programs";

  private final PerformanceModel<Partition> model;
  private final String measuredTime;
  private final double threshold;

  public LocalModelFilterer(Builder builder) {
    super(builder.programName);

    this.model = builder.model;
    this.measuredTime = builder.measuredTime;
    this.threshold = builder.threshold;
  }

  @Override
  public Set<LocalPerformanceModel<Partition>> analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    if (file.exists()) {
      return this.readFromFile(file);
    }

    Set<LocalPerformanceModel<Partition>> relevantLocalModels = this.analyze();
    if (Options.checkIfSave()) {
      this.writeToFile(relevantLocalModels);
    }
    return relevantLocalModels;
  }

  @Override
  public Set<LocalPerformanceModel<Partition>> analyze() {
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = new HashSet<>();
    for (LocalPerformanceModel<Partition> localModel : this.model.getLocalModels()) {
      if (this.relevantLocalModel(localModel)) {
        relevantLocalModels.add(localModel);
      }
    }

    System.out.println(
        relevantLocalModels.size()
            + " relevant (threshold "
            + this.threshold
            + "s) local models out of "
            + this.model.getLocalModels().size());
    System.out.println(
        DECIMAL_FORMAT.format(
                100.0 * relevantLocalModels.size() / this.model.getLocalModels().size())
            + "%");
    return relevantLocalModels;
  }

  private boolean relevantLocalModel(LocalPerformanceModel<Partition> localModel) {
    for (Double time : localModel.getModel().values()) {
      if (time > (this.threshold * 1E9)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void writeToFile(Set<LocalPerformanceModel<Partition>> relevantLocalModels)
      throws IOException {
    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      String outputFile = this.outputDir() + "/" + localModel.getRegion() + Options.DOT_JSON;
      File file = new File(outputFile);
      if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent file dirs");
      }

      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.writeValue(file, localModel);
    }
  }

  @Override
  public Set<LocalPerformanceModel<Partition>> readFromFile(File dir) throws IOException {
    Collection<File> files = FileUtils.listFiles(dir, new String[] {"json"}, false);
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = new HashSet<>();

    for (File file : files) {
      ObjectMapper mapper = new ObjectMapper();
      LocalPerformanceModel<String> readLocalModel =
          mapper.readValue(file, new TypeReference<LocalPerformanceModel<String>>() {});
      LocalPerformanceModel<Partition> localModel =
          new IDTALocalPerformanceModel(
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
      relevantLocalModels.add(localModel);
    }

    return relevantLocalModels;
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName() + "/" + this.measuredTime;
  }

  public static class Builder {
    private final String programName;
    private final String measuredTime;

    private PerformanceModel<Partition> model;
    private double threshold = 0.0;

    public Builder(String programName, String measuredTime) {
      this.programName = programName;
      this.measuredTime = measuredTime;
    }

    public Builder model(PerformanceModel<Partition> model) {
      this.model = model;
      return this;
    }

    public Builder threshold(double threshold) {
      if (threshold < 0.0) {
        throw new IllegalArgumentException("The threshold " + threshold + " cannot be < 0.0");
      }
      this.threshold = threshold;
      return this;
    }

    public LocalModelFilterer build() {
      return new LocalModelFilterer(this);
    }
  }
}
