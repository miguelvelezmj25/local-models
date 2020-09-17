package edu.cmu.cs.mvelezce.lc.adapters.earlyReturn;

import edu.cmu.cs.mvelezce.adapters.BaseAdapter;

import java.util.Arrays;
import java.util.List;

public class BaseEarlyReturnAdapter extends BaseAdapter {
  public static final String PROGRAM_NAME = "EarlyReturn";
  public static final String ROOT_PACKAGE = "edu.cmu.cs.mvelezce.perf";
  public static final String MAIN_CLASS = ROOT_PACKAGE + PROGRAM_NAME;

  private static final String[] OPTIONS = new String[] {"A"};

  public BaseEarlyReturnAdapter() {
    super(PROGRAM_NAME, MAIN_CLASS, getListOfOptions());
  }

  public static List<String> getListOfOptions() {
    return Arrays.asList(OPTIONS);
  }
}
