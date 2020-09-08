package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.influence.LocalPerformanceInfluenceModel;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import edu.cmu.cs.mvelezce.utils.config.Options;
import edu.cmu.cs.mvelezce.utils.configurations.ConfigHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class PrettyInfluenceModelBuilder implements Analysis<PrettyLocalInfluenceModel> {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  public static final String OUTPUT_DIR =
      "../lc-perf-behavior/"
          + Options.DIRECTORY
          + "/local/models/influence/pretty/java/idta/programs";

  private final String programName;
  private final String measuredTime;
  private final JavaRegion region;
  private final double threshold;

  public PrettyInfluenceModelBuilder(String programName, String measuredTime, JavaRegion region) {
    this(programName, measuredTime, region, 0.0);
  }

  public PrettyInfluenceModelBuilder(
      String programName, String measuredTime, JavaRegion region, double threshold) {
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
                + this.region.getId()
                + Options.DOT_JSON);
    if (!file.exists()) {
      throw new IllegalArgumentException(
          "Could not find influence model for "
              + this.programName
              + "."
              + this.measuredTime
              + " "
              + this.region.getId());
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

      Set<String> terms = toTerms(entry.getKey());
      if (terms.size() > 2) {
        continue;
      }
      influenceModel.put(terms, entry.getValue());
    }

    if (influenceModel.isEmpty()) {
      return new PrettyLocalInfluenceModel(
          readLocalModel.getRegion(),
          this.region.getRegionPackage(),
          this.region.getRegionClass(),
          this.region.getRegionMethodSignature(),
          influenceModel,
          new LinkedHashMap<>());
    }

    sortInfluenceModel(influenceModel);
    LinkedHashMap<Set<String>, String> executions = getExecutions(readLocalModel);
    return new PrettyLocalInfluenceModel(
        readLocalModel.getRegion(),
        this.region.getRegionPackage(),
        this.region.getRegionClass(),
        this.region.getRegionMethodSignature(),
        influenceModel,
        executions);
  }

  private LinkedHashMap<Set<String>, String> getExecutions(
      LocalPerformanceModel<String> readLocalModel) {
    LinkedHashMap<Set<String>, Double> influenceModel = getInfluenceModel(readLocalModel);
    LocalPerformanceInfluenceModel localInfluenceModel =
        new LocalPerformanceInfluenceModel(
            readLocalModel.getRegion(), influenceModel, new LinkedHashMap<>());

    Set<String> options = new HashSet<>();
    for (String s : readLocalModel.getModel().keySet()) {
      options.addAll(toTerms(s));
    }
    options.remove("");
    Set<Set<String>> configs = ConfigHelper.getConfigurations(options);

    LinkedHashMap<Set<String>, String> executions = new LinkedHashMap<>();
    List<String> optionsList = new ArrayList<>(options);
    for (Set<String> config : configs) {
      double time = localInfluenceModel.evaluate(config, optionsList);
      executions.put(config, DECIMAL_FORMAT.format(time / 1E9));
    }
    sortInfluenceModel(executions);
    return executions;
  }

  private LinkedHashMap<Set<String>, Double> getInfluenceModel(
      LocalPerformanceModel<String> readLocalModel) {
    LinkedHashMap<Set<String>, Double> influenceModel = new LinkedHashMap<>();
    for (Map.Entry<String, Double> entry : readLocalModel.getModel().entrySet()) {
      Set<String> terms = toTerms(entry.getKey());
      terms.remove("");
      influenceModel.put(terms, entry.getValue());
    }
    return influenceModel;
  }

  private void sortInfluenceModel(LinkedHashMap<Set<String>, String> configsToValues) {
    Set<Map.Entry<Set<String>, String>> influenceModelSet = configsToValues.entrySet();
    List<Map.Entry<Set<String>, String>> influenceModelList = new ArrayList<>(influenceModelSet);
    influenceModelList.sort(
        (entry1, entry2) -> {
          Double v1 = Math.abs(Double.parseDouble(entry1.getValue()));
          Double v2 = Math.abs(Double.parseDouble(entry2.getValue()));

          return v2.compareTo(v1);
        });

    configsToValues.clear();
    for (Map.Entry<Set<String>, String> map : influenceModelList) {
      configsToValues.put(map.getKey(), map.getValue());
    }
  }

  private Set<String> toTerms(String entry) {
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
        + this.region.getId()
        + Options.DOT_JSON;
  }
}
