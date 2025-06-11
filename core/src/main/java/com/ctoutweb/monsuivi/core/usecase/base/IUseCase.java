package com.ctoutweb.monsuivi.core.usecase.base;

public interface IUseCase <T extends IUseCase.Input, U extends IUseCase.Output>{
  public U execute(T input);
  public interface Input {}
  public interface Output {}
}
