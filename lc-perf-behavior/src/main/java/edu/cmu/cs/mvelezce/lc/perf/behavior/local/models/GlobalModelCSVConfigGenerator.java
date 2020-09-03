package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.lc.perf.behavior.AbstractCSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.behavior.configs.CSVConfigGenerator;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class GlobalModelCSVConfigGenerator extends AbstractCSVConfigGenerator {
  private static final String OUTPUT_DIR =
      CSVConfigGenerator.OUTPUT_DIR + "/configs/local/models/global/java/programs/";

  private final Set<Set<String>> configs;

  public GlobalModelCSVConfigGenerator(
      String programName,
      List<String> options,
      PerformanceModel<Partition> model,
      String measuredTime,
      Set<Set<String>> configs) {
    super(programName, options, model, measuredTime);

    this.configs = configs;
  }

  @Override
  public void generateCSVFile() throws IOException {
    StringBuilder result = new StringBuilder();
    for (String option : this.getOptions()) {
      result.append(option);
      result.append(",");
    }
    result.append("time");
    result.append("\n");

    for (Set<String> config : this.configs) {
      for (String option : this.getOptions()) {
        if (config.contains(option)) {
          result.append("1");
        } else {
          result.append("0");
        }
        result.append(",");
      }
      long time = this.getModel().evaluate(config, this.getOptions());
      result.append(DECIMAL_FORMAT.format(time / 1E9));
      result.append("\n");
    }

    String outputDir =
        GlobalModelCSVConfigGenerator.OUTPUT_DIR
            + "/configs/java/programs/"
            + this.getProgramName()
            + "/"
            + this.getMeasuredTime()
            + "/"
            + this.getProgramName()
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
}
