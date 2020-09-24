package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

public class ConvertUniqueCallStackGetterTest {

  @Test
  public void fireProgressChanged_SCALE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SCALE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void fireProgressChanged_FALSE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void verticalFromWorkToDst_SCALE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void verticalFromWorkToDst_FALSE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SCALE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void horizontallyFromSrcToWork_FALSE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void compressJpeg_SCALE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void compressJpeg_FALSE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void scale_SCALE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SCALE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void scale_FALSE() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void fireProgressChanged_PLATFORM() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "PLATFORM",
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "PLATFORM",
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "PLATFORM",
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void compressJpeg_PLATFORM() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "PLATFORM",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void scale_PLATFORM() {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "PLATFORM",
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    getter.getUniqueCallStacks();
  }
}
