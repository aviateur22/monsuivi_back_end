package com.ctoutweb.monsuivi.infra.annotation.customAnnotation.productStatus;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductStatusValidator.class)
public @interface isProductStatusValid {
  String message()  default  "Statut invalide";
  Class<?>[] groups() default {};
  Class <? extends Payload>[] payload() default {};
}
