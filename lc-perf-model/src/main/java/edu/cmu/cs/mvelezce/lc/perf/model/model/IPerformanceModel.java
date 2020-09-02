package edu.cmu.cs.mvelezce.lc.perf.model.model;

import java.util.List;
import java.util.Set;

public interface IPerformanceModel<T> {

  Set<LocalPerformanceModel<T>> getLocalModels();

  long evaluate(Set<String> config, List<String> options);
}
