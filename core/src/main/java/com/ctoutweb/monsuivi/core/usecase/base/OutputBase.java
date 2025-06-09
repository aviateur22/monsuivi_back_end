package com.ctoutweb.monsuivi.core.usecase.base;

public abstract class OutputBase<T> {
  private final T outputBoundary;

  public OutputBase(T outputBoundary) {
    this.outputBoundary = outputBoundary;
  }

  public T getOutputBoundary() {
    return outputBoundary;
  }
}
