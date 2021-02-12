package dev.pirokiko.commerceshop.customer.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER, METHOD, ANNOTATION_TYPE, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZipCodeValidator.class)
@Documented
public @interface ValidNLZipCode {
    String message() default "Invalid zipcode supplied";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    ZipCodeCountry value() default ZipCodeCountry.NL;
}
