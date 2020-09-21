package edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot.parser;

import com.mijecu25.meme.utils.execute.Executor;
import edu.cmu.cs.mvelezce.java.execute.adapters.ExecutorAdapter;
import edu.cmu.cs.mvelezce.java.execute.parser.BaseRawExecutionParser;
import edu.cmu.cs.mvelezce.java.results.sampling.raw.profiler.jprofiler.RawJProfilerSamplingPerfExecution;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JProfilerSamplingExecutionSnapshotParser
    extends BaseRawExecutionParser<RawJProfilerSamplingPerfExecution> {

  public static final String ROOT_DIR = "lc-execute-jprofiler-snapshot";
  public static final String OUTPUT_DIR = Options.DIRECTORY + "/jprofiler/snapshots/java/programs";
  public static final String SNAPSHOT_FILE = "snapshot.jps";

  public JProfilerSamplingExecutionSnapshotParser(String programName) {
    super(programName, "");
  }

  @Override
  public void logExecution(Set<String> config, int iter, ExecutorAdapter executorAdapter)
      throws IOException, InterruptedException {
    File outputDir = new File(OUTPUT_DIR + "/" + this.getProgramName());
    if (!outputDir.exists() && !outputDir.mkdirs()) {
      throw new UnsupportedEncodingException("Could not create dirs");
    }

    File snapshotFile = new File("./" + SNAPSHOT_FILE);
    List<String> commandList = buildCommandAsList(snapshotFile.getName(), outputDir, config);
    ProcessBuilder builder = new ProcessBuilder();
    builder.command(commandList);
    Process process = builder.start();
    Executor.processOutput(process);
    Executor.processError(process);
    process.waitFor();

    FileUtils.forceDelete(snapshotFile);
  }

  private static List<String> buildCommandAsList(
      String snapshotFileName, File outputDir, Set<String> config) {
    List<String> commandList = new ArrayList<>();
    commandList.add("cp");
    commandList.add(snapshotFileName);
    commandList.add(outputDir.getPath() + "/" + getFileName(config));
    return commandList;
  }

  private static String getFileName(Set<String> config) {
    if (config.isEmpty()) {
      return "FALSE.jps";
    }

    StringBuilder name = new StringBuilder();
    Iterator<String> iter = config.iterator();
    while (iter.hasNext()) {
      name.append(iter.next());
      if (iter.hasNext()) {
        name.append("-");
      }
    }
    name.append(".jps");
    return name.toString();
  }

  @Override
  protected RawJProfilerSamplingPerfExecution readFromFile(File file) {
    throw new UnsupportedOperationException("Implement read from file");
  }
}
