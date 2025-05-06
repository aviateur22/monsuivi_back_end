package com.ctoutweb.monsuivi.infra.annotation.customAnnotation.localDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LocalDateValidator implements ConstraintValidator<IsValidLocalDate, String> {
  private String message;
  @Override
  public void initialize(IsValidLocalDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.message = constraintAnnotation.message();
  }

  /**
   * Valide des String au fromat dd/MM/Yyyy
   * @param dateFormat object to validate
   * @param context context in which the constraint is evaluated
   * @return boolean
   */
  @Override
  public boolean isValid(String dateFormat, ConstraintValidatorContext context) {
    // On ne control pas si la date est vide
    if(dateFormat == null)
      return true;

    String regex = "^([0-2][0-9]|3[01])/([0][1-9]|1[0-2])/\\d{4}$";
    if(dateFormat.matches(regex))
      return true;

    context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();

    return false;
  }
}
