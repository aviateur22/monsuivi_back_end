package com.ctoutweb.monsuivi.infra.annotation;

import com.ctoutweb.monsuivi.infra.exception.ValidatorException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AnnotationValidator {
  private final Validator validator;

  public AnnotationValidator(Validator validator) {
    this.validator = validator;
  }

  /**
   *
   * @param objectToValidate
   * @throws ValidatorException
   */
  public<T> void validate(T objectToValidate) {
    Set<ConstraintViolation<T>> validationErrors = validator.validate(objectToValidate);

    // Récuperation derniere erreur
    String lastError = null;

    if(!validationErrors.isEmpty()) {
      lastError = validationErrors.stream().reduce((first, second)->second).map(ConstraintViolation::getMessage).orElse(null);

      throw new ValidatorException(lastError);
    }
  }
}
