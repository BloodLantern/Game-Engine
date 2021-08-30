package com.bloodLantern.events;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Any method annotaded with EventListener is supposed to listen a certain event
 * type (the one written as a parameter of this same method).
 * 
 * @author BloodLantern
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface EventListener {
}
