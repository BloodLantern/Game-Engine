package com.bloodLantern.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to declare a value as being nullable.
 *
 * @author BloodLantern
 */
@Retention(CLASS)
@Target({ FIELD, METHOD, PARAMETER })
public @interface Nullable {
}
