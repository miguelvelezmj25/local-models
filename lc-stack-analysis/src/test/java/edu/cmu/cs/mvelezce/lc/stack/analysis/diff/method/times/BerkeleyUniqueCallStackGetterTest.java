package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.method.times;

import edu.cmu.cs.mvelezce.adapters.measureDiskOrderedScan.BaseMeasureDiskOrderedScanAdapter;
import org.junit.Test;

public class BerkeleyUniqueCallStackGetterTest {

  @Test
  public void log_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void log_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void addPostMarshallingInfo_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void addPostMarshallingInfo_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void readFromFileInternal_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void readFromFileInternal_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void writeToFile_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void writeToFile_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void force_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void force_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void findEntry_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void findEntry_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void serialize_TEMPORARY() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "TEMPORARY",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void serialize_FALSE() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "FALSE",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void log_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.log.LogManager",
            "log",
            "(Lcom/sleepycat/je/log/LogParams;)Lcom/sleepycat/je/log/LogItem;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void addPostMarshallingInfo_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.log.LogEntryHeader",
            "addPostMarshallingInfo",
            "(Ljava/nio/ByteBuffer;JLcom/sleepycat/je/utilint/VLSN;)Ljava/nio/ByteBuffer;");
    getter.getUniqueCallStacks();
  }

  @Test
  public void readFromFileInternal_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.log.FileManager",
            "readFromFileInternal",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJ)V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void writeToFile_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.log.FileManager",
            "writeToFile",
            "(Ljava/io/RandomAccessFile;Ljava/nio/ByteBuffer;JJZ)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void force_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.log.FileManager$LogEndFileDescriptor",
            "force",
            "()V");
    getter.getUniqueCallStacks();
  }

  @Test
  public void findEntry_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.tree.IN",
            "findEntry",
            "([BZZLjava/util/Comparator;)I");
    getter.getUniqueCallStacks();
  }

  @Test
  public void serialize_SEQUENTIAL() {
    String programName = BaseMeasureDiskOrderedScanAdapter.PROGRAM_NAME;
    UniqueCallStackGetter getter =
        new UniqueCallStackGetter(
            programName,
            "SEQUENTIAL",
            "com.sleepycat.je.tree.IN",
            "serialize",
            "(Ljava/nio/ByteBuffer;ZZ)V");
    getter.getUniqueCallStacks();
  }
}
