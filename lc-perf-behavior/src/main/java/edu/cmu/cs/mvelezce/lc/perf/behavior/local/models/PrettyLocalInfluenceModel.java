package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

public class PrettyLocalInfluenceModel {

  private final UUID region;
  private final String packageName;
  private final String className;
  private final String methodSignature;
  private final LinkedHashMap<Set<String>, String> influenceModel;
  private final LinkedHashMap<Set<String>, String> executions;

  public PrettyLocalInfluenceModel(
      UUID region,
      String packageName,
      String className,
      String methodSignature,
      LinkedHashMap<Set<String>, String> influenceModel,
      LinkedHashMap<Set<String>, String> executions) {
    this.region = region;
    this.packageName = packageName;
    this.className = className;
    this.methodSignature = methodSignature;
    this.influenceModel = influenceModel;
    this.executions = executions;
  }

  // Private constructor for jackson xml
  private PrettyLocalInfluenceModel() {
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

  public LinkedHashMap<Set<String>, String> getInfluenceModel() {
    return influenceModel;
  }

  public LinkedHashMap<Set<String>, String> getExecutions() {
    return executions;
  }
}
