package com.ctoutweb.monsuivi.infra.exception;

/**
 * Exception dans les mappers
 */
public class ServiceException extends RuntimeException {
  public ServiceException(String message) {
    super(message);
  }
}
