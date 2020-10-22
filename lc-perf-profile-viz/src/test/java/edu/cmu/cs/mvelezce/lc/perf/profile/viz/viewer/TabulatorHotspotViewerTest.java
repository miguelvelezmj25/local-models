package edu.cmu.cs.mvelezce.lc.perf.profile.viz.viewer;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;

public class TabulatorHotspotViewerTest {

  @Test
  public void Convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    TabulatorHotspotViewer viewer = new TabulatorHotspotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }
}
