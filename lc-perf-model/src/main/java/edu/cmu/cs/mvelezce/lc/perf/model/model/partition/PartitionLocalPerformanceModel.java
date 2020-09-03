package edu.cmu.cs.mvelezce.lc.perf.model.model.partition;

import edu.cmu.cs.mvelezce.explorer.idta.IDTA;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.ConstraintUtils;
import edu.cmu.cs.mvelezce.explorer.utils.FeatureExprUtils;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;

import java.util.*;

public class PartitionLocalPerformanceModel extends LocalPerformanceModel<Partition> {

  private static final Map<Set<String>, Partition> CONFIG_TO_PARTITION = new HashMap<>();

  public PartitionLocalPerformanceModel(
      UUID region,
      Map<Partition, Double> model,
      Map<Partition, Double> modelToMin,
      Map<Partition, Double> modelToMax,
      Map<Partition, Double> modelToDiff,
      Map<Partition, Double> modelToSampleVariance,
      Map<Partition, List<Double>> modelToConfidenceInterval,
      Map<Partition, Double> modelToCoefficientOfVariation,
      Map<Partition, String> modelToPerfHumanReadable,
      Map<Partition, String> modelToMinHumanReadable,
      Map<Partition, String> modelToMaxHumanReadable,
      Map<Partition, String> modelToDiffHumanReadable,
      Map<Partition, String> modelToSampleVarianceHumanReadable,
      Map<Partition, List<String>> modelToConfidenceIntervalHumanReadable,
      Map<Partition, String> modelToCoefficientOfVariationHumanReadable) {
    super(
        region,
        model,
        modelToMin,
        modelToMax,
        modelToDiff,
        modelToSampleVariance,
        modelToConfidenceInterval,
        modelToCoefficientOfVariation,
        modelToPerfHumanReadable,
        modelToMinHumanReadable,
        modelToMaxHumanReadable,
        modelToDiffHumanReadable,
        modelToSampleVarianceHumanReadable,
        modelToConfidenceIntervalHumanReadable,
        modelToCoefficientOfVariationHumanReadable);
  }

  protected static Partition getConfigAsPartition(Set<String> config, List<String> options) {
    Partition configAsPartition = CONFIG_TO_PARTITION.get(config);

    if (configAsPartition == null) {
      String stringPartition = ConstraintUtils.parseAsConstraint(config, options);
      configAsPartition =
          new Partition(FeatureExprUtils.parseAsFeatureExpr(IDTA.USE_BDD, stringPartition));
      CONFIG_TO_PARTITION.put(config, configAsPartition);
    }

    return configAsPartition;
  }

  @Override
  public double evaluate(Set<String> config, List<String> options) {
    System.err.println(
        "Calling expensive evaluate to search for each entry doing equivalence check");
    Partition configAsPartition = getConfigAsPartition(config, options);

    for (Map.Entry<Partition, Double> entry : this.getModel().entrySet()) {
      if (!configAsPartition
          .getFeatureExpr()
          .equiv(entry.getKey().getFeatureExpr())
          .isTautology()) {
        continue;
      }

      return entry.getValue();
    }

    throw new RuntimeException("The model appears to be empty");
  }

  @Override
  public double evaluateVariance(Set<String> config, List<String> options) {
    System.err.println(
        "Calling expensive evaluate to search for each entry doing equivalence check");
    Partition configAsPartition = getConfigAsPartition(config, options);

    for (Map.Entry<Partition, Double> entry : this.getModelToSampleVariance().entrySet()) {
      if (!configAsPartition
          .getFeatureExpr()
          .equiv(entry.getKey().getFeatureExpr())
          .isTautology()) {
        continue;
      }

      return entry.getValue();
    }

    throw new RuntimeException("The model appears to be empty");
  }

  @Override
  public List<Double> evaluateConfidenceInterval(Set<String> config, List<String> options) {
    System.err.println(
        "Calling expensive evaluate to search for each entry doing equivalence check");
    Partition configAsPartition = getConfigAsPartition(config, options);

    for (Map.Entry<Partition, List<Double>> entry :
        this.getModelToConfidenceInterval().entrySet()) {
      if (!configAsPartition
          .getFeatureExpr()
          .equiv(entry.getKey().getFeatureExpr())
          .isTautology()) {
        continue;
      }

      return entry.getValue();
    }

    throw new RuntimeException("The model appears to be empty");
  }

  /**
   * Returns the configs to partition map to reset it when evaluating the execution time of local
   * models
   *
   * @return the configs to partition map
   */
  public static Map<Set<String>, Partition> getConfigToPartition() {
    return CONFIG_TO_PARTITION;
  }
}
