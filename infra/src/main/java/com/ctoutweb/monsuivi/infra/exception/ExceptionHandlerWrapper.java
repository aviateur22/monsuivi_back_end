package com.ctoutweb.monsuivi.infra.exception;

import com.ctoutweb.monsuivi.core.exception.CoreException;
import com.ctoutweb.monsuivi.infra.InfraFactory;
import com.ctoutweb.monsuivi.infra.model.error.IErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerWrapper {
  private static final Logger LOGGER = LogManager.getLogger();
  private final InfraFactory infraFactory;
  public ExceptionHandlerWrapper(InfraFactory infraFactory) {
    this.infraFactory = infraFactory;
  }

  @ExceptionHandler(value = {ValidatorException.class})
  public ResponseEntity<IErrorMessage> internalException(ValidatorException exception) {
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {BadRequestException.class})
  public ResponseEntity<IErrorMessage> badRequestestException(BadRequestException exception) {
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {ServiceException.class})
  public ResponseEntity<IErrorMessage> mapperException(ServiceException exception) {
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {CoreException.class})
  public ResponseEntity<IErrorMessage> coreExcpetion(CoreException exception) {
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<IErrorMessage> globalException(Exception exception) {
    LOGGER.error(()->exception);
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
  /**
   * Capture l'exception d'un champs manquant (Dto à null) avant l'éxecution du DtoValidator
   * @param exception  MethodArgumentNotValidException
   * @return ResponseEntity<IErrorMessage>
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<IErrorMessage> handleValidationErrors(MethodArgumentNotValidException exception) {
    LOGGER.error(String.format("[ExceptionHandlerWrapper] - handleValidationErrors: %s ", exception));
    return new ResponseEntity<>(infraFactory.getErrorMessageImpl("Des données sont manquantes pour compléter la demande"), HttpStatus.BAD_REQUEST);

  }

}
