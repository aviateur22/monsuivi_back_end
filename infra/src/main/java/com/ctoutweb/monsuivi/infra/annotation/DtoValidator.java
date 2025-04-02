package com.ctoutweb.monsuivi.infra.annotation;

import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class DtoValidator {
  private final Validator validator;

  public DtoValidator(Validator validator) {
    this.validator = validator;
  }

  public<T> void validateDto(T dtoToValidate) {
    AnnotationValidator annotationToValidate = new AnnotationValidator(validator);
    annotationToValidate.validate(dtoToValidate);
  }
}
