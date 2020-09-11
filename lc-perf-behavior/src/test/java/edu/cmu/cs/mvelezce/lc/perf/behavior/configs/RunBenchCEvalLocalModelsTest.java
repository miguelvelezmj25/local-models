package edu.cmu.cs.mvelezce.lc.perf.behavior.configs;

import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.behavior.local.models.LocalModelFilterer;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RunBenchCEvalLocalModelsTest {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");

  @Test
  public void eval() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    List<String> options = BaseRunBenchCAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      System.out.println("Region " + localModel.getRegion());
      for (Set<String> config : getConfigs()) {
        double time = localModel.evaluate(config, options);
        System.out.println(
            "Config "
                + getPrettyConfig(config)
                + " "
                + DECIMAL_FORMAT.format(1.0 * time / 1E9)
                + "s");
      }
      System.out.println();
    }
  }

  private String getPrettyConfig(Set<String> config) {
    StringBuilder prettyConfig = new StringBuilder("[");
    for (String option : getOptions()) {
      if (config.contains(option)) {
        prettyConfig.append(option).append(",");
      }
    }
    prettyConfig.append("]");
    return prettyConfig.toString();
  }

  private List<String> getOptions() {
    List<String> options = new ArrayList<>();
    options.add("MV_STORE");
    options.add("PAGE_SIZE");
    options.add("DEFRAG_ALWAYS");

    return options;
  }

  private List<Set<String>> getConfigs() {
    List<Set<String>> configs = new ArrayList<>();

    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("DEFRAG_ALWAYS");
    configs.add(config);

    config = new HashSet<>();
    config.add("PAGE_SIZE");
    configs.add(config);

    config = new HashSet<>();
    config.add("PAGE_SIZE");
    config.add("DEFRAG_ALWAYS");
    configs.add(config);

    config = new HashSet<>();
    config.add("MV_STORE");
    configs.add(config);

    config = new HashSet<>();
    config.add("MV_STORE");
    config.add("DEFRAG_ALWAYS");
    configs.add(config);

    config = new HashSet<>();
    config.add("MV_STORE");
    config.add("PAGE_SIZE");
    configs.add(config);

    config = new HashSet<>();
    config.add("MV_STORE");
    config.add("PAGE_SIZE");
    config.add("DEFRAG_ALWAYS");
    configs.add(config);

    return configs;
  }
}
