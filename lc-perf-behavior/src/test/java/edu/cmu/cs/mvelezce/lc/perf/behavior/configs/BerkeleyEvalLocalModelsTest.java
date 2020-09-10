package edu.cmu.cs.mvelezce.lc.perf.behavior.configs;

import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
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

public class BerkeleyEvalLocalModelsTest {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");

  @Test
  public void eval() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    List<String> options = BaseMeasureDiskOrderedScanAdapter.getListOfOptions();
    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
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
    options.add("TEMPORARY");
    options.add("SEQUENTIAL");
    options.add("MAX_MEMORY");
    options.add("DUPLICATES");
    options.add("CACHE_MODE");

    return options;
  }

  private List<Set<String>> getConfigs() {
    List<Set<String>> configs = new ArrayList<>();

    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_MEMORY");
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_MEMORY");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("MAX_MEMORY");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("MAX_MEMORY");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("CACHE_MODE");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    config.add("SEQUENTIAL");
    config.add("MAX_MEMORY");
    config.add("DUPLICATES");
    config.add("CACHE_MODE");
    configs.add(config);

    return configs;
  }
}
