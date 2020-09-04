package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

public class PrettyLocalInfluenceModel {

  private final UUID region;
  private final LinkedHashMap<Set<String>, String> influenceModel;

  public PrettyLocalInfluenceModel(UUID region, LinkedHashMap<Set<String>, String> influenceModel) {
    this.region = region;
    this.influenceModel = influenceModel;
  }

  // Private constructor for jackson xml
  private PrettyLocalInfluenceModel() {
    this.region = UUID.randomUUID();
    this.influenceModel = new LinkedHashMap<>();
  }

  public UUID getRegion() {
    return region;
  }

  public LinkedHashMap<Set<String>, String> getInfluenceModel() {
    return influenceModel;
  }
}
