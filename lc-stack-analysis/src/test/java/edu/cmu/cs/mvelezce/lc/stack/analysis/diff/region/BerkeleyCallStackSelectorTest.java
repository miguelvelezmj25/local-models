package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

import java.io.IOException;

public class BerkeleyCallStackSelectorTest {

  @Test
  public void log_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    selector.select();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    selector.select();
  }

  @Test
  public void readFromFileInternal_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    selector.select();
  }

  @Test
  public void writeToFile_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    selector.select();
  }

  @Test
  public void force_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    selector.select();
  }

  @Test
  public void findEntry_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    selector.select();
  }

  @Test
  public void serialize_TEMPORARY_notTEMPORARY() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "¬TEMPORARY",
            "TEMPORARY",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    selector.select();
  }

  @Test
  public void log_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    selector.select();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    selector.select();
  }

  @Test
  public void readFromFileInternal_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    selector.select();
  }

  @Test
  public void writeToFile_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    selector.select();
  }

  @Test
  public void force_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    selector.select();
  }

  @Test
  public void findEntry_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    selector.select();
  }

  @Test
  public void serialize_SEQUENTIAL_notSEQUENTIAL() throws IOException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "SEQUENTIAL",
            "¬SEQUENTIAL",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    selector.select();
  }
}
