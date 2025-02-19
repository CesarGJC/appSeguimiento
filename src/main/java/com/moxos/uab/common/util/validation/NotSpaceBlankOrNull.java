package com.moxos.uab.common.util.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NotSpaceBlankOrNullValidator.class)
public @interface NotSpaceBlankOrNull  {

    String message() default "Debe ingresar alguna descripcion";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    boolean optional() default false;
}
