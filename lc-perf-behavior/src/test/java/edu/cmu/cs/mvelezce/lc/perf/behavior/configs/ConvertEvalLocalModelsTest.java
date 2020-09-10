package edu.cmu.cs.mvelezce.lc.perf.behavior.configs;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.behavior.local.models.LocalModelFilterer;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.utils.configurations.ConfigHelper;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ConvertEvalLocalModelsTest {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");

  @Test
  public void eval() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    List<String> options = BaseConvertAdapter.getListOfOptions();
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
    options.add("SCALE");
    options.add("PLATFORM");
    options.add("SKIP_UPSCALING");
    options.add("ANTI_ALIASING");

    return options;
  }

  private Set<Set<String>> getConfigs() {
    Collection<String> options = getOptions();
    return ConfigHelper.getConfigurations(options);
  }
}
