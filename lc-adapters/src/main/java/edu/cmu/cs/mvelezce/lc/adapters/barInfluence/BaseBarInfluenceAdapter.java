package edu.cmu.cs.mvelezce.lc.adapters.barInfluence;

import edu.cmu.cs.mvelezce.adapters.BaseAdapter;

import java.util.Arrays;
import java.util.List;

public class BaseBarInfluenceAdapter extends BaseAdapter {
  public static final String PROGRAM_NAME = "BarInfluence";
  public static final String ROOT_PACKAGE = "edu.cmu.cs.mvelezce.perf";
  public static final String MAIN_CLASS = ROOT_PACKAGE + "." + PROGRAM_NAME;
  public static final String PHOSPHOR_ROOT_DIR =
      "../performance-mapper-evaluation/phosphor/phosphor-examples";
  public static final String PHOSPHOR_CLASS_PATH =
      "../performance-mapper-evaluation/phosphor/phosphor-examples/target/classes";

  private static final String[] OPTIONS = new String[] {"A"};

  public BaseBarInfluenceAdapter() {
    super(PROGRAM_NAME, MAIN_CLASS, getListOfOptions());
  }

  public static List<String> getListOfOptions() {
    return Arrays.asList(OPTIONS);
  }
}
