package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.compare;

import com.github.difflib.algorithm.DiffException;
import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import org.junit.Test;

import java.io.IOException;

public class RunBenchCCallStackDiffTest {

  @Test
  public void compact_DEFRAG_ALWAYS_FALSE() throws IOException, DiffException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName, "DEFRAG_ALWAYS", "FALSE", "org.h2.pagestore.PageStore", "compact", "(I)V");
    differ.diff();
  }

  @Test
  public void writeFully_DEFRAG_ALWAYS_FALSE() throws IOException, DiffException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    CallStackDiff differ =
        new CallStackDiff(
            programName,
            "DEFRAG_ALWAYS",
            "FALSE",
            "org.h2.store.fs.FileUtils",
            "writeFully",
            "(Ljava/nio/channels/FileChannel;Ljava/nio/ByteBuffer;)V");
    differ.diff();
  }
}
