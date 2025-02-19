package com.moxos.uab.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotSpaceBlankOrNullValidator implements ConstraintValidator<NotSpaceBlankOrNull, String> {

    @Override
    public void initialize(NotSpaceBlankOrNull a) {
        this.isOptional = a.optional();
    }

    private Boolean isOptional;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        return isNullOrEmpty(value);
    }

    private boolean isNullOrEmpty(String str) {
        return str != null && !str.isBlank();
    }
}