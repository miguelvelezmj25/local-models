package edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot.executor;

import edu.cmu.cs.mvelezce.java.execute.sampling.idta.profiler.jprofiler.IDTAJProfilerSamplingExecutor;
import edu.cmu.cs.mvelezce.java.execute.sampling.parser.profiler.jprofiler.RawJProfilerSamplingExecutionParser;
import edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot.parser.JProfilerSamplingExecutionSnapshotParser;

import java.util.Set;

public class JProfilerSamplingExecutor extends IDTAJProfilerSamplingExecutor {

  public JProfilerSamplingExecutor(
      String programName, Set<Set<String>> configs, int waitAfterExecution) {
    super(
        programName,
        configs,
        new JProfilerSamplingExecutionSnapshotParser(programName),
        waitAfterExecution,
        RawJProfilerSamplingExecutionParser.ALL_THREAD_STATUS);
  }

  public String outputDir() {
    return "../lc-execute-jprofiler-snapshot/src/main/resources/jprofiler/snapshots/java/programs";
  }
}
