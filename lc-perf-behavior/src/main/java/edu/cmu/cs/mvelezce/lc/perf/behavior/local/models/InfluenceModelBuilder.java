package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.ConstraintUtils;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.IDTALocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.partition.PartitionLocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.pretty.idta.IDTAPrettyBuilder;
import edu.cmu.cs.mvelezce.utils.config.Options;
import edu.cmu.cs.mvelezce.utils.configurations.ConfigHelper;
import scala.collection.JavaConversions;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InfluenceModelBuilder extends IDTAPrettyBuilder {

  private static final String OUTPUT_DIR =
      "../lc-perf-behavior/" + Options.DIRECTORY + "/local/models/influence/java/idta/programs";

  private final String measuredTime;
  private final String region;

  public InfluenceModelBuilder(
      String programName, String measuredTime, LocalPerformanceModel<Partition> localModel) {
    super(programName, getAllOptions(localModel), getBFModel(localModel));

    this.measuredTime = measuredTime;
    this.region = localModel.getRegion().toString();
  }

  /** Adds entries for implied partitions to build a performance-influence model. */
  private static PerformanceModel<Partition> getBFModel(
      LocalPerformanceModel<Partition> localModel) {
    Set<String> allOptions = getAllOptions(localModel);
    if (allOptions.isEmpty()) {
      return toPerfModel(localModel);
    }

    Set<Set<String>> allConfigs = ConfigHelper.getConfigurations(allOptions);
    Set<Partition> allPartitions = getAllPartitions(allConfigs, allOptions);
    Set<Partition> missingPartitions = getMissingPartitions(localModel, allPartitions);
    if (missingPartitions.isEmpty()) {
      return toPerfModel(localModel);
    }

    LocalPerformanceModel<Partition> fullLocalModel =
        addMissingPartitions(localModel, allPartitions, new ArrayList<>(allOptions));
    return toPerfModel(fullLocalModel);
  }

  private static LocalPerformanceModel<Partition> addMissingPartitions(
      LocalPerformanceModel<Partition> localModel,
      Set<Partition> allPartitions,
      List<String> options) {
    Map<Set<String>, Partition> x = PartitionLocalPerformanceModel.getConfigToPartition();
    Map<Partition, Double> model = new HashMap<>();
    for (Partition partition : allPartitions) {
      model.put(partition, 0.0);
    }

    LocalPerformanceModel<Partition> idtaModel =
        new IDTALocalPerformanceModel(
            localModel.getRegion(),
            localModel.getModel(),
            localModel.getModelToMin(),
            localModel.getModelToMax(),
            localModel.getModelToDiff(),
            localModel.getModelToSampleVariance(),
            localModel.getModelToConfidenceInterval(),
            localModel.getModelToCoefficientOfVariation(),
            localModel.getModelToPerfHumanReadable(),
            localModel.getModelToMinHumanReadable(),
            localModel.getModelToMaxHumanReadable(),
            localModel.getModelToDiffHumanReadable(),
            localModel.getModelToDiffHumanReadable(),
            localModel.getModelToConfidenceIntervalHumanReadable(),
            localModel.getModelToCoefficientOfVariationHumanReadable());
    for (Partition partition : allPartitions) {
      Set<String> config = ConstraintUtils.toConfig(partition.getFeatureExpr(), options);
      double time = idtaModel.evaluate(config, options);
      model.put(partition, time);
    }

    return new LocalPerformanceModel<>(
        localModel.getRegion(),
        model,
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

  private static PerformanceModel<Partition> toPerfModel(
      LocalPerformanceModel<Partition> localModel) {
    Set<LocalPerformanceModel<Partition>> localModels = new HashSet<>();
    localModels.add(localModel);
    return new PerformanceModel<>(localModels);
  }

  private static Set<Partition> getMissingPartitions(
      LocalPerformanceModel<Partition> localModel, Set<Partition> allPartitions) {
    Set<Partition> missingPartitions = new HashSet<>();
    for (Partition potentialPartition : allPartitions) {
      boolean found = false;
      for (Partition partition : localModel.getModel().keySet()) {
        if (potentialPartition.getFeatureExpr().equiv(partition.getFeatureExpr()).isTautology()) {
          found = true;
          break;
        }
      }
      if (!found) {
        missingPartitions.add(potentialPartition);
      }
    }

    return missingPartitions;
  }

  private static Set<Partition> getAllPartitions(Set<Set<String>> allConfigs, Set<String> options) {
    Set<String> prettyPartitions = new HashSet<>();
    for (Set<String> config : allConfigs) {
      String constraint = ConstraintUtils.parseAsConstraint(config, options);
      prettyPartitions.add(constraint);
    }
    return Partition.getPartitions(prettyPartitions);
  }

  private static Set<String> getAllOptions(LocalPerformanceModel<Partition> localModel) {
    Set<String> options = new HashSet<>();
    for (Partition partition : localModel.getModel().keySet()) {
      scala.collection.immutable.Set<String> features =
          partition.getFeatureExpr().collectDistinctFeatures();
      options.addAll(JavaConversions.setAsJavaSet(features));
    }
    return options;
  }

  @Override
  public PerformanceModel<Set<String>> analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    if (file.exists()) {
      return this.readFromFile(file);
    }

    PerformanceModel<Set<String>> influenceModel = this.analyze();
    if (Options.checkIfSave()) {
      this.writeToFile(influenceModel);
    }
    return influenceModel;
  }

  @Override
  public void writeToFile(PerformanceModel<Set<String>> model) throws IOException {
    String outputFile = this.outputDir();
    File file = new File(outputFile);
    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent dirs");
    }

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.writeValue(file, model);
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR
        + "/"
        + this.getProgramName()
        + "/"
        + this.measuredTime
        + "/"
        + this.region
        + Options.DOT_JSON;
  }
}
