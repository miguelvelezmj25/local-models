package edu.cmu.cs.mvelezce.lc.perf.behavior.scripts.matlab.configs.local.models.local;

import edu.cmu.cs.mvelezce.explorer.idta.partition.Partition;
import edu.cmu.cs.mvelezce.lc.perf.model.model.LocalPerformanceModel;
import org.apache.commons.io.FileUtils;
import scala.collection.JavaConversions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class PlotLocalModelsPerfBehavior {

  public static final String DOT_M = ".m";

  private final String programName;
  private final String measuredTime;
  private final Set<LocalPerformanceModel<Partition>> relevantLocalModels;

  PlotLocalModelsPerfBehavior(
      String programName,
      String measuredTime,
      Set<LocalPerformanceModel<Partition>> relevantLocalModels) {
    this.programName = programName;
    this.measuredTime = measuredTime;
    this.relevantLocalModels = relevantLocalModels;
  }

  void generatePlotScript() throws IOException {
    File rootDir =
        new File(
            "src/main/matlab/edu/cmu/cs/mvelezce/lc/perf/behavior/plots/local/models/local/"
                + this.programName
                + "/"
                + this.measuredTime);
    if (rootDir.exists()) {
      FileUtils.forceDelete(rootDir);
    }

    for (LocalPerformanceModel<Partition> localModel : this.relevantLocalModels) {
      String outputDir = "/m" + localModel.getRegion().toString().replaceAll("-", "_") + DOT_M;
      File outputFile = new File(rootDir, outputDir);

      if (outputFile.exists()) {
        FileUtils.forceDelete(outputFile);
      }
      if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
        throw new RuntimeException("Could not create parent files");
      }

      String result =
          "figure(\"Visible\", false);"
              + "\n"
              + "\n"
              + "train = readtable('../../../../../../../../../../../../../../resources/configs/local/models/local/java/programs/configs/java/programs/"
              + this.programName
              + "/"
              + this.measuredTime
              + "/"
              + localModel.getRegion()
              + ".csv');"
              + "\n"
              + "times = table2array(train(:,"
              + (this.getOptionsNumber(localModel) + 1)
              + ":"
              + (this.getOptionsNumber(localModel) + 1)
              + "));"
              + "\n"
              + "times = sort(times);"
              + "\n"
              + "\n"
              + "count = [];"
              + "\n"
              + "for i=1:length(times)"
              + "\n"
              + "    count = [count; i];"
              + "\n"
              + "end"
              + "\n"
              + "\n"
              + "plot(count,times,'k','LineWidth',3);"
              + "\n"
              + "\n"
              + "title('"
              + localModel.getRegion()
              + "', 'Fontsize',80);"
              + "\n"
              + "xlabel('Configurations');"
              + "\n"
              + "ylabel('Performance (s)');"
              + "\n"
              + "\n"
              + "ylim([floor(times(1)) * 1.0, ceil(times(length(times))) * 1.0]);"
              + "\n"
              + "\n"
              + "set(gca,'FontSize',20);"
              + "\n"
              + "set(gca,'xtick',[])"
              + "\n"
              + "\n"
              + "set(gcf,'Position',[100 100 400 300])"
              + "\n"
              + "\n"
              + "scale=2;"
              + "\n"
              + "paperunits='centimeters';"
              + "\n"
              + "filewidth=7.5;%cm"
              + "\n"
              + "fileheight=5.5;%cm"
              + "\n"
              + "filetype='pdf';"
              + "\n"
              + "res=300;%resolution"
              + "\n"
              + "size=[filewidth fileheight]*scale;"
              + "\n"
              + "set(gcf,'paperunits',paperunits,'paperposition',[0 0 size]);"
              + "\n"
              + "set(gcf, 'PaperSize', size);"
              + "\n"
              + "saveas(gcf,'"
              + localModel.getRegion()
              + "',filetype)"
              + "\n";

      PrintWriter writer = new PrintWriter(outputFile);
      writer.write(result);
      writer.flush();
      writer.close();
    }
  }

  private int getOptionsNumber(LocalPerformanceModel<Partition> localModel) {
    Set<String> options = new HashSet<>();
    for (Partition partition : localModel.getModel().keySet()) {
      scala.collection.immutable.Set<String> features =
          partition.getFeatureExpr().collectDistinctFeatures();
      options.addAll(JavaConversions.setAsJavaSet(features));
    }
    return options.size();
  }
}
