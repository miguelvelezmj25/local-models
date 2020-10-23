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
        new File(this.outputDir() + "/tabulator"));
  }

  private void saveViewer(File tabularEntryFile, String viewerFile) throws FileNotFoundException {
    String outputFile =
        this.outputDir()
            + "/"
            + FilenameUtils.removeExtension(tabularEntryFile.getName())
            + ".html";
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
//        + "        <link href=\"tabulator/dist/css/tabulator.min.css\" rel=\"stylesheet\">\n"
        + "        <link href=\"tabulator/dist/css/tabulator_site.min.css\" rel=\"stylesheet\">\n"
        + "    </head>\n"
        + "    <body>\n"
        + "        <div id=\"example-table\"></div>\n"
        + "        <script type=\"text/javascript\" src=\"tabulator/dist/js/tabulator.min.js\"></script>\n"
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
    List<TabulatorEntry> entries =
        mapper.readValue(file, new TypeReference<List<TabulatorEntry>>() {});
    return entries;
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
