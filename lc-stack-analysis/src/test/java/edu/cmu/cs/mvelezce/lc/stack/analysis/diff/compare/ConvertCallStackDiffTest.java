package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ConvertCallStackDiffTest {

  @Test
  public void fireProgressChanged_SCALE_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("eda33826-0677-49c9-a69e-5f0e25fa8c3d.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("20796797-2e09-49ff-884f-0a0537e4fdd9.csv");
    stacks2.add("206a0499-8892-4160-8224-d9542e470d23.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_SCALE_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("ec4baa08-01d1-4990-afa9-4d9d01071c2e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("55e7869f-0ccf-4db2-a889-16a3b3c3b549.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_SCALE_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("8489fca1-9008-41a1-bb72-3588b8d25843.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("45214722-24e8-4655-ad9f-385c444c8333.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("6f889ae0-605b-4c53-b750-adcaf8f5e8d0.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("128cdb27-e5e4-459c-bb27-babc965f4450.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("9ca489e2-8593-4bb5-bb6e-aa62c5f70e96.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("049327a9-a060-48c0-84ed-cdc8218b5710.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_SCALE_FALSE_3() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("8f48ac0c-dd86-4cf1-a70a-04eb9c510107.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("858698df-f5c0-41cf-9c55-019c9cba443b.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void compressJpeg_SCALE_FALSE() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("16f5acd2-01c8-4ba0-8a71-cbfdccdbf4f4.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("1afa9646-9d00-44e5-b67d-7ed3847728d4.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    differ.diff();
  }

  @Test
  public void scale_SCALE_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("0a7864c3-5356-492c-86d2-f710121a0545.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("70103ec5-19f7-4c61-85da-989a15c5a29a.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_SCALE_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("b8f89249-3613-4002-a805-31875bb065ad.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b33b9bee-8424-485d-8530-c7956763effb.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_SCALE_FALSE_3() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("dab14c36-6cf2-4ddb-9e8e-68389804c576.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("898f91b6-c22d-416a-bab8-e3f846320472.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_SCALE_FALSE_4() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fad4a49b-c7c2-495b-843b-f5e063748fb8.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("57e34929-b21f-413c-a379-6506ca83bc2a.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_SCALE_FALSE_5() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "SCALE";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("1a09bcc1-789a-43c1-991d-6bfbb5605dbf.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("200b6654-c1b9-40d9-b3bb-5117240d15db.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            5,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void fireProgressChanged_PLATFORM_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("9919e85f-6ad0-49cc-8a16-5f9a9ad45627.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("20796797-2e09-49ff-884f-0a0537e4fdd9.csv");
    stacks2.add("206a0499-8892-4160-8224-d9542e470d23.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.AdvancedResizeOp",
            "fireProgressChanged",
            "(F)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("0a140556-f88e-4345-958a-223c4179dd66.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("45214722-24e8-4655-ad9f-385c444c8333.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void verticalFromWorkToDst_PLATFORM_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("36d9774f-7ae1-4fe4-8511-ea8b243ade8b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("55e7869f-0ccf-4db2-a889-16a3b3c3b549.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "verticalFromWorkToDst",
            "([[B[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("efa05864-50a7-4048-8b2f-282d8a6566f8.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("049327a9-a060-48c0-84ed-cdc8218b5710.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("3342be39-b470-495b-abc6-a032b192ef9b.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("128cdb27-e5e4-459c-bb27-babc965f4450.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void horizontallyFromSrcToWork_PLATFORM_FALSE_3() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("8b456ba4-1f63-4935-9363-0d318fc493bd.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("858698df-f5c0-41cf-9c55-019c9cba443b.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "com.mortennobel.imagescaling.ResampleOp",
            "horizontallyFromSrcToWork",
            "(Ljava/awt/image/BufferedImage;[[BII)V");
    differ.diff();
  }

  @Test
  public void compressJpeg_PLATFORM_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("7202e419-58ce-4d83-90a1-d00753e33297.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("1afa9646-9d00-44e5-b67d-7ed3847728d4.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "compressJpeg",
            "(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_FALSE_1() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fbef6c9b-599d-47e2-ac3f-d73b7f42b5f8.csv");
    stacks1.add("adea02a0-2534-41aa-bb17-f3980cdb114e.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("898f91b6-c22d-416a-bab8-e3f846320472.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_FALSE_2() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fbef6c9b-599d-47e2-ac3f-d73b7f42b5f8.csv");
    stacks1.add("86f90b59-d3be-410a-b3cc-73162332c1ab.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("70103ec5-19f7-4c61-85da-989a15c5a29a.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            2,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_FALSE_3() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fbef6c9b-599d-47e2-ac3f-d73b7f42b5f8.csv");
    stacks1.add("d2aad047-5d19-44ac-bb48-28446066744c.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b33b9bee-8424-485d-8530-c7956763effb.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            3,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_FALSE_4() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fbef6c9b-599d-47e2-ac3f-d73b7f42b5f8.csv");
    stacks1.add("d8deafad-dc30-4d70-add1-8c3fb021551d.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("57e34929-b21f-413c-a379-6506ca83bc2a.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            4,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }

  @Test
  public void scale_PLATFORM_FALSE_5() throws IOException, DiffException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;

    String option1 = "PLATFORM";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("fbef6c9b-599d-47e2-ac3f-d73b7f42b5f8.csv");
    stacks1.add("0150289d-da2f-43cf-bcfc-d5a2be4ad851.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("200b6654-c1b9-40d9-b3bb-5117240d15db.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            5,
            option1,
            stacks1,
            option2,
            stacks2,
            "at.favre.tools.dconvert.converters.scaling.ImageHandler",
            "scale",
            "(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;");
    differ.diff();
  }
}
