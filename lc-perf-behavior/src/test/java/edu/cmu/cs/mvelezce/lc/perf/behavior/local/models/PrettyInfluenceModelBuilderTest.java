package edu.cmu.cs.mvelezce.lc.perf.behavior.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PrettyInfluenceModelBuilderTest {

  @Test
  public void berkeleyDB() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    File root =
        new File(
            PrettyInfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + programName
                + "/"
                + BaseExecutor.REAL
                + "/");
    if (root.exists()) {
      FileUtils.forceDelete(root);
    }

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      PrettyInfluenceModelBuilder builder =
          new PrettyInfluenceModelBuilder(
              programName, BaseExecutor.REAL, localModel.getRegion().toString(), 0.1);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      builder.analyze(args);
    }
  }

  @Test
  public void lucene() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    File root =
        new File(
            PrettyInfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + programName
                + "/"
                + BaseExecutor.REAL
                + "/");
    if (root.exists()) {
      FileUtils.forceDelete(root);
    }

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.REAL).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      PrettyInfluenceModelBuilder builder =
          new PrettyInfluenceModelBuilder(
              programName, BaseExecutor.REAL, localModel.getRegion().toString(), 0.1);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      builder.analyze(args);
    }
  }

  @Test
  public void convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    File root =
        new File(
            PrettyInfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + programName
                + "/"
                + BaseExecutor.USER
                + "/");
    if (root.exists()) {
      FileUtils.forceDelete(root);
    }

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      PrettyInfluenceModelBuilder builder =
          new PrettyInfluenceModelBuilder(
              programName, BaseExecutor.USER, localModel.getRegion().toString(), 0.1);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      builder.analyze(args);
    }
  }

  @Test
  public void runBenchC() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    File root =
        new File(
            PrettyInfluenceModelBuilder.OUTPUT_DIR
                + "/"
                + programName
                + "/"
                + BaseExecutor.USER
                + "/");
    if (root.exists()) {
      FileUtils.forceDelete(root);
    }

    LocalModelFilterer filterer =
        new LocalModelFilterer.Builder(programName, BaseExecutor.USER).build();
    String[] args = new String[0];
    Set<LocalPerformanceModel<Partition>> relevantLocalModels = filterer.analyze(args);

    for (LocalPerformanceModel<Partition> localModel : relevantLocalModels) {
      PrettyInfluenceModelBuilder builder =
          new PrettyInfluenceModelBuilder(
              programName, BaseExecutor.USER, localModel.getRegion().toString(), 0.1);
      args = new String[2];
      args[0] = "-delres";
      args[1] = "-saveres";
      builder.analyze(args);
    }
  }
}
