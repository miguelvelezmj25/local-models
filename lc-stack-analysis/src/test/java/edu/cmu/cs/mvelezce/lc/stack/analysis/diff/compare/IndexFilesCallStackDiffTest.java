package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class IndexFilesCallStackDiffTest {

  @Test
  public void zzRefill_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;

    String option1 = "MAX_BUFFERED_DOCS";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("9a48f0f9-23a3-4728-8b5a-b5821261cd19.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("6a0c6412-4860-49ec-9433-8712d97f389d.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "org.apache.lucene.core.analysis.standard.StandardTokenizerImpl",
            "zzRefill",
            "()Z");
    differ.diff();
  }

  @Test
  public void rehash_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.BytesRefHash",
            "rehash",
            "(IZ)V");
    differ.diff();
  }

  @Test
  public void readVInt_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataInput",
            "readVInt",
            "()I");
    differ.diff();
  }

  @Test
  public void reorder_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "reorder",
            "(II[I[II)V");
    differ.diff();
  }

  @Test
  public void DataOutput_writeVInt_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVInt",
            "(I)V");
    differ.diff();
  }

  @Test
  public void TermsHashPerField_writeVInt_MAX_BUFFERED_DOCS_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "writeVInt",
            "(II)V");
    differ.diff();
  }

  @Test
  public void writeVLong_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVLong",
            "(J)V");
    differ.diff();
  }

  @Test
  public void add_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "add",
            "()V");
    differ.diff();
  }

  @Test
  public void writeTerm_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.PushPostingsWriterBase",
            "writeTerm",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;Lorg/apache/lucene/core/util/FixedBitSet;)Lorg/apache/lucene/core/codecs/BlockTermState;");
    differ.diff();
  }

  @Test
  public void quicksort_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.IntroSorter",
            "quicksort",
            "(III)V");
    differ.diff();
  }

  @Test
  public void readByte_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.ByteSliceReader",
            "readByte",
            "()B");
    differ.diff();
  }

  @Test
  public void init_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;

    String option1 = "MAX_BUFFERED_DOCS";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("04261395-95d9-41db-ad75-772a72bb5670.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b0da2aa4-65d1-4a74-98f6-78b0fd18243b.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "org.apache.lucene.core.util.packed.PackedInts$MutableImpl",
            "<init>",
            "(II)V");
    differ.diff();
  }

  @Test
  public void FSDirectory$FSIndexOutput$1_write_MAX_BUFFERED_DOCS_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.FSDirectory$FSIndexOutput$1",
            "write",
            "([BII)V");
    differ.diff();
  }

  @Test
  public void BlockTreeTermsWriter$TermsWriter_write_MAX_BUFFERED_DOCS_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.blocktree.BlockTreeTermsWriter$TermsWriter",
            "write",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;)V");
    differ.diff();
  }

  @Test
  public void writeBlock_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.lucene50.ForUtil",
            "writeBlock",
            "([I[BLorg/apache/lucene/core/store/IndexOutput;)V");
    differ.diff();
  }

  @Test
  public void computeCommonPrefixLengthAndBuildHistogram_MAX_BUFFERED_DOCS_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "computeCommonPrefixLengthAndBuildHistogram",
            "(III[I)I");
    differ.diff();
  }

  @Test
  public void zzRefill_MAX_TOKEN_LENGTH_FALSE_1() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;

    String option1 = "MAX_TOKEN_LENGTH";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("a31dde8b-3589-4153-a6d1-46aca226ccc7.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("6a0c6412-4860-49ec-9433-8712d97f389d.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "org.apache.lucene.core.analysis.standard.StandardTokenizerImpl",
            "zzRefill",
            "()Z");
    differ.diff();
  }

  @Test
  public void rehash_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.util.BytesRefHash",
            "rehash",
            "(IZ)V");
    differ.diff();
  }

  @Test
  public void readVInt_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.store.DataInput",
            "readVInt",
            "()I");
    differ.diff();
  }

  @Test
  public void reorder_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "reorder",
            "(II[I[II)V");
    differ.diff();
  }

  @Test
  public void DataOutput_writeVInt_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVInt",
            "(I)V");
    differ.diff();
  }

  @Test
  public void TermsHashPerField_writeVInt_MAX_TOKEN_LENGTH_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "writeVInt",
            "(II)V");
    differ.diff();
  }

  @Test
  public void writeVLong_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVLong",
            "(J)V");
    differ.diff();
  }

  @Test
  public void add_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "add",
            "()V");
    differ.diff();
  }

  @Test
  public void writeTerm_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.codecs.PushPostingsWriterBase",
            "writeTerm",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;Lorg/apache/lucene/core/util/FixedBitSet;)Lorg/apache/lucene/core/codecs/BlockTermState;");
    differ.diff();
  }

  @Test
  public void quicksort_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.util.IntroSorter",
            "quicksort",
            "(III)V");
    differ.diff();
  }

  @Test
  public void readByte_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.index.ByteSliceReader",
            "readByte",
            "()B");
    differ.diff();
  }

  @Test
  public void init_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;

    String option1 = "MAX_TOKEN_LENGTH";
    Set<String> stacks1 = new HashSet<>();
    stacks1.add("074c97b5-485a-4a0e-be8c-e5d3942714c3.csv");

    String option2 = "FALSE";
    Set<String> stacks2 = new HashSet<>();
    stacks2.add("b0da2aa4-65d1-4a74-98f6-78b0fd18243b.csv");

    CallStackDiff differ =
        new CallStackDiff(
            programName,
            1,
            option1,
            stacks1,
            option2,
            stacks2,
            "org.apache.lucene.core.util.packed.PackedInts$MutableImpl",
            "<init>",
            "(II)V");
    differ.diff();
  }

  @Test
  public void FSDirectory$FSIndexOutput$1_write_MAX_TOKEN_LENGTH_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.store.FSDirectory$FSIndexOutput$1",
            "write",
            "([BII)V");
    differ.diff();
  }

  @Test
  public void BlockTreeTermsWriter$TermsWriter_write_MAX_TOKEN_LENGTH_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.codecs.blocktree.BlockTreeTermsWriter$TermsWriter",
            "write",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;)V");
    differ.diff();
  }

  @Test
  public void writeBlock_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.codecs.lucene50.ForUtil",
            "writeBlock",
            "([I[BLorg/apache/lucene/core/store/IndexOutput;)V");
    differ.diff();
  }

  @Test
  public void computeCommonPrefixLengthAndBuildHistogram_MAX_TOKEN_LENGTH_FALSE()
      throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "computeCommonPrefixLengthAndBuildHistogram",
            "(III[I)I");
    differ.diff();
  }
}
