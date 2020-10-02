package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;

public class ConvertCallStackSelectorTest {

  @Test
  public void fireProgressChanged_SCALE_notSCALE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "¬SCALE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    selector.select();
  }

  @Test
  public void verticalFromWorkToDst_SCALE_notSCALE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "¬SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    selector.select();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_notSCALE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "¬SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    selector.select();
  }

  @Test
  public void compressJpeg_SCALE_notSCALE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "¬SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    selector.select();
  }

  @Test
  public void scale_SCALE_notSCALE() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SCALE",
            "¬SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    selector.select();
  }

  @Test
  public void fireProgressChanged_PLATFORM_notPLATFORM() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "PLATFORM",
            "¬PLATFORM",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    selector.select();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM_notPLATFORM() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "PLATFORM",
            "¬PLATFORM",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    selector.select();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_notPLATFORM() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "PLATFORM",
            "¬PLATFORM",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    selector.select();
  }

  @Test
  public void compressJpeg_PLATFORM_notPLATFORM() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "PLATFORM",
            "¬PLATFORM",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    selector.select();
  }

  @Test
  public void scale_PLATFORM_notPLATFORM() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "PLATFORM",
            "¬PLATFORM",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    selector.select();
  }
}
