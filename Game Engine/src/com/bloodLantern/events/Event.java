package com.bloodLantern.events;

import java.util.EventObject;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.main.GameEngine;

/**
 * An Event is an object created when something happens. Methods annotated with
 * {@link EventListener} are called with the created object when their attached
 * Event is raised.
 *
 * @author BloodLantern
 */
public class Event extends EventObject implements Cloneable {

	/**
	 * Default superclass constructor.
	 */
	public Event(@NotNull Object source) {
		super(source);
		GameEngine.verifyNotNull("Cannot create an Event with a null source!", source);
	}

	@Override
	@NotNull
	public Event clone() {
		try {
			return (Event) super.clone();
		} catch (CloneNotSupportedException e) {
			// This class does implement the Cloneable interface. This shouldn't happen
			throw new RuntimeException("Can't clone Event.");
		}
	}

	@Override
	@NotNull
	public String toString() {
		return "Event[" + source + "]";
	}

}
