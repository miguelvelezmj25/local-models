package edu.cmu.cs.mvelezce.lc.perf.profile.viz.viewer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.cs.mvelezce.analysis.Analysis;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorEntry;
import edu.cmu.cs.mvelezce.lc.perf.profile.viz.parser.TabulatorHotspotParser;
import edu.cmu.cs.mvelezce.utils.config.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

public class TabulatorHotspotViewer implements Analysis<Void> {

  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

  public static final String OUTPUT_DIR = Options.DIRECTORY + "/viewer/hotspots/java/programs";

  private final String programName;

  public TabulatorHotspotViewer(String programName) {
    this.programName = programName;
  }

  @Override
  public Void analyze(String[] args) throws IOException {
    Options.getCommandLine(args);
    File file = new File(this.outputDir());
    Options.checkIfDeleteResult(file);
    return this.analyze();
  }

  @Override
  public Void analyze() throws IOException {
    File snapshotsDir = new File(TabulatorHotspotParser.OUTPUT_DIR + "/" + this.programName);
    Collection<File> tabulatorEntryFiles =
        FileUtils.listFiles(snapshotsDir, new String[] {"json"}, false);
    for (File tabularEntryFile : tabulatorEntryFiles) {
      List<TabulatorEntry> entries = this.parseEntries(tabularEntryFile);
      String viewerFile = this.getViewerFile(entries);
      this.saveViewer(tabularEntryFile, viewerFile);
      this.copyTabulatorDir();
    }

    return null;
  }

  private void copyTabulatorDir() throws IOException {
    FileUtils.copyDirectory(
        new File("src/main/java/edu/cmu/cs/mvelezce/lc/perf/profile/viz/tabulator"),
        new File(this.outputDir() + "/"));
  }

  private void saveViewer(File tabularEntryFile, String viewerFile) throws FileNotFoundException {
    String outputFile =
        this.outputDir() + "/" + FilenameUtils.removeExtension(tabularEntryFile.getName()) + ".htm";
    File file = new File(outputFile);
    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new RuntimeException("Could not create parent file dirs");
    }

    try (PrintWriter out = new PrintWriter(outputFile)) {
      out.println(viewerFile);
    }
  }

  private String getViewerFile(List<TabulatorEntry> entries) {
    return "<!DOCTYPE html>\n"
        + "<html lang=\"en\">\n"
        + "    <head>\n"
        //        + "        <link href=\"tabulator/dist/css/tabulator.min.css\"
        // rel=\"stylesheet\">\n"
        + "        <link href=\"tabulator_site.min.css\" rel=\"stylesheet\">\n"
        + "    </head>\n"
        + "    <body>\n"
        + "        <div id=\"example-table\" class=\"tabulator\" role=\"grid\" tabulator-layout=\"fitData\"><div class=\"tabulator-header\" style=\"padding-right: 0px;\"><div class=\"tabulator-headers\" style=\"margin-left: 0px;\"><div class=\"tabulator-col tabulator-sortable\" role=\"columnheader\" aria-sort=\"none\" tabulator-field=\"hotspot\" title=\"\" style=\"min-width: 40px; width: 875px; height: 33px;\"><div class=\"tabulator-col-content\"><div class=\"tabulator-col-title-holder\"><div class=\"tabulator-col-title\">Hot Spot</div><div class=\"tabulator-col-sorter\"><div class=\"tabulator-arrow\"></div></div></div></div><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-col tabulator-sortable\" role=\"columnheader\" aria-sort=\"none\" tabulator-field=\"selfTimeVisual\" title=\"\" style=\"min-width: 40px; width: 175px; height: 33px;\"><div class=\"tabulator-col-content\"><div class=\"tabulator-col-title-holder\"><div class=\"tabulator-col-title\">Self Time Visual</div><div class=\"tabulator-col-sorter\"><div class=\"tabulator-arrow\"></div></div></div></div><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-col tabulator-sortable\" role=\"columnheader\" aria-sort=\"none\" tabulator-field=\"selfTime\" title=\"\" style=\"min-width: 40px; height: 33px; width: 146px;\"><div class=\"tabulator-col-content\"><div class=\"tabulator-col-title-holder\"><div class=\"tabulator-col-title\">Self Time (secs.)</div><div class=\"tabulator-col-sorter\"><div class=\"tabulator-arrow\"></div></div></div></div><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-frozen-rows-holder\"></div></div><div class=\"tabulator-tableHolder\" tabindex=\"0\" style=\"height: 667px;\"><div class=\"tabulator-table\" style=\"padding-top: 0px; padding-bottom: 0px;\"><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleOp.verticalFromWorkToDst([[B[BII)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"100\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 100%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">88.03<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.converters.scaling.ImageHandler.compressJpeg(Ljava/awt/image/BufferedImage;Lcom/twelvemonkeys/imageio/metadata/CompoundDirectory;FLjava/io/File;)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"39\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 39%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">34.04<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleOp.horizontallyFromSrcToWork(Ljava/awt/image/BufferedImage;[[BII)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"20\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 20%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">17.60<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.converters.scaling.ImageHandler.scale(Lat/favre/tools/dconvert/converters/scaling/ScaleAlgorithm;Ljava/awt/image/BufferedImage;IILat/favre/tools/dconvert/arg/ImageType$ECompression;Ljava/awt/Color;)Ljava/awt/image/BufferedImage;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"8\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 8%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">7.12<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ImageUtils.setBGRPixels([BLjava/awt/image/BufferedImage;IIII)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"2\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 2%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">1.48<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ImageUtils.getPixelsBGR(Ljava/awt/image/BufferedImage;II[B[I)[B<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"1\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 1%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">1.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.util.ImageUtil.read(Ljavax/imageio/stream/ImageInputStream;Lat/favre/tools/dconvert/arg/ImageType;)Lat/favre/tools/dconvert/util/LoadedImage;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.44<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleOp.doFilter(Ljava/awt/image/BufferedImage;Ljava/awt/image/BufferedImage;II)Ljava/awt/image/BufferedImage;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.27<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.DConvert.getRegisteredImageReadersAndWriters()Ljava/lang/String;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.26<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleOp.createSubSampling(Lcom/mortennobel/imagescaling/ResampleFilter;II)Lcom/mortennobel/imagescaling/ResampleOp$SubSamplingData;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.19<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.converters.APlatformConverter.convert(Lat/favre/tools/dconvert/arg/Arguments;Lat/favre/tools/dconvert/util/LoadedImage;ZLjava/lang/StringBuilder;Ljava/util/List;Ljava/awt/Dimension;Ljava/io/File;)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.02<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.WorkerHandler.&lt;init&gt;(Ljava/util/List;Lat/favre/tools/dconvert/arg/Arguments;Lat/favre/tools/dconvert/WorkerHandler$Callback;)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.util.DensityBucketUtil.getDensityBucketsWithFactorScale(Ljava/util/List;Ljava/awt/Dimension;Lat/favre/tools/dconvert/arg/Arguments;F)Ljava/util/Map;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.arg.EScalingAlgorithm.&lt;clinit&gt;()V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleFilters.&lt;clinit&gt;()V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.arg.EPlatform.&lt;clinit&gt;()V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.Convert.upScalingAlgo(Z)Ljava/lang/String;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.DConvert.informFinished(ILjava/util/List;Z)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.DConvert$1.onFinished(ILjava/util/List;Ljava/lang/StringBuilder;Ljava/util/List;Z)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.twelvemonkeys.imageio.metadata.jpeg.JPEGSegmentUtil.&lt;clinit&gt;()V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"text-align: right; height: 29px; width: 146px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.DConvert.execute(Lat/favre/tools/dconvert/arg/Arguments;ZLat/favre/tools/dconvert/DConvert$HandlerCallback;)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"width: 146px; text-align: right; height: 29px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-even tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>at.favre.tools.dconvert.util.ImageUtil.loadImage(Ljava/io/File;)Lat/favre/tools/dconvert/util/LoadedImage;<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"width: 146px; text-align: right; height: 29px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div><div class=\"tabulator-row tabulator-selectable tabulator-row-odd tabulator-tree-level-0\" role=\"row\" style=\"padding-left: 0px;\"><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"hotspot\" title=\"\" style=\"width: 875px; height: 29px;\"><div class=\"tabulator-data-tree-control\" tabindex=\"0\"><div class=\"tabulator-data-tree-control-expand\"></div></div>com.mortennobel.imagescaling.ResampleOp.&lt;init&gt;(II)V<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTimeVisual\" aria-label=\"0\" title=\"\" style=\"width: 175px; min-width: 30px; position: relative; height: 29px;\"><div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div><div data-max=\"88.03386\" data-min=\"0\" style=\"display: inline-block; position: relative; width: 0%; background-color: rgb(45, 194, 20); height: 100%;\"></div></div><div class=\"tabulator-cell\" role=\"gridcell\" tabulator-field=\"selfTime\" title=\"\" style=\"width: 146px; text-align: right; height: 29px;\">0.01<div class=\"tabulator-col-resize-handle\"></div><div class=\"tabulator-col-resize-handle prev\"></div></div></div></div></div></div>\n"
        + "        <script type=\"text/javascript\" src=\"tabulator.min.js\"></script>\n"
        + "        <script type=\"text/javascript\">\n"
        + "\n"
        + "        var tableDataNested = [\n"
        + this.getHotspotData(12, entries)
        + "        ];\n"
        + "\n"
        + "        var table = new Tabulator(\"#example-table\", {\n"
        + "            data:tableDataNested,\n"
        + "            dataTree:true,\n"
        + "            dataTreeStartExpanded:false,\n"
        + "            columns:[\n"
        + "                {title:\"Hot Spot\", field:\"hotspot\", width:\"50px\"},\n"
        + "                {title:\"Self Time Visual\", field:\"selfTimeVisual\", sorter:\"number\", formatter:\"progress\", width:\"10px\", formatterParams:{max:"
        + this.getMaxTime(entries)
        + "}}, \n"
        + "                {title:\"Self Time (secs.)\", field:\"selfTime\", hozAlign:\"right\", sorter:\"numeric\"},\n"
        + "			],\n"
        + "        });\n"
        + "        </script>\n"
        + "    </body>\n"
        + "</html>\n";
  }

  private double getMaxTime(List<TabulatorEntry> entries) {
    double max = 0;
    for (TabulatorEntry entry : entries) {
      max = Math.max(max, entry.getTime());
    }

    return max;
  }

  private String getHotspotData(int indent, List<TabulatorEntry> entries) {
    StringBuilder builder = new StringBuilder();
    for (TabulatorEntry entry : entries) {
      for (int i = 0; i < indent; i++) {
        builder.append(" ");
      }
      builder.append("{");
      builder.append("hotspot:\"");
      builder.append(entry.getMethod());
      builder.append("\",");
      builder.append("selfTimeVisual:\"");
      builder.append(entry.getTime());
      builder.append("\",");
      builder.append("selfTime:\"");
      builder.append(DECIMAL_FORMAT.format(entry.getTime()));

      if (entry.getTabulatorEntries().isEmpty()) {
        builder.append("\"");
        builder.append("\n");
        for (int i = 0; i < indent; i++) {
          builder.append(" ");
        }
      } else {
        builder.append("\", _children:[\n");
        builder.append(this.getStackData(indent + 4, entry.getTabulatorEntries()));
        builder.append("\n");
        for (int i = 0; i < indent; i++) {
          builder.append(" ");
        }
        builder.append("]");
      }
      builder.append("},\n");
    }

    return builder.toString();
  }

  private String getStackData(int indent, List<TabulatorEntry> entries) {
    StringBuilder builder = new StringBuilder();
    for (TabulatorEntry entry : entries) {
      for (int i = 0; i < indent; i++) {
        builder.append(" ");
      }
      builder.append("{");
      builder.append("hotspot:\"");
      builder.append(DECIMAL_FORMAT.format(entry.getTime()));
      builder.append(" secs. - ");
      builder.append(entry.getMethod());

      if (entry.getTabulatorEntries().isEmpty()) {
        builder.append("\"");
        builder.append("\n");
        for (int i = 0; i < indent; i++) {
          builder.append(" ");
        }
      } else {
        builder.append("\", _children:[\n");
        builder.append(this.getStackData(indent + 4, entry.getTabulatorEntries()));
        builder.append("\n");
        for (int i = 0; i < indent; i++) {
          builder.append(" ");
        }
        builder.append("]");
      }
      builder.append("},\n");
    }

    return builder.toString();
  }

  private List<TabulatorEntry> parseEntries(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(file, new TypeReference<List<TabulatorEntry>>() {});
  }

  @Override
  public String outputDir() {
    return OUTPUT_DIR + "/" + this.programName;
  }

  @Override
  public void writeToFile(Void unused) {
    throw new UnsupportedOperationException("Method should not be called");
  }

  @Override
  public Void readFromFile(File file) {
    throw new UnsupportedOperationException("Method should not be called");
  }
}
