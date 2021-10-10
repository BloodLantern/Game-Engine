package com.bloodLantern.events;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Any method annotaded with EventListener is supposed to listen a certain event
 * type (the one written as a parameter of this same method). If the class in
 * which the annotated method does implement the {@link Listener} interface,
 * then it will be invoked with the unique Event or any Event subclass argument.
 *
 * @author BloodLantern
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface EventListener {

	/**
	 * A priority defines when, during the fireEvent process, the annotated method
	 * should be invoked. A high priority means that it should be called first while
	 * a low priority means the opposite.
	 *
	 * @return The priority.
	 */
	int value() default EventPriority.NORMAL;

}
