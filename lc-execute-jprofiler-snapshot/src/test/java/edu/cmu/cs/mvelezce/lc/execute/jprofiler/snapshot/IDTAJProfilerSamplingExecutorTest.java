package edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.compress.BaseCompression;
import edu.cmu.cs.mvelezce.java.execute.Executor;
import edu.cmu.cs.mvelezce.lc.compress.stack.analysis.StackAnalysisConfigs;
import edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot.executor.JProfilerSamplingExecutor;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class IDTAJProfilerSamplingExecutorTest {

  @Test
  public void berkeleyDb() throws IOException, InterruptedException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName);
    String[] args = new String[0];
    Set<Set<String>> configs = compression.analyze(args);

    args = new String[3];
    args[0] = "-delres";
    args[1] = "-saveres";
    args[2] = "-i1";
    Executor executor = new JProfilerSamplingExecutor(programName, configs, 30000);
    executor.execute(args);
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    System.err.println(
        "Ignoring hotspot 'org.apache.lucene.core.util.BytesRefHash.add(org.apache.lucene.core.util.BytesRef)' "
            + "since it was taking different amounts of time in config "
            + "[MAX_BUFFERED_DOCS,USE_COMPOUND_FILE,CHECK_PENDING_FLUSH_UPDATE,MAX_TOKEN_LENGTH,COMMIT_ON_CLOSE]");
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName);
    String[] args = new String[0];
    Set<Set<String>> configs = compression.analyze(args);

    args = new String[3];
    args[0] = "-delres";
    args[1] = "-saveres";
    args[2] = "-i1";
    Executor executor = new JProfilerSamplingExecutor(programName, configs, 30000);
    executor.execute(args);
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName);
    String[] args = new String[0];
    Set<Set<String>> configs = compression.analyze(args);

    args = new String[3];
    args[0] = "-delres";
    args[1] = "-saveres";
    args[2] = "-i1";
    Executor executor = new JProfilerSamplingExecutor(programName, configs, 30000);
    executor.execute(args);
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    System.err.println(
        "Ignoring hotspot 'org.h2.util.MathUtils$1.run()' since it was executing for the majority of the time in "
            + "most configs. This time was being attributed to the base time, which was wrong");
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName);
    String[] args = new String[0];
    Set<Set<String>> configs = compression.analyze(args);

    args = new String[3];
    args[0] = "-delres";
    args[1] = "-saveres";
    args[2] = "-i1";
    Executor executor = new JProfilerSamplingExecutor(programName, configs, 30000);
    executor.execute(args);
  }
}
