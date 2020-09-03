package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import de.fosd.typechef.featureexpr.FeatureExpr;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.ConstraintUtils;
import edu.cmu.cs.mvelezce.lc.perf.behavior.AbstractCSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.behavior.configs.CSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.partition.PartitionLocalPerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import scala.collection.JavaConversions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocalModelCSVConfigGenerator extends AbstractCSVConfigGenerator {
  private static final String OUTPUT_DIR =
      CSVConfigGenerator.OUTPUT_DIR + "/configs/local/models/local/java/programs/";

  public LocalModelCSVConfigGenerator(
      String programName,
      List<String> options,
      PerformanceModel<Partition> model,
      String measuredTime) {
    super(programName, options, model, measuredTime);
  }

  @Override
  public void generateCSVFile() throws IOException {
    File rootDir =
        new File(
            LocalModelCSVConfigGenerator.OUTPUT_DIR
                + "/configs/java/programs/"
                + this.getProgramName()
                + "/"
                + this.getMeasuredTime());
    if (rootDir.exists()) {
      FileUtils.forceDelete(rootDir);
    }

    for (LocalPerformanceModel<Partition> localModel : this.getModel().getLocalModels()) {
      StringBuilder result = new StringBuilder();
      Set<String> options = this.getLocalModelOptions(localModel);
      for (String option : options) {
        result.append(option);
        result.append(",");
      }
      result.append("time");
      result.append("\n");

      Set<Set<String>> configs = this.getLocalModelConfigs(localModel, options);
      for (Set<String> config : configs) {
        for (String option : options) {
          if (config.contains(option)) {
            result.append("1");
          } else {
            result.append("0");
          }
          result.append(",");
        }
        double time = localModel.evaluate(config, new ArrayList<>(options));
        result.append(DECIMAL_FORMAT.format(time / 1E9));
        result.append("\n");
      }

      String outputDir = localModel.getRegion() + Options.DOT_CSV;
      File outputFile = new File(rootDir, outputDir);
      if (outputFile.exists()) {
        FileUtils.forceDelete(outputFile);
      } else if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent directories");
      }
      PrintWriter writer = new PrintWriter(outputFile);
      writer.write(result.toString());
      writer.flush();
      writer.close();
      PartitionLocalPerformanceModel.getConfigToPartition().clear();
    }
  }

  private Set<Set<String>> getLocalModelConfigs(
      LocalPerformanceModel<Partition> localModel, Set<String> options) {
    Set<Set<String>> configs = new HashSet<>();
    for (Partition partition : localModel.getModel().keySet()) {
      FeatureExpr featureExpr = partition.getFeatureExpr();
      Set<String> config = ConstraintUtils.toConfig(featureExpr, options);
      configs.add(config);
    }
    return configs;
  }

  private Set<String> getLocalModelOptions(LocalPerformanceModel<Partition> localModel) {
    Set<String> localModelOptions = new HashSet<>();
    for (Partition partition : localModel.getModel().keySet()) {
      scala.collection.immutable.Set<String> features =
          partition.getFeatureExpr().collectDistinctFeatures();
      Set<String> options = JavaConversions.setAsJavaSet(features);
      localModelOptions.addAll(options);
    }

    Set<String> allOptions = new HashSet<>(this.getOptions());
    for (String option : localModelOptions) {
      if (!allOptions.contains(option)) {
        throw new IllegalArgumentException("Invalid option " + option);
      }
    }

    return localModelOptions;
  }
}
