package com.ctoutweb.monsuivi.infra.annotation.customAnnotation.image;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageNotNullValidator implements ConstraintValidator<ImageNotNull, MultipartFile> {
  private String message;
  @Override
  public void initialize(ImageNotNull constraintAnnotation) {
    this.message = constraintAnnotation.message();
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {
    if(!image.isEmpty() || image.getSize() > 0)
      return true;

    context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();

    return false;
  }
}
