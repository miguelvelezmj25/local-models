package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

import java.io.IOException;

public class BerkeleyCallStackSelectorTest {

  @Test
  public void log_TEMPORARY_FALSE() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    selector.select();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_FALSE() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    selector.select();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_FALSE() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    selector.select();
  }
}
