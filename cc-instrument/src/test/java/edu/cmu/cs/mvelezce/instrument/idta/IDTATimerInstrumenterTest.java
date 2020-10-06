package edu.cmu.cs.mvelezce.instrument.idta;

import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.analysis.idta.IDTAAnalysis;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.transform.instrumentation.time.IDTAExecutionTimeMethodInstrumenter;
import edu.cmu.cs.mvelezce.instrumenter.instrument.Instrumenter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence.BaseBarInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.barInfluence2.BaseBarInfluence2Adapter;
import edu.cmu.cs.mvelezce.lc.adapters.diffStacks.BaseDiffStacksAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.dummyRegion.BaseDummyRegionAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.earlyReturn.BaseEarlyReturnAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.ifAMoo.BaseIfAMooAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.multiplePaths.BaseMultiplePathsAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.needSlicing.BaseNeedSlicingAdapter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IDTATimerInstrumenterTest {

  @Test
  public void BarInfluence()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseBarInfluenceAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseBarInfluenceAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseBarInfluenceAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseBarInfluenceAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseBarInfluenceAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void BarInfluence2()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseBarInfluence2Adapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseBarInfluence2Adapter.MAIN_CLASS;
    String srcDir = "../" + BaseBarInfluence2Adapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseBarInfluence2Adapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseBarInfluence2Adapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void DiffStacks()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseDiffStacksAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseDiffStacksAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseDiffStacksAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseDiffStacksAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseDiffStacksAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void DummyRegion()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseDummyRegionAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseDummyRegionAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseDummyRegionAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseDummyRegionAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseDummyRegionAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void EarlyReturn()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseEarlyReturnAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseEarlyReturnAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseEarlyReturnAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseEarlyReturnAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseEarlyReturnAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void IfAMoo()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseIfAMooAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseIfAMooAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseIfAMooAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseIfAMooAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseIfAMooAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void MooInfluence()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseMooInfluenceAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseMooInfluenceAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseMooInfluenceAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseMooInfluenceAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void MultiplePaths()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseMultiplePathsAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseMultiplePathsAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseMultiplePathsAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseMultiplePathsAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseMultiplePathsAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }

  @Test
  public void NeedSlicing()
      throws IOException, InterruptedException, NoSuchMethodException, IllegalAccessException,
          InvocationTargetException {
    String programName = BaseNeedSlicingAdapter.PROGRAM_NAME;
    String workloadSize = "small";

    Analysis<Map<JavaRegion, Partitioning>> analysis = new IDTAAnalysis(programName, workloadSize);
    Map<JavaRegion, Partitioning> regionsToPartitions = analysis.analyze();

    String mainClass = BaseNeedSlicingAdapter.MAIN_CLASS;
    String srcDir = "../" + BaseNeedSlicingAdapter.PHOSPHOR_ROOT_DIR;
    String classDir = "../" + BaseNeedSlicingAdapter.PHOSPHOR_CLASS_PATH;
    Set<String> options = new HashSet<>(BaseNeedSlicingAdapter.getListOfOptions());
    Instrumenter instrumenter =
        new IDTATimerInstrumenter(
            programName,
            mainClass,
            srcDir,
            classDir,
            options,
            regionsToPartitions,
            new IDTAExecutionTimeMethodInstrumenter());

    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";

    instrumenter.instrument(args);
  }
}
