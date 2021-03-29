package edu.cmu.cs.mvelezce.lc.vs.extension.global.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class ModelForConfigGeneratorTest {

  @Test
  void main() throws IOException, ParseException {
    Set<String> configs = new HashSet<>();
    configs.add("default");
    configs.add("user");
    configs.add("miguel");

    String programName = "Main";
    ModelForConfigGenerator generator = new ModelForConfigGenerator(programName, configs);
    generator.generate();
  }

  @Test
  void convert() throws IOException, ParseException {
    Set<String> configs = new HashSet<>();
    configs.add("default");
    configs.add("user");

    String programName = "Convert";
    ModelForConfigGenerator generator = new ModelForConfigGenerator(programName, configs);
    generator.generate();
  }
}
