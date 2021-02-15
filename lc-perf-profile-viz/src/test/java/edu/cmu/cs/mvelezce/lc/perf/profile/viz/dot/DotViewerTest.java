package edu.cmu.cs.mvelezce.lc.perf.profile.viz.dot;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class DotViewerTest {

  @Test
  public void MooInfluence2() throws IOException {
    String programName = "MooInfluence2";
    DotViewer viewer = new DotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void MooInfluence() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    DotViewer viewer = new DotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void Convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    DotViewer viewer = new DotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void Convert2() throws IOException {
    String programName = "Convert2";
    DotViewer viewer = new DotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }
}
