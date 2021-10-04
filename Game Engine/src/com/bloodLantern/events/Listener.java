package com.bloodLantern.events;

/**
 * A class implementing the Listener interface will listen to the raised {@link Event Events}.
 * 
 * @author BloodLantern
 */
public interface Listener extends java.util.EventListener {
	public void eventOccured(Event event);
}
