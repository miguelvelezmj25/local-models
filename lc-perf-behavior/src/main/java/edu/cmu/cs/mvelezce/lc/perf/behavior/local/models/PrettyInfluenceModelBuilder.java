package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PrettyInfluenceModelBuilder implements Analysis<PrettyLocalInfluenceModel> {

  public static final String OUTPUT_DIR =
      "../lc-perf-behavior/"
          + Options.DIRECTORY
          + "/local/models/influence/pretty/java/idta/programs";

  private final String programName;
  private final String measuredTime;
  private final String region;
  private final double threshold;

  public PrettyInfluenceModelBuilder(String programName, String measuredTime, String region) {
    this(programName, measuredTime, region, 0.0);
  }

  public PrettyInfluenceModelBuilder(
      String programName, String measuredTime, String region, double threshold) {
    this.programName = programName;
    this.measuredTime = measuredTime;
    this.region = region;
    this.threshold = threshold;
  }

  @Override
  public PrettyLocalInfluenceModel analyze() throws IOException {
    File file =
        new File(
            InfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + this.programName
                + "/"
                + this.measuredTime
                + "/"
                + this.region
                + Options.DOT_JSON);
    if (!file.exists()) {
      throw new IllegalArgumentException(
          "Could not find influence model for "
              + this.programName
              + "."
              + this.measuredTime
              + " "
              + this.region);
    }

    ObjectMapper mapper = new ObjectMapper();
    PerformanceModel<String> readModel =
        mapper.readValue(file, new TypeReference<PerformanceModel<String>>() {});
    LocalPerformanceModel<String> readLocalModel = readModel.getLocalModels().iterator().next();
    LinkedHashMap<Set<String>, String> influenceModel = new LinkedHashMap<>();
    for (Map.Entry<String, String> entry :
        readLocalModel.getModelToPerfHumanReadable().entrySet()) {
      double time = Double.parseDouble(entry.getValue());
      if (Math.abs(time) <= this.threshold) {
        continue;
      }

      Set<String> config = toConfig(entry.getKey());
      influenceModel.put(config, entry.getValue());
    }

    Set<Map.Entry<Set<String>, String>> influenceModelSet = influenceModel.entrySet();
    List<Map.Entry<Set<String>, String>> influenceModelList = new ArrayList<>(influenceModelSet);
    Collections.sort(
        influenceModelList,
        (entry1, entry2) -> {
          Double v1 = Math.abs(Double.parseDouble(entry1.getValue()));
          Double v2 = Math.abs(Double.parseDouble(entry2.getValue()));

          return v2.compareTo(v1);
        });

    influenceModel.clear();
    for (Map.Entry<Set<String>, String> map : influenceModelList) {
      influenceModel.put(map.getKey(), map.getValue());
    }
    return new PrettyLocalInfluenceModel(readLocalModel.getRegion(), influenceModel);
  }

  private Set<String> toConfig(String entry) {
    int startOptionIndex = entry.indexOf("[") + 1;
    int endOptionIndex = entry.lastIndexOf("]");
    String optionsString = entry.substring(startOptionIndex, endOptionIndex);
    String[] arrayOptions = optionsString.split(",");

    Set<String> conf = new HashSet<>();
    for (String arrayOption : arrayOptions) {
      conf.add(arrayOption.trim());
    }
    return conf;
  }

  @Override
  public PrettyLocalInfluenceModel analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    if (file.exists()) {
      return this.readFromFile(file);
    }

    PrettyLocalInfluenceModel prettyInfluenceModel = this.analyze();
    if (Options.checkIfSave()) {
      this.writeToFile(prettyInfluenceModel);
    }
    return prettyInfluenceModel;
  }

  @Override
  public void writeToFile(PrettyLocalInfluenceModel influenceModel) throws IOException {
    String outputFile = this.outputDir();
    File file = new File(outputFile);
    if (influenceModel.getInfluenceModel().isEmpty()) {
      if (file.exists()) {
        FileUtils.forceDelete(file);
      }
      return;
    }

    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent dirs");
    }

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.writeValue(file, influenceModel);
  }

  @Override
  public PrettyLocalInfluenceModel readFromFile(File file) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR
        + "/"
        + this.programName
        + "/"
        + this.measuredTime
        + "/"
        + this.region
        + Options.DOT_JSON;
  }
}
