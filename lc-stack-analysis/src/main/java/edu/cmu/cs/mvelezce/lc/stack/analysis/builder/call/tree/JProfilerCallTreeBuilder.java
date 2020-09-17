package edu.cmu.cs.mvelezce.lc.stack.analysis.builder.call.tree;

import com.mijecu25.meme.utils.execute.Executor;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JProfilerCallTreeBuilder implements Analysis<Object> {

  private static final String OUTPUT_DIR = Options.DIRECTORY + "/jprofiler/java/programs";
  private static final String SNAPSHOTS_ROOT =
      "../lc-profiler-analysis/src/main/resources/jprofiler/snapshots/java/programs/";
  private static final String JPROFILER_11_EXPORT_CMD_OSX =
      "/Applications/JProfiler.app/Contents/Resources/app/bin/jpexport";

  private final String programName;

  public JProfilerCallTreeBuilder(String programName) {
    this.programName = programName;
  }

  @Override
  public Object analyze(String[] args) throws IOException, InterruptedException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    if (file.exists()) {
      return this.readFromFile(file);
    }

    Object result = this.analyze();
    if (Options.checkIfSave()) {
      this.writeToFile(result);
    }
    return result;
  }

  @Override
  public Object analyze() throws IOException, InterruptedException {
    File snapshotsDir = new File(SNAPSHOTS_ROOT + "/" + this.programName);
    Collection<File> snapshots = FileUtils.listFiles(snapshotsDir, new String[] {"jps"}, false);
    for (File snapshot : snapshots) {
      this.export(snapshot.getPath());
    }

    throw new UnsupportedOperationException("implement");
  }

  public void export(String snapshotFile) throws IOException, InterruptedException {
    List<String> commandList = new ArrayList<>();
    commandList.add(JPROFILER_11_EXPORT_CMD_OSX);
    commandList.add(snapshotFile);
    commandList.add("CallTree");
    commandList.add("-aggregation=method");
    commandList.add("-viewmode=tree");
    commandList.add("-threadstatus=running");
    commandList.add("-format=xml");
    commandList.add("calltree.xml");
    Executor.executeCommand(commandList);
  }

  @Override
  public void writeToFile(Object o) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public Object readFromFile(File file) {
    throw new UnsupportedOperationException("implement");
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }
}
