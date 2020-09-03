package edu.cmu.cs.mvelezce.lc.perf.behavior;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public abstract class AbstractCSVConfigGenerator {

  protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

  private final String programName;
  private final List<String> options;
  private final PerformanceModel<Partition> model;
  private final String measuredTime;

  protected AbstractCSVConfigGenerator(
      String programName,
      List<String> options,
      PerformanceModel<Partition> model,
      String measuredTime) {
    this.programName = programName;
    this.options = options;
    this.model = model;
    this.measuredTime = measuredTime;
  }

  public abstract void generateCSVFile() throws IOException;

  protected String getProgramName() {
    return programName;
  }

  protected List<String> getOptions() {
    return options;
  }

  protected PerformanceModel<Partition> getModel() {
    return model;
  }

  protected String getMeasuredTime() {
    return measuredTime;
  }
}
