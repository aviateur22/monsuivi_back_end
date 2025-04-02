package com.ctoutweb.monsuivi.infra.model.error;

public record ErrorMessageImpl(String error) implements IErrorMessage  {
    @Override
    public String getError() {
      return error;
    }
  }
