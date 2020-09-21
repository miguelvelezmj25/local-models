package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import org.junit.Test;

import java.io.IOException;

public class IndexFilesCallStackDiffTest {

  @Test
  public void fireProgressChanged_MAX_BUFFERED_DOCS_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
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
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
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
  public void fireProgressChanged_MAX_TOKEN_LENGTH_FALSE() throws IOException, DiffException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
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
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "MAX_TOKEN_LENGTH",
            "FALSE",
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
