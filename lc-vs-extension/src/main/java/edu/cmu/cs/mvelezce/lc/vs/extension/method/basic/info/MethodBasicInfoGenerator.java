package edu.cmu.cs.mvelezce.lc.vs.extension.method.basic.info;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorEntry;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorHotspotParser;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class MethodBasicInfoGenerator implements Analysis<Map<String, Map<String, Double>>> {

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/basic/method/info/java/programs";

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  private final String programName;
  private final String defaultConfig;
  private final String reportConfig;

  public MethodBasicInfoGenerator(String programName, String defaultConfig, String reportConfig) {
    this.programName = programName;
    this.defaultConfig = defaultConfig;
    this.reportConfig = reportConfig;
  }

  @Override
  public Map<String, Map<String, Double>> analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);

    Map<String, Map<String, Double>> result = this.analyze();
    if (Options.checkIfSave()) {
      this.writeToFile(result);
    }

    return result;
  }

  @Override
  public Map<String, Map<String, Double>> analyze() throws IOException {
    File snapshotsDir =
        new File(
            "../lc-perf-profile-viz/" + TabulatorHotspotParser.OUTPUT_DIR + "/" + this.programName);
    Collection<File> tabulatorEntryFiles =
        FileUtils.listFiles(snapshotsDir, new String[] {"json"}, false);

    // Initialize optionsToProfilerEntries
    Map<String, List<TabulatorEntry>> configsToProfilerEntries = new HashMap<>();
    for (File tabularEntryFile : tabulatorEntryFiles) {
      configsToProfilerEntries.put(tabularEntryFile.getName(), new ArrayList<>());
    }

    for (File tabularEntryFile : tabulatorEntryFiles) {
      List<TabulatorEntry> profilerEntries =
          configsToProfilerEntries.get(tabularEntryFile.getName());
      profilerEntries.addAll(this.parseProfilerEntries(tabularEntryFile));
    }

    Map<String, Map<String, Double>> methodsBasicInfos = new HashMap<>();
    for (Map.Entry<String, List<TabulatorEntry>> entry : configsToProfilerEntries.entrySet()) {
      Map<String, Double> methods2Times = new HashMap<>();
      for (TabulatorEntry tabulatorEntry : entry.getValue()) {
        methods2Times.put(tabulatorEntry.getMethod(), tabulatorEntry.getTime());
      }
      methodsBasicInfos.put(entry.getKey(), methods2Times);
    }

    return methodsBasicInfos;
  }

  private List<TabulatorEntry> parseProfilerEntries(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(file, new TypeReference<List<TabulatorEntry>>() {});
  }

  @Override
  public void writeToFile(Map<String, Map<String, Double>> result) throws IOException {
    Set<String> methods = this.getAllMethods(result.values());
    Map<String, Double> methods2DefaultTimes =
        this.getMethodsToTimes(methods, result, this.defaultConfig);
    Map<String, Double> method2ReportTimes =
        this.getMethodsToTimes(methods, result, this.reportConfig);
    Set<String> methodsToInclude = new HashSet<>();
    methodsToInclude.addAll(this.getMethodsToInclude(methods2DefaultTimes));
    methodsToInclude.addAll(this.getMethodsToInclude(method2ReportTimes));
    String methodsBasicInfo =
        this.getMethodsBasicInfo(methodsToInclude, methods2DefaultTimes, method2ReportTimes);

    String outputFile = this.outputDir() + "/" + this.programName + ".csv";
    File file = new File(outputFile);
    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent file dirs");
    }
    FileWriter fileWriter = new FileWriter(outputFile);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(methodsBasicInfo);
    printWriter.close();
  }

  private String getMethodsBasicInfo(
      Set<String> methodsToInclude,
      Map<String, Double> methods2DefaultTimes,
      Map<String, Double> method2ReportTimes) {
    StringBuilder result = new StringBuilder();
    for (String method : methodsToInclude) {
      String methodName = method.substring(0, method.indexOf("("));
      result.append(methodName);
      result.append(",");
      result.append(DECIMAL_FORMAT.format(methods2DefaultTimes.get(method)));
      result.append(" seconds");
      result.append(",");
      result.append(DECIMAL_FORMAT.format(method2ReportTimes.get(method)));
      result.append("\n");
    }
    return result.toString();
  }

  private Set<String> getMethodsToInclude(Map<String, Double> methods2Times) {
    Set<String> methodsToInclude = new HashSet<>();
    for (Map.Entry<String, Double> entry : methods2Times.entrySet()) {
      if (entry.getValue() >= 0.01) {
        methodsToInclude.add(entry.getKey());
      }
    }
    return methodsToInclude;
  }

  private Map<String, Double> getMethodsToTimes(
      Set<String> methods, Map<String, Map<String, Double>> methodsBasicInfo, String config) {
    Map<String, Double> methodsToDefaultTimes = new HashMap<>();
    for (String method : methods) {
      methodsToDefaultTimes.put(method, 0.0);
    }

    for (Map.Entry<String, Map<String, Double>> methodBasicInfo : methodsBasicInfo.entrySet()) {
      String configEntry = methodBasicInfo.getKey();
      configEntry = configEntry.substring(0, configEntry.indexOf("."));
      if (!configEntry.equals(config)) {
        continue;
      }

      for (Map.Entry<String, Double> entry : methodBasicInfo.getValue().entrySet()) {
        methodsToDefaultTimes.put(entry.getKey(), entry.getValue());
      }
    }
    return methodsToDefaultTimes;
  }

  private Set<String> getAllMethods(Collection<Map<String, Double>> allHotspots) {
    Set<String> methods = new HashSet<>();
    for (Map<String, Double> hotspot : allHotspots) {
      methods.addAll(hotspot.keySet());
    }
    return methods;
  }

  @Override
  public Map<String, Map<String, Double>> readFromFile(File file) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }
}
