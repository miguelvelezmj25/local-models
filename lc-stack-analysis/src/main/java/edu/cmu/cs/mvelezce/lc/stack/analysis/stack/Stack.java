package edu.cmu.cs.mvelezce.lc.stack.analysis.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack {

  private final Deque<Integer> stack = new ArrayDeque<Integer>();

  public Deque<Integer> getStack() {
    return stack;
  }
}
