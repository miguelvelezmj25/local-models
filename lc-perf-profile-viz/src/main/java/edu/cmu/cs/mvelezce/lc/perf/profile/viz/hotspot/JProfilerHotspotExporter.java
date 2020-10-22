package edu.cmu.cs.mvelezce.lc.perf.profile.viz.hotspot;

import com.mijecu25.meme.utils.execute.Executor;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JProfilerHotspotExporter implements Analysis<Void> {

  private static final String JPROFILER_11_EXPORT_CMD_OSX =
      "/Applications/JProfiler.app/Contents/Resources/app/bin/jpexport";
  private static final String SNAPSHOTS_ROOT =
      "../lc-execute-jprofiler-snapshot/src/main/resources/jprofiler/snapshots/java/programs/";

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/jprofiler/hotspots/java/programs";
  public static final String RUNNING_THREAD_STATUS = "running";
  public static final String ALL_THREAD_STATUS = "all";

  private final String programName;
  private final String threadStatus;

  public JProfilerHotspotExporter(String programName, String threadStatus) {
    this.programName = programName;
    this.threadStatus = threadStatus;
  }

  @Override
  public Void analyze(String[] args) throws IOException, InterruptedException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    return this.analyze();
  }

  @Override
  public Void analyze() throws IOException, InterruptedException {
    File outputRoot = new File(this.outputDir());
    if (!outputRoot.exists() && !outputRoot.mkdirs()) {
      throw new RuntimeException("Could not create parent file dirs");
    }

    File snapshotsDir = new File(SNAPSHOTS_ROOT + "/" + this.programName);
    Collection<File> snapshots = FileUtils.listFiles(snapshotsDir, new String[] {"jps"}, false);
    for (File snapshot : snapshots) {
      this.export(snapshot);
    }

    return null;
  }

  private void export(File snapshotFile) throws IOException, InterruptedException {
    List<String> commandList = new ArrayList<>();
    commandList.add(JPROFILER_11_EXPORT_CMD_OSX);
    commandList.add(snapshotFile.getPath());
    commandList.add("HotSpots");
    commandList.add("-format=xml");
    commandList.add("-aggregation=method");
    commandList.add("-threadstatus=" + this.threadStatus);
    commandList.add("-expandbacktraces=true");
    commandList.add("-valuesummation=self");
    commandList.add(
        this.outputDir() + "/" + FilenameUtils.removeExtension(snapshotFile.getName()) + ".xml");
    Executor.executeCommand(commandList);
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }

  @Override
  public void writeToFile(Void unused) {
    throw new UnsupportedOperationException("Method should not be called");
  }

  @Override
  public Void readFromFile(File file) {
    throw new UnsupportedOperationException("Method should not be called");
  }
}
