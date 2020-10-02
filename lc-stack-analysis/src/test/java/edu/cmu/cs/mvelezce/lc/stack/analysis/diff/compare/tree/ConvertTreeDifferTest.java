package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare.tree;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.explorer.idta.partition.Partitioning;
import edu.cmu.cs.mvelezce.instrument.idta.IDTATimerInstrumenter;
import edu.cmu.cs.mvelezce.instrument.region.instrumenter.BaseRegionInstrumenter;
import edu.cmu.cs.mvelezce.region.java.JavaRegion;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class ConvertTreeDifferTest {

  @Test
  public void fireProgressChanged_SCALE_notSCALE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬SCALE";
    String option2 = "SCALE";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_SCALE_notSCALE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬SCALE";
    String option2 = "SCALE";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_notSCALE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬SCALE";
    String option2 = "SCALE";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void compressJpeg_SCALE_notSCALE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬SCALE";
    String option2 = "SCALE";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    differ.diff();
  }

  @Test
  public void scale_SCALE_notSCALE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬SCALE";
    String option2 = "SCALE";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void fireProgressChanged_PLATFORM_notPLATFORM() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬PLATFORM";
    String option2 = "PLATFORM";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM_notPLATFORM() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬PLATFORM";
    String option2 = "PLATFORM";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_notPLATFORM() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬PLATFORM";
    String option2 = "PLATFORM";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void compressJpeg_PLATFORM_notPLATFORM() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬PLATFORM";
    String option2 = "PLATFORM";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_notPLATFORM() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String option1 = "¬PLATFORM";
    String option2 = "PLATFORM";
    BaseRegionInstrumenter<Partitioning> instrumenter = new IDTATimerInstrumenter(programName);
    Set<JavaRegion> regions = instrumenter.getProcessedRegionsToData().keySet();

    TreeDiffer differ =
        new TreeDiffer(
            programName,
            option1,
            option2,
            regions,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }
}
