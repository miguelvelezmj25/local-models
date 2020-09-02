package edu.cmu.cs.mvelezce.lc.perf.behavior.configs;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.explorer.utils.ConstraintUtils;
import edu.cmu.cs.mvelezce.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.model.PerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVConfigGenerator {
  public static final String OUTPUT_DIR = "../lc-perf-behavior/src/main/resources";

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

  private final String programName;
  private final List<String> options;
  private final PerformanceModel<Partition> model;
  private final String measuredTime;

  CSVConfigGenerator(
      String programName,
      List<String> options,
      PerformanceModel<Partition> model,
      String measuredTime) {
    this.programName = programName;
    this.options = options;
    this.model = model;
    this.measuredTime = measuredTime;
  }

  void generateCSVFile() throws IOException {
    StringBuilder result = new StringBuilder();
    for (String option : this.options) {
      result.append(option);
      result.append(",");
    }
    result.append("time");
    result.append("\n");

    Set<Set<String>> configs = this.getConfigs();
    for (Set<String> config : configs) {
      for (String option : this.options) {
        if (config.contains(option)) {
          result.append("1");
        } else {
          result.append("0");
        }
        result.append(",");
      }
      long time = this.model.evaluate(config, this.options);
      result.append(DECIMAL_FORMAT.format(time / 1E9));
      result.append("\n");
    }

    String outputDir =
        CSVConfigGenerator.OUTPUT_DIR
            + "/configs/java/programs/"
            + this.programName
            + "/"
            + this.measuredTime
            + "/"
            + this.programName
            + Options.DOT_CSV;
    File outputFile = new File(outputDir);
    if (outputFile.exists()) {
      FileUtils.forceDelete(outputFile);
    } else if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent directories");
    }
    PrintWriter writer = new PrintWriter(outputFile);
    writer.write(result.toString());
    writer.flush();
    writer.close();
  }

  private Set<Set<String>> getConfigs() {
    Set<LocalPerformanceModel<Partition>> localModels = this.model.getLocalModels();
    if (localModels.size() != 1) {
      throw new IllegalArgumentException("Expected 1 local model. Got " + localModels.size());
    }

    Set<Set<String>> configs = new HashSet<>();
    LocalPerformanceModel<Partition> localModel = localModels.iterator().next();
    for (Partition partition : localModel.getModel().keySet()) {
      configs.add(ConstraintUtils.toConfig(partition.getFeatureExpr(), this.options));
    }
    return configs;
  }
}
