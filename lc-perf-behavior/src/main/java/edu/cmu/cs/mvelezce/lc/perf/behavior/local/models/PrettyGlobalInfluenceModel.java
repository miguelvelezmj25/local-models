package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import java.util.LinkedHashMap;
import java.util.Set;

public class PrettyGlobalInfluenceModel {

  private final LinkedHashMap<Set<String>, String> influenceModel;

  public PrettyGlobalInfluenceModel(LinkedHashMap<Set<String>, String> influenceModel) {
    this.influenceModel = influenceModel;
  }

  // Private constructor for jackson xml
  private PrettyGlobalInfluenceModel() {
    this.influenceModel = new LinkedHashMap<>();
  }

  public LinkedHashMap<Set<String>, String> getInfluenceModel() {
    return influenceModel;
  }
}
