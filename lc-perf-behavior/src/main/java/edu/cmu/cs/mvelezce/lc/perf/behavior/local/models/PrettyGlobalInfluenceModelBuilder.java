package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.analysis.BaseAnalysis;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class PrettyGlobalInfluenceModelBuilder extends BaseAnalysis<PrettyGlobalInfluenceModel> {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
  private static final Set<String> TRUE_TERM = getTrueTerm();

  private static Set<String> getTrueTerm() {
    Set<String> trueTerm = new HashSet<>();
    trueTerm.add("True");
    return trueTerm;
  }

  public static final String OUTPUT_DIR =
      "../lc-perf-behavior/"
          + Options.DIRECTORY
          + "/local/models/global/influence/pretty/java/idta/programs";

  private final String measuredTime;

  PrettyGlobalInfluenceModelBuilder(String programName, String measuredTime) {
    super(programName);

    this.measuredTime = measuredTime;
  }

  @Override
  public PrettyGlobalInfluenceModel analyze() throws IOException {
    Set<PrettyLocalInfluenceModel> models = this.getLocalModels();
    LinkedHashMap<Set<String>, String> influenceModel = this.getInfluenceModel(models);
    return new PrettyGlobalInfluenceModel(influenceModel);
  }

  private LinkedHashMap<Set<String>, String> getInfluenceModel(
      Set<PrettyLocalInfluenceModel> models) {
    Set<Set<String>> terms = this.getTerms(models);
    Map<Set<String>, Double> model = new HashMap<>();
    for (Set<String> term : terms) {
      model.put(term, 0.0);
    }
    for (PrettyLocalInfluenceModel localModel : models) {
      LinkedHashMap<Set<String>, String> localInfluenceModel = localModel.getInfluenceModel();
      for (Set<String> term : terms) {
        if (!term.equals(TRUE_TERM) && !localInfluenceModel.containsKey(term)) {
          continue;
        }

        String valueString = localInfluenceModel.get(term);
        double value = Double.parseDouble(valueString);
        double currentValue = model.get(term);
        model.put(term, currentValue + value);
      }
    }

    return toInfluenceModel(model);
  }

  private LinkedHashMap<Set<String>, String> toInfluenceModel(Map<Set<String>, Double> model) {
    LinkedHashMap<Set<String>, String> influenceModel = new LinkedHashMap<>();
    for (Map.Entry<Set<String>, Double> entry : model.entrySet()) {
      influenceModel.put(entry.getKey(), DECIMAL_FORMAT.format(entry.getValue()));
    }
    this.sortInfluenceModel(influenceModel);
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

  private Set<Set<String>> getTerms(Set<PrettyLocalInfluenceModel> models) {
    Set<Set<String>> terms = new HashSet<>();
    for (PrettyLocalInfluenceModel model : models) {
      Set<Set<String>> modelTerms = new HashSet<>(model.getInfluenceModel().keySet());
      modelTerms.remove(TRUE_TERM);
      terms.addAll(modelTerms);
    }
    return terms;
  }

  private Set<PrettyLocalInfluenceModel> getLocalModels() throws IOException {
    File rootDir =
        new File(
            PrettyInfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + this.getProgramName()
                + "/"
                + this.measuredTime);
    Collection<File> modelFiles = FileUtils.listFiles(rootDir, new String[] {"json"}, false);
    Set<PrettyLocalInfluenceModel> models = new HashSet<>();
    for (File modelFile : modelFiles) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
      Map<String, Object> readLocalModel =
          mapper.readValue(modelFile, new TypeReference<Map<String, Object>>() {});
      Map<String, String> readInfluenceModel =
          (Map<String, String>) readLocalModel.get("influenceModel");
      LinkedHashMap<Set<String>, String> influenceModel =
          this.getInfluenceModel(readInfluenceModel);
      PrettyLocalInfluenceModel prettyModel =
          new PrettyLocalInfluenceModel(
              UUID.fromString((String) readLocalModel.get("region")),
              "",
              "",
              "",
              influenceModel,
              new LinkedHashMap<>());
      models.add(prettyModel);
    }
    return models;
  }

  private LinkedHashMap<Set<String>, String> getInfluenceModel(Map<String, String> model) {
    LinkedHashMap<Set<String>, String> influenceModel = new LinkedHashMap<>();
    for (Map.Entry<String, String> entry : model.entrySet()) {
      Set<String> term = this.getTerm(entry.getKey());
      term.remove("");
      influenceModel.put(term, entry.getValue());
    }
    return influenceModel;
  }

  private Set<String> getTerm(String entry) {
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
  public void writeToFile(PrettyGlobalInfluenceModel influenceModel) throws IOException {
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
  public PrettyGlobalInfluenceModel readFromFile(File file) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR
        + "/"
        + this.getProgramName()
        + "/"
        + this.measuredTime
        + "/"
        + this.getProgramName()
        + Options.DOT_JSON;
  }
}
