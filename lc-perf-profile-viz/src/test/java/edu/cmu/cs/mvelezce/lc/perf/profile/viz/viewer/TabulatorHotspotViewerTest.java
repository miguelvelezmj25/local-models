package edu.cmu.cs.mvelezce.lc.perf.profile.viz.viewer;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.java.execute.adapters.measureDiskOrderedScan.MeasureDiskOrderedScanExecutorAdapter;
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

  @Test
  public void Berkeley() throws IOException {
    String programName = MeasureDiskOrderedScanExecutorAdapter.PROGRAM_NAME;
    TabulatorHotspotViewer viewer = new TabulatorHotspotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }

  @Test
  public void Main() throws IOException {
    String programName = "Main";
    TabulatorHotspotViewer viewer = new TabulatorHotspotViewer(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    viewer.analyze(args);
  }
}
