package edu.cmu.cs.mvelezce.lc.vs.extension.local.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.behavior.local.models.PrettyInfluenceModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.behavior.local.models.PrettyLocalInfluenceModelRead;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class LocalModelsGenerator implements Analysis<Void> {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/local/models/java/programs";

  private final String programName;
  private final String measuredTime;

  public LocalModelsGenerator(String programName, String measuredTime) {
    this.programName = programName;
    this.measuredTime = measuredTime;
  }

  @Override
  public Void analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);

    this.analyze();
    return null;
  }

  @Override
  public Void analyze() throws IOException {
    String prettyLocalModelsDir =
        PrettyInfluenceModelBuilder.OUTPUT_DIR + "/" + this.programName + "/" + this.measuredTime;
    Collection<File> prettyLocalModels =
        FileUtils.listFiles(new File(prettyLocalModelsDir), new String[] {"json"}, false);
    for (File prettyLocalModel : prettyLocalModels) {
      ObjectMapper mapper = new ObjectMapper();
      PrettyLocalInfluenceModelRead localModel =
          mapper.readValue(prettyLocalModel, new TypeReference<PrettyLocalInfluenceModelRead>() {});
      if (localModel.getClassName().isEmpty()) {
        continue;
      }
      File file =
          new File(
              this.outputDir()
                  + "/"
                  + localModel.getPackageName()
                  + "."
                  + localModel.getClassName()
                  + "."
                  + this.getMethodName(localModel.getMethodSignature())
                  + ".csv");
      if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent dirs for " + file);
      }
      PrintWriter writer = new PrintWriter(file);
      for (Map.Entry<Set<String>, String> entry : localModel.getInfluenceModel().entrySet()) {
        if (entry.getKey().isEmpty()) {
          continue;
        }
        for (String selectedOption : entry.getKey()) {
          writer.print(selectedOption);
          writer.print(",");
        }
        writer.println(entry.getValue());
      }
      writer.close();
    }
    return null;
  }

  private String getMethodName(String methodSignature) {
    return methodSignature.substring(0, methodSignature.indexOf("("));
  }

  @Override
  public void writeToFile(Void unused) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public Void readFromFile(File file) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }
}
