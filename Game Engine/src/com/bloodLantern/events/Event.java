package com.bloodLantern.events;

import java.util.EventObject;

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
	public Event(Object source) {
		super(source);
	}

	@Override
	public Event clone() {
		try {
			return (Event) super.clone();
		} catch (CloneNotSupportedException e) {
			// This class does implement the Cloneable interface. This shouldn't happen
			throw new RuntimeException("Can't clone Event.");
		}
	}

	@Override
	public String toString() {
		return "Event[" + source + "]";
	}

}
