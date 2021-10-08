package com.bloodLantern.events;

/**
 * A class implementing the Listener interface will listen to the raised {@link Event Events}. It must also be added to the 
 * 
 * @author BloodLantern
 */
@FunctionalInterface
public interface Listener extends java.util.EventListener {
	/**
	 * Action to execute when an Event is raised.
	 * 
	 * @param event the raised Event.
	 */
	@EventListener
	public void eventOccured(Event event);
}
