package edu.cmu.cs.mvelezce.lc.vs.extension.local.models;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import edu.cmu.cs.mvelezce.java.execute.BaseExecutor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LocalModelsGeneratorTest {

  @Test
  void density() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String measuredTime = BaseExecutor.USER;
    LocalModelsGenerator generator = new LocalModelsGenerator(programName, measuredTime);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    generator.analyze(args);
  }
}
