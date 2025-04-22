package com.ctoutweb.monsuivi.infra.annotation.customAnnotation.image;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageNotNullValidator.class)
public @interface ImageNotNull {
String message()  default  "L'image est obligatoire";
  Class<?>[] groups() default {};
  Class <? extends Payload>[] payload() default {};
}
