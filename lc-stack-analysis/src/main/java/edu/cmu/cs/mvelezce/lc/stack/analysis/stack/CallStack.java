package edu.cmu.cs.mvelezce.lc.stack.analysis.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class CallStack<T> {

  private final Deque<T> stack = new ArrayDeque<>();

  public Deque<T> getStack() {
    return stack;
  }
}
