package com.ctoutweb.monsuivi.infra.exception;

public class ValidatorException extends RuntimeException {
  public ValidatorException(String message) {
    super(message);
  }
}
