package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;

public class ConvertCallStackSelectorTest {

  @Test
  public void fireProgressChanged() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "!SCALE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    selector.select();
  }

  @Test
  public void verticalFromWorkToDst() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "!SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    selector.select();
  }
}
