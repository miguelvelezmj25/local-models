package edu.cmu.cs.mvelezce.lc.vs.extension.local.models;

import com.google.common.collect.Sets;
import edu.cmu.cs.mvelezce.utils.configurations.ConfigHelper;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LocalModelForConfigGenerator {

  public static final String ROOT_DIR = "./src/main/resources/local/models/java/programs";

  private final String programName;
  private final Map<String, Set<String>> configs;

  public LocalModelForConfigGenerator(String programName, Map<String, Set<String>> configs) {
    this.programName = programName;
    this.configs = configs;
  }

  void generate() throws IOException, ParseException {
    Collection<File> modelFiles = this.getModelFiles();
    for (File modelFile : modelFiles) {
      JSONObject rawModel = (JSONObject) new JSONParser().parse(new FileReader(modelFile));
      Set<ModelTerm> modelTerms = this.getModelTerms(rawModel);

      Set<String> allOptions = this.getAllOptions(modelTerms);
      Set<Set<String>> allConfigs = ConfigHelper.getConfigurations(allOptions);
      double base = Double.parseDouble(String.valueOf(rawModel.get("base")));
      Map<Set<String>, Double> configs2Times = this.getConfigs2Time(base, modelTerms, allConfigs);

      Map<String, Map<Set<String>, Double>> configNames2Times =
          this.getConfigNames2Times(configs2Times, allOptions);
      this.save(configNames2Times, modelTerms, modelFile, allOptions);
    }
  }

  private void save(
      Map<String, Map<Set<String>, Double>> configNames2Times,
      Set<ModelTerm> modelTerms,
      File modelFile,
      Set<String> allOptions)
      throws IOException {
    Map<String, AbstractMap.SimpleEntry<String, String>> options = new HashMap<>();
    for (ModelTerm modelTerm : modelTerms) {
      options.putAll(modelTerm.getOptions());
    }

    JSONArray models = new JSONArray();
    for (Map.Entry<String, Map<Set<String>, Double>> entry : configNames2Times.entrySet()) {
      JSONObject model = new JSONObject();
      Set<String> config = this.configs.get(entry.getKey());
      Sets.SetView<String> selectedOptions = Sets.intersection(config, allOptions);
      JSONArray terms = new JSONArray();
      for (Map.Entry<Set<String>, Double> modelEntry : entry.getValue().entrySet()) {
        JSONObject termEntry = new JSONObject();
        JSONArray termEntries = new JSONArray();
        Sets.SetView<String> diff = Sets.symmetricDifference(selectedOptions, modelEntry.getKey());
        for (String option : allOptions) {
          if (!diff.contains(option)) {
            continue;
          }

          JSONObject optionEntry = new JSONObject();
          AbstractMap.SimpleEntry<String, String> values = options.get(option);
          if (selectedOptions.contains(option)) {
            optionEntry.put("option", option);
            optionEntry.put("from", values.getValue());
            optionEntry.put("to", values.getKey());
          } else {
            optionEntry.put("option", option);
            optionEntry.put("from", values.getKey());
            optionEntry.put("to", values.getValue());
          }
          termEntries.add(optionEntry);
        }
        termEntry.put("options", termEntries);
        termEntry.put("time", modelEntry.getValue());
        terms.add(termEntry);
      }

      model.put("name", entry.getKey());
      model.put("terms", terms);
      models.add(model);
    }

    JSONObject result = new JSONObject();
    result.put("models", models);

    PrintWriter printWriter =
        new PrintWriter(ROOT_DIR + "/" + this.programName + "/influence/" + modelFile.getName());
    printWriter.println(result.toJSONString());
    printWriter.flush();
    printWriter.close();
  }

  private Map<String, Map<Set<String>, Double>> getConfigNames2Times(
      Map<Set<String>, Double> configs2Times, Set<String> allOptions) {
    Map<String, Map<Set<String>, Double>> configs2Models = new HashMap<>();
    for (Map.Entry<String, Set<String>> entry : this.configs.entrySet()) {
      Set<String> config = entry.getValue();
      Sets.SetView<String> selectedOptions = Sets.intersection(config, allOptions);
      Map<Set<String>, Set<String>> allDiffConfigs = new HashMap<>();
      Map<Set<String>, Double> model = new HashMap<>();

      for (int i = 0; i <= Math.min(allOptions.size(), 2); i++) {
        //      for (int i = 0; i <= allOptions.size(); i++) {
        Map<Set<String>, Set<String>> diffConfigs =
            this.getDiffConfigs(selectedOptions, i, configs2Times.keySet());
        for (Map.Entry<Set<String>, Set<String>> diffConfigEntry : diffConfigs.entrySet()) {
          Set<String> diffConfig = diffConfigEntry.getValue();
          double influence = configs2Times.get(diffConfig);
          Set<String> difference = diffConfigEntry.getKey();

          for (Map.Entry<Set<String>, Set<String>> previousDiffConfig : allDiffConfigs.entrySet()) {
            if (!difference.containsAll(previousDiffConfig.getKey())) {
              continue;
            }
            influence -= model.get(previousDiffConfig.getValue());
          }
          model.put(diffConfig, influence);
        }

        allDiffConfigs.putAll(diffConfigs);
      }

      configs2Models.put(entry.getKey(), model);
    }

    return configs2Models;
  }

  private Map<Set<String>, Set<String>> getDiffConfigs(
      Set<String> config, int diffTerms, Set<Set<String>> configs) {
    Map<Set<String>, Set<String>> diffConfigs = new HashMap<>();
    if (diffTerms == 0) {
      diffConfigs.put(new HashSet<>(), new HashSet<>(config));
      return diffConfigs;
    }

    for (Set<String> candidate : configs) {
      Sets.SetView<String> diff = Sets.symmetricDifference(config, candidate);
      if (diff.size() == diffTerms) {
        diffConfigs.put(new HashSet<>(diff), candidate);
      }
    }
    return diffConfigs;
  }

  private Set<String> getAllOptions(Set<ModelTerm> modelTerms) {
    Set<String> allOptions = new HashSet<>();
    for (ModelTerm modelTerm : modelTerms) {
      allOptions.addAll(modelTerm.getOptions().keySet());
    }
    return allOptions;
  }

  private Map<Set<String>, Double> getConfigs2Time(
      double base, Set<ModelTerm> modelTerms, Set<Set<String>> allConfigs) {
    Map<Set<String>, Double> configs2Times = new HashMap<>();
    for (Set<String> config : allConfigs) {
      double time = base;
      for (ModelTerm modelTerm : modelTerms) {
        if (!config.containsAll(modelTerm.getOptions().keySet())) {
          continue;
        }

        if (modelTerm.getSign().equals("+")) {
          time += modelTerm.getValue();
        } else {
          time -= modelTerm.getValue();
        }
      }
      configs2Times.put(config, Math.max(0.0, time));
    }
    return configs2Times;
  }

  private Collection<File> getModelFiles() {
    File dir = new File(ROOT_DIR + "/" + this.programName + "/raw");
    return FileUtils.listFiles(dir, new String[] {"json"}, false);
  }

  private Set<ModelTerm> getModelTerms(JSONObject rawModel) {
    JSONArray model = (JSONArray) rawModel.get("model");
    Set<ModelTerm> modelTerms = new HashSet<>();
    for (Object m : model) {
      JSONObject modelTermRaw = (JSONObject) m;
      modelTerms.add(ModelTerm.from(modelTermRaw));
    }
    return modelTerms;
  }
}
