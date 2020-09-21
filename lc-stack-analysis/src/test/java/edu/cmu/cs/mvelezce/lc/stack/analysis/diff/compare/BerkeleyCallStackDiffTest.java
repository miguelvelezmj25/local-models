package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

import java.io.IOException;

public class BerkeleyCallStackDiffTest {

  @Test
  public void log_TEMPORARY_FALSE() throws IOException, DiffException {
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

  @Test
  public void writeToFile_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void force_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void findEntry_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void serialize_TEMPORARY_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "TEMPORARY",
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }

  @Test
  public void log_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    differ.diff();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    differ.diff();
  }

  @Test
  public void readFromFileInternal_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    differ.diff();
  }

  @Test
  public void writeToFile_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    differ.diff();
  }

  @Test
  public void force_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    differ.diff();
  }

  @Test
  public void findEntry_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    differ.diff();
  }

  @Test
  public void serialize_SEQUENTIAL_FALSE() throws IOException, DiffException {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "SEQUENTIAL",
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    differ.diff();
  }
}
