package dev.pirokiko.commerceshop.customer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ValidNLZipCode, String> {

    private ZipCodeCountry country;

    @Override
    public void initialize(ValidNLZipCode constraintAnnotation) {
        this.country = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) return true;

        if(ZipCodeCountry.NL.equals(country)){
            return object.trim().matches("/^\\d{4}\\s*[A-Z]{2}$/");
        }

        throw new IllegalArgumentException("Supplied ZipCodeCountry is not supported");
    }
}
