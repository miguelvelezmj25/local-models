package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PrettyLocalInfluenceModelRead {

  private final UUID region;
  private final String packageName;
  private final String className;
  private final String methodSignature;

  @JsonDeserialize(using = PrettyLocalInfluenceModelMapDeserializer.class)
  private final Map<Set<String>, String> influenceModel;

  @JsonDeserialize(using = PrettyLocalInfluenceModelMapDeserializer.class)
  private final Map<Set<String>, String> executions;

  public PrettyLocalInfluenceModelRead(
      UUID region,
      String packageName,
      String className,
      String methodSignature,
      Map<Set<String>, String> influenceModel,
      Map<Set<String>, String> executions) {
    this.region = region;
    this.packageName = packageName;
    this.className = className;
    this.methodSignature = methodSignature;
    this.influenceModel = influenceModel;
    this.executions = executions;
  }

  // Private constructor for jackson xml
  private PrettyLocalInfluenceModelRead() {
    this.region = UUID.randomUUID();
    this.packageName = "";
    this.className = "";
    this.methodSignature = "";
    this.influenceModel = new LinkedHashMap<>();
    this.executions = new LinkedHashMap<>();
  }

  public UUID getRegion() {
    return region;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getClassName() {
    return className;
  }

  public String getMethodSignature() {
    return methodSignature;
  }

  public Map<Set<String>, String> getInfluenceModel() {
    return influenceModel;
  }

  public Map<Set<String>, String> getExecutions() {
    return executions;
  }
}
