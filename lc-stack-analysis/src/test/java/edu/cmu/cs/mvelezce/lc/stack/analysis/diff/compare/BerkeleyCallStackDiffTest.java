package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

import java.io.IOException;

public class BerkeleyCallStackDiffTest {

  @Test
  public void fireProgressChanged_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }
}
