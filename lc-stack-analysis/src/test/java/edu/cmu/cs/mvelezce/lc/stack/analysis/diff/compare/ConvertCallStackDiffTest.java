package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;

public class ConvertCallStackDiffTest {

  @Test
  public void fireProgressChanged() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SCALE",
            "!SCALE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SCALE",
            "!SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SCALE",
            "!SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void compressJpeg() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SCALE",
            "!SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    differ.diff();
  }

  @Test
  public void scale() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SCALE",
            "!SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }
}
