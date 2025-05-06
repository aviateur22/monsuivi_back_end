package com.ctoutweb.monsuivi.infra.annotation.customAnnotation.productStatus;

import com.ctoutweb.monsuivi.infra.model.product.ProductStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductStatusValidator implements ConstraintValidator<isProductStatusValid, String> {
  private String message;
  @Override
  public void initialize(isProductStatusValid constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(String productStatus, ConstraintValidatorContext context) {
    if(productStatus.equalsIgnoreCase(ProductStatus.FOR_SALE.getProductStatusCode()) || productStatus.equalsIgnoreCase(ProductStatus.SOLD.getProductStatusCode()))
      return true;

    context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();

    return false;
  }
}
