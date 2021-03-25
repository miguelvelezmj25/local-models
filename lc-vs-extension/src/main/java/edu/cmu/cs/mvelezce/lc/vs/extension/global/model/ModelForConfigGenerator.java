package edu.cmu.cs.mvelezce.lc.vs.extension.global.model;

import edu.cmu.cs.mvelezce.lc.vs.extension.local.models.LocalModelForConfigGenerator;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class ModelForConfigGenerator {

  public static final String ROOT_DIR = "./src/main/resources/global/models/java/programs";

  private final String programName;
  private final Set<String> configs;

  public ModelForConfigGenerator(String programName, Set<String> configs) {
    this.programName = programName;
    this.configs = configs;
  }

  void generate() throws IOException, ParseException {
    Map<String, Map<JSONArray, Double>> configNames2Models = new HashMap<>();
    Collection<File> localModelFiles = getLocalModelFiles();
    for (String config : this.configs) {
      List<JSONObject> terms = this.getLocalModel(localModelFiles, config);
      Map<JSONArray, Double> modelRaw = this.getModelRaw(terms);
      configNames2Models.put(config, modelRaw);
    }

    this.save(configNames2Models);
  }

  private void save(Map<String, Map<JSONArray, Double>> configNames2Models)
      throws FileNotFoundException {
    JSONArray models = new JSONArray();
    for (Map.Entry<String, Map<JSONArray, Double>> entry : configNames2Models.entrySet()) {
      JSONObject model = new JSONObject();
      JSONArray terms = new JSONArray();
      for (Map.Entry<JSONArray, Double> termEntries : entry.getValue().entrySet()) {
        JSONObject optionEntry = new JSONObject();
        optionEntry.put("options", termEntries.getKey());
        optionEntry.put("time", termEntries.getValue());
        terms.add(optionEntry);
      }

      model.put("name", entry.getKey());
      model.put("terms", terms);
      models.add(model);
    }

    JSONObject result = new JSONObject();
    result.put("models", models);

    PrintWriter printWriter =
        new PrintWriter(ROOT_DIR + "/" + this.programName + "/influence/perf-models.json");
    printWriter.println(result.toJSONString());
    printWriter.flush();
    printWriter.close();
  }

  private Map<JSONArray, Double> getModelRaw(List<JSONObject> terms) {
    Map<JSONArray, Double> modelRaw = new HashMap<>();
    for (JSONObject term : terms) {
      modelRaw.put((JSONArray) term.get("options"), 0.0);
    }

    for (JSONObject term : terms) {
      JSONArray options = (JSONArray) term.get("options");
      double time = modelRaw.get(options);
      time += Double.parseDouble(String.valueOf(term.get("time")));
      modelRaw.put((JSONArray) term.get("options"), time);
    }
    return modelRaw;
  }

  private List<JSONObject> getLocalModel(Collection<File> localModelFiles, String config)
      throws IOException, ParseException {
    List<JSONObject> terms = new ArrayList<>();
    for (File modelFile : localModelFiles) {
      JSONArray localModels =
          (JSONArray)
              ((JSONObject) new JSONParser().parse(new FileReader(modelFile))).get("models");
      for (Object lm : localModels) {
        JSONObject localModel = (JSONObject) lm;
        if (!localModel.get("name").equals(config)) {
          continue;
        }
        JSONArray termsRaw = (JSONArray) localModel.get("terms");
        terms.addAll(termsRaw);
      }
    }
    return terms;
  }

  //    JSONObject rawModel =
  //        (JSONObject)
  //            new JSONParser()
  //                .parse(new FileReader(RAW_DIR + "/" + this.programName + "/raw/raw.json"));
  //    double base = Double.parseDouble(String.valueOf(rawModel.get("base")));
  //    JSONArray model = (JSONArray) rawModel.get("model");
  //
  //    Map<Set<String>, Double> configs2Time = this.getConfigs2Time(base, model);
  //
  //    this.some();
  //
  //
  //
  //    Map<String, Double> config2Times = new HashMap<>();
  //    for (Map.Entry<String, Set<String>> entry : this.configs.entrySet()) {
  //      Set<String> config = entry.getValue();
  //      double time = base;
  //      for (Object o : model) {
  //        JSONObject term = (JSONObject) o;
  //        Set<String> options = new HashSet<>();
  //        JSONArray termOptions = (JSONArray) term.get("options");
  //        for (Object p : termOptions) {
  //          JSONObject termOption = (JSONObject) p;
  //          options.add(String.valueOf(termOption.get("option")));
  //        }
  //        if (!config.containsAll(options)) {
  //          continue;
  //        }
  //
  //        double influence = Double.parseDouble(String.valueOf(term.get("value")));
  //        if (term.get("sign").equals("+")) {
  //          time += influence;
  //        } else {
  //          time -= influence;
  //        }
  //      }
  //      config2Times.put(entry.getKey(), time);
  //    }

  private Collection<File> getLocalModelFiles() {
    File dir =
        new File(LocalModelForConfigGenerator.ROOT_DIR + "/" + this.programName + "/influence");
    return FileUtils.listFiles(dir, new String[] {"json"}, false);
  }

  private Map<Set<String>, Double> getConfigs2Time(double base, JSONArray model) {
    for (Object o : model) {
      JSONObject term = (JSONObject) o;
      JSONArray termOptions = (JSONArray) term.get("options");
      Set<String> options = new HashSet<>();
      for (Object p : termOptions) {
        JSONObject termOption = (JSONObject) p;
        options.add(String.valueOf(termOption.get("option")));
      }

      System.out.println();
    }
    throw new UnsupportedOperationException("todo");
  }
}
