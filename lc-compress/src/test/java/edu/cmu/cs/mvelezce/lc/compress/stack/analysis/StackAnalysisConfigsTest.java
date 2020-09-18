package edu.cmu.cs.mvelezce.lc.compress.stack.analysis;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.compress.BaseCompression;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StackAnalysisConfigsTest {

  @Test
  public void berkeleyDb() throws IOException, InterruptedException {
    Set<Set<String>> configs = new HashSet<>();
    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("TEMPORARY");
    configs.add(config);

    config = new HashSet<>();
    config.add("SEQUENTIAL");
    configs.add(config);

    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName, configs);

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    configs = compression.analyze(args);
    System.out.println("Configs " + configs.size());
  }

  @Test
  public void lucene() throws IOException, InterruptedException {
    Set<Set<String>> configs = new HashSet<>();
    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_BUFFERED_DOCS");
    configs.add(config);

    config = new HashSet<>();
    config.add("MAX_TOKEN_LENGTH");
    configs.add(config);

    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName, configs);

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    configs = compression.analyze(args);
    System.out.println("Configs " + configs.size());
  }

  @Test
  public void convert() throws IOException, InterruptedException {
    Set<Set<String>> configs = new HashSet<>();
    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("SCALE");
    configs.add(config);

    config = new HashSet<>();
    config.add("PLATFORM");
    configs.add(config);

    String programName = BaseConvertAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName, configs);

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    configs = compression.analyze(args);
    System.out.println("Configs " + configs.size());
  }

  @Test
  public void runBenchC() throws IOException, InterruptedException {
    Set<Set<String>> configs = new HashSet<>();
    Set<String> config = new HashSet<>();
    configs.add(config);

    config = new HashSet<>();
    config.add("DEFRAG_ALWAYS");
    configs.add(config);

    config = new HashSet<>();
    config.add("MV_STORE");
    configs.add(config);

    config = new HashSet<>();
    config.add("PAGE_SIZE");
    configs.add(config);

    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    BaseCompression compression = new StackAnalysisConfigs(programName, configs);

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    configs = compression.analyze(args);
    System.out.println("Configs " + configs.size());
  }
}
