package edu.cmu.cs.mvelezce.lc.perf.model.model.idta.RunBenchC;

import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.PerformanceModel;
import edu.cmu.cs.mvelezce.lc.perf.model.model.idta.IDTAPerformanceModel;

import java.util.Set;

public class RunBenchCIDTAPerformanceModel<T> extends IDTAPerformanceModel<T> {

  //  private static final double INTERCEPT = 0;
  //  private static final double SLOPE = 1;
  //  private static final double R_SQUARED = 0;

  // All configs
  //  private static final double INTERCEPT = -0.3903;
  //  private static final double SLOPE = 0.9944;
  //  private static final double R_SQUARED = 0.9999;

  // 5 random configs
  //  private static final double INTERCEPT = -0.3006;
  //  private static final double SLOPE = 0.9833;
  //  private static final double R_SQUARED = 0.9999;

  //  private static final double INTERCEPT = -0.2817;
  //  private static final double SLOPE = 0.9837;
  //  private static final double R_SQUARED = 0.9999;

  //    private static final double INTERCEPT = -0.4400;
  //    private static final double SLOPE = 0.9967;
  //    private static final double R_SQUARED = 0.9999;

  //    private static final double INTERCEPT = -0.3896;
  //    private static final double SLOPE = 0.9936;
  //    private static final double R_SQUARED = 0.9999;

  private static final double INTERCEPT = -0.4231;
  private static final double SLOPE = 0.9997;
  private static final double R_SQUARED = 0.9999;

  public RunBenchCIDTAPerformanceModel(Set<LocalPerformanceModel<T>> localModels) {
    super(localModels, INTERCEPT, SLOPE);
  }

  public static <T> RunBenchCIDTAPerformanceModel<T> toRunBenchCIDTAPerformanceModel(
      PerformanceModel<T> performanceModel) {
    return new RunBenchCIDTAPerformanceModel<>(performanceModel.getLocalModels());
  }
}
