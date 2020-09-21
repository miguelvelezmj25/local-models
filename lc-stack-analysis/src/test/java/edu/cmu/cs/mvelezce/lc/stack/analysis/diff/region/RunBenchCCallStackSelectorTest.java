package edu.cmu.cs.mvelezce.lc.stack.analysis.diff.region;

import edu.cmu.cs.mvelezce.adapters.runBenchC.BaseRunBenchCAdapter;
import org.junit.Test;

import java.io.IOException;

public class RunBenchCCallStackSelectorTest {

  @Test
  public void compact_DEFRAG_ALWAYS_FALSE() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName, "DEFRAG_ALWAYS", "FALSE", "org.h2.pagestore.PageStore", "compact", "(I)V");
    selector.select();
  }

  @Test
  public void writeFully_DEFRAG_ALWAYS_FALSE() throws IOException {
    String programName = BaseRunBenchCAdapter.PROGRAM_NAME;
    CallStackSelector selector =
        new CallStackSelector(
            programName,
            "DEFRAG_ALWAYS",
            "FALSE",
            "org.h2.store.fs.FileUtils",
            "writeFully",
            "(Ljava/nio/channels/FileChannel;Ljava/nio/ByteBuffer;)V");
    selector.select();
  }
}
