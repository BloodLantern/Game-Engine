package com.bloodLantern.events;

import java.util.EventObject;

/**
 * An Event is an object created when something happens. Methods annotated with
 * {@link EventListener} are called with the created object when their attached
 * Event is raised.
 * 
 * @author BloodLantern
 */
public abstract class Event extends EventObject implements Cloneable {

	/**
	 * Default superclass constructor.
	 */
	public Event(Object source) {
		super(source);
	}
	
	@Override
	public Object clone() {
		return this;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
