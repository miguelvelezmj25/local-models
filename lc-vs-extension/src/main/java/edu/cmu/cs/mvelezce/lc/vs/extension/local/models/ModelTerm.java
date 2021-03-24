package edu.cmu.cs.mvelezce.lc.vs.extension.local.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ModelTerm {

  private final Map<String, AbstractMap.SimpleEntry<String, String>> options;
  private final String sign;
  private final double value;

  public ModelTerm(
      Map<String, AbstractMap.SimpleEntry<String, String>> options, String sign, double value) {
    this.options = options;
    this.sign = sign;
    this.value = value;
  }

  public static ModelTerm from(JSONObject modelTermRaw) {
    JSONArray optionsRaw = (JSONArray) modelTermRaw.get("options");
    Map<String, AbstractMap.SimpleEntry<String, String>> options = new HashMap<>();
    for (Object o : optionsRaw) {
      JSONObject optionRaw = (JSONObject) o;
      String option = String.valueOf(optionRaw.get("option"));
      AbstractMap.SimpleEntry<String, String> values =
          new AbstractMap.SimpleEntry<>(
              String.valueOf(optionRaw.get("from")), String.valueOf(optionRaw.get("to")));
      options.put(option, values);
    }
    return new ModelTerm(
        options,
        String.valueOf(modelTermRaw.get("sign")),
        Double.parseDouble(String.valueOf(modelTermRaw.get("value"))));
  }

  public Map<String, AbstractMap.SimpleEntry<String, String>> getOptions() {
    return options;
  }

  public String getSign() {
    return sign;
  }

  public double getValue() {
    return value;
  }
}
