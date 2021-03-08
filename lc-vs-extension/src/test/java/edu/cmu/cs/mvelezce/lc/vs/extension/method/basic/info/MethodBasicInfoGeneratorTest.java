package edu.cmu.cs.mvelezce.lc.vs.extension.method.basic.info;

import edu.cmu.cs.mvelezce.adapters.convert.BaseConvertAdapter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class MethodBasicInfoGeneratorTest {

  @Test
  void density() throws IOException {
    String programName = BaseConvertAdapter.PROGRAM_NAME;
    String defaultConfig = "¬KEEP_SCALE";
    String reportConfig = "REPORT";
    MethodBasicInfoGenerator generator =
        new MethodBasicInfoGenerator(programName, defaultConfig, reportConfig);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    generator.analyze(args);
  }

  @Test
  void main() throws IOException {
    String programName = "Main";
    String defaultConfig = "false,5,1";
    String reportConfig = "true,10,2";
    MethodBasicInfoGenerator generator =
        new MethodBasicInfoGenerator(programName, defaultConfig, reportConfig);
    String[] args = new String[2];
    args[0] = "-delres";
    args[1] = "-saveres";
    generator.analyze(args);
  }
}
