package edu.cmu.cs.mvelezce.lc.perf.profile.viz.vs;

import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class VSViewerTest {

  @Test
  public void MooInfluence() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    VSViewer viewer = new VSViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void MooInfluence2() throws IOException {
    String programName = "MooInfluence2";
    VSViewer viewer = new VSViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void Convert2() throws IOException {
    String programName = "Convert2";
    VSViewer viewer = new VSViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }
}
