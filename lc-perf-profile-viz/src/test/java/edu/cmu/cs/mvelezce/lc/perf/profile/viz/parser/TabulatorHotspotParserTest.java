package edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.lc.adapters.mooInfluence.BaseMooInfluenceAdapter;
import org.junit.Test;

import java.io.IOException;

public class TabulatorHotspotParserTest {

  @Test
  public void MooInfluence() throws IOException {
    String programName = BaseMooInfluenceAdapter.PROGRAM_NAME;
    TabulatorHotspotParser parser = new TabulatorHotspotParser(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    parser.analyze(args);
  }

  @Test
  public void Convert() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    TabulatorHotspotParser parser = new TabulatorHotspotParser(programName);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    parser.analyze(args);
  }
}
