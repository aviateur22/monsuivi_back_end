package com.ctoutweb.monsuivi.core.model.usecase;

public abstract class InputBase<T> {
  protected T inputPort;

  public InputBase(T inputPort){
    this.inputPort = inputPort;
  }

  /**
   * Renvoie inputPort servant dans le useCase
   * @return T - Implementation de l'absctraction
   */
  public T getUsecaseInput() {
    return this.inputPort;
  };
}
