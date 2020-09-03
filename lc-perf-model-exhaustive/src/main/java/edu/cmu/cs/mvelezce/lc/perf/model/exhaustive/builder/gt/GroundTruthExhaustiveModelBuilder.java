package edu.cmu.cs.mvelezce.lc.perf.model.exhaustive.builder.gt;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.java.results.processed.PerformanceEntry;
import edu.cmu.cs.mvelezce.lc.perf.model.exhaustive.builder.ExhaustiveModelBuilder;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.utils.config.Options;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroundTruthExhaustiveModelBuilder extends ExhaustiveModelBuilder {

  private static final String OUTPUT_DIR =
      "../lc-perf-model-exhaustive/" + Options.DIRECTORY + "/model/java/programs/gt";

  private final int id;

  public GroundTruthExhaustiveModelBuilder(String programName, String measuredTime) {
    this(programName, new ArrayList<>(), new HashSet<>(), measuredTime);
  }

  GroundTruthExhaustiveModelBuilder(
      String programName, List<String> options, Set<PerformanceEntry> performanceEntries, int id) {
    super(programName, options, performanceEntries, BaseExecutor.REAL);

    this.id = id;
  }

  GroundTruthExhaustiveModelBuilder(
      String programName,
      List<String> options,
      Set<PerformanceEntry> performanceEntries,
      String measuredTime) {
    super(programName, options, performanceEntries, measuredTime);

    this.id = -1;
  }

  @Override
  public void writeToFile(PerformanceModel<Partition> results) throws IOException {
    if (this.id < 0) {
      super.writeToFile(results);
    } else {
      String outputFile =
          this.outputDir() + this.id + "/" + this.getProgramName() + this.id + Options.DOT_JSON;
      File file = new File(outputFile);
      file.getParentFile().mkdirs();

      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(file, results);
    }
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.getProgramName() + "/" + this.getMeasuredTime();
  }
}
