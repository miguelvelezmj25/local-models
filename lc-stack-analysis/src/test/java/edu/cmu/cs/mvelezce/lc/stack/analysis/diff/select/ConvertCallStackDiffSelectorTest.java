package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.select;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;

public class ConvertCallStackDiffSelectorTest {

  @Test
  public void fireProgressChanged_SCALE_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "SCALE",
            "FALSE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    selector.select();
  }

  @Test
  public void verticalFromWorkToDst_SCALE_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "SCALE",
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    selector.select();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "SCALE",
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    selector.select();
  }

  @Test
  public void compressJpeg_SCALE_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "SCALE",
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    selector.select();
  }

  @Test
  public void scale_SCALE_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "SCALE",
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    selector.select();
  }

  @Test
  public void fireProgressChanged_PLATFORM_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "PLATFORM",
            "FALSE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    selector.select();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "PLATFORM",
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    selector.select();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "PLATFORM",
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    selector.select();
  }

  @Test
  public void compressJpeg_PLATFORM_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "PLATFORM",
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    selector.select();
  }

  @Test
  public void scale_PLATFORM_FALSE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackDiffSelector selector =
        new CallStackDiffSelector(
            programName,
            "PLATFORM",
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    selector.select();
  }
}
