package edu.cmu.cs.mvelezce.lc.perf.profile.viz.hotspot;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class JProfilerHotspotExporterTest {

  @Test
  public void MooInfluence() throws IOException, InterruptedException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    JProfilerHotspotExporter builder =
        new JProfilerHotspotExporter(programName, JProfilerHotspotExporter.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }

  @Test
  public void Convert() throws IOException, InterruptedException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    JProfilerHotspotExporter builder =
        new JProfilerHotspotExporter(programName, JProfilerHotspotExporter.ALL_THREAD_STATUS);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    builder.analyze(args);
  }
}
