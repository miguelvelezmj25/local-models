package edu.cmu.cs.mvelezce.lc.vs.extension.local.models;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LocalModelForConfigGeneratorTest {

  @Test
  void main() throws IOException, ParseException {
    Map<String, Set<String>> configs = new HashMap<>();
    Set<String> config = new HashSet<>();
    configs.put("default", config);

    config = new HashSet<>();
    config.add("CLEAN");
    config.add("DATA_SIZE");
    config.add("ROUNDS");
    configs.put("user", config);

    config = new HashSet<>();
    config.add("ROUNDS");
    configs.put("miguel", config);

    String programName = "Main";
    LocalModelForConfigGenerator generator = new LocalModelForConfigGenerator(programName, configs);
    generator.generate();
  }
}
