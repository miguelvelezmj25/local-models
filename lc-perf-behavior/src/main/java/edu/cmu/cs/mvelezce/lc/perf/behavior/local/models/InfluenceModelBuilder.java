package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.ConstraintUtils;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.pretty.idta.IDTAPrettyBuilder;
import edu.cmu.cs.mvelezce.utils.config.Options;
import edu.cmu.cs.mvelezce.utils.configurations.ConfigHelper;
import scala.collection.JavaConversions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InfluenceModelBuilder extends IDTAPrettyBuilder {

  private static final String OUTPUT_DIR =
      "../lc-perf-behavior/" + Options.DIRECTORY + "/local/models/influence/java/idta/programs";

  public InfluenceModelBuilder(
      String programName, Collection<String> options, LocalPerformanceModel<Partition> localModel) {
    super(programName, options, getBFModel(localModel));
  }

  /** Adds entries for implied partitions to build a performance-influence model. */
  private static PerformanceModel<Partition> getBFModel(
      LocalPerformanceModel<Partition> localModel) {
    Set<String> allOptions = getAllOptions(localModel);
    Set<Set<String>> allConfigs = ConfigHelper.getConfigurations(allOptions);
    Set<Partition> allPartitions = getAllPartitions(allConfigs, allOptions);
    Set<Partition> missingPartitions = getMissingPartitions(localModel, allPartitions);
    if (missingPartitions.isEmpty()) {
      return toPerfModel(localModel);
    }

    throw new UnsupportedOperationException("Implement");
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
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName();
  }
}
