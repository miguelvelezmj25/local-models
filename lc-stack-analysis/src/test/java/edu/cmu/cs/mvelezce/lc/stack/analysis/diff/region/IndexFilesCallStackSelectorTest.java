package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.indexFiles.BaseIndexFilesAdapter;
import org.junit.Test;

import java.io.IOException;

public class IndexFilesCallStackSelectorTest {

  @Test
  public void fireProgressChanged_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.analysis.standard.StandardTokenizerImpl",
            "zzRefill",
            "()Z");
    selector.select();
  }

  @Test
  public void rehash_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.BytesRefHash",
            "rehash",
            "(IZ)V");
    selector.select();
  }

  @Test
  public void readVInt_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataInput",
            "readVInt",
            "()I");
    selector.select();
  }

  @Test
  public void reorder_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "reorder",
            "(II[I[II)V");
    selector.select();
  }

  @Test
  public void DataOutput_writeVInt_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVInt",
            "(I)V");
    selector.select();
  }

  @Test
  public void TermsHashPerField_writeVInt_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "writeVInt",
            "(II)V");
    selector.select();
  }

  @Test
  public void writeVLong_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.DataOutput",
            "writeVLong",
            "(J)V");
    selector.select();
  }

  @Test
  public void add_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.TermsHashPerField",
            "add",
            "()V");
    selector.select();
  }

  @Test
  public void writeTerm_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.PushPostingsWriterBase",
            "writeTerm",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;Lorg/apache/lucene/core/util/FixedBitSet;)Lorg/apache/lucene/core/codecs/BlockTermState;");
    selector.select();
  }

  @Test
  public void quicksort_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.IntroSorter",
            "quicksort",
            "(III)V");
    selector.select();
  }

  @Test
  public void readByte_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.index.ByteSliceReader",
            "readByte",
            "()B");
    selector.select();
  }

  @Test
  public void init_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.packed.PackedInts$MutableImpl",
            "<init>",
            "(II)V");
    selector.select();
  }

  @Test
  public void FSDirectory$FSIndexOutput$1_write_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.store.FSDirectory$FSIndexOutput$1",
            "write",
            "([BII)V");
    selector.select();
  }

  @Test
  public void BlockTreeTermsWriter$TermsWriter_write_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.blocktree.BlockTreeTermsWriter$TermsWriter",
            "write",
            "(Lorg/apache/lucene/core/util/BytesRef;Lorg/apache/lucene/core/index/TermsEnum;)V");
    selector.select();
  }

  @Test
  public void writeBlock_MAX_BUFFERED_DOCS_FALSE() throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.codecs.lucene50.ForUtil",
            "writeBlock",
            "([I[BLorg/apache/lucene/core/store/IndexOutput;)V");
    selector.select();
  }

  @Test
  public void computeCommonPrefixLengthAndBuildHistogram_MAX_BUFFERED_DOCS_FALSE()
      throws IOException {
    String programName = BaseIndexFilesAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "MAX_BUFFERED_DOCS",
            "FALSE",
            "org.apache.lucene.core.util.MSBRadixSorter",
            "computeCommonPrefixLengthAndBuildHistogram",
            "(III[I)I");
    selector.select();
  }
}