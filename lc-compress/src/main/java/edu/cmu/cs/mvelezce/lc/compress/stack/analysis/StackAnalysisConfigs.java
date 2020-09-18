package edu.cmu.cs.mvelezce.lc.compress.stack.analysis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.cmu.cs.mvelezce.compress.BaseCompression;
import edu.cmu.cs.mvelezce.utils.config.Options;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StackAnalysisConfigs extends BaseCompression {

  private static final String OUTPUT_DIR =
      "../lc-compress/" + Options.DIRECTORY + "/compression/java/programs/stack/analysis";

  private final Set<Set<String>> configs;

  public StackAnalysisConfigs(String programName) {
    this(programName, new HashSet<>());
  }

  public StackAnalysisConfigs(String programName, Set<Set<String>> configs) {
    super(programName, new ArrayList<>());

    this.configs = configs;
  }

  @Override
  public Set<Set<String>> analyze() {
    return configs;
  }

  @Override
  public void writeToFile(Set<Set<String>> results) throws IOException {
    String outputFile = this.outputDir() + "/" + this.getProgramName() + ".json";
    File file = new File(outputFile);
    if (!file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent dirs");
    }
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.writeValue(file, results);
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName();
  }
}
