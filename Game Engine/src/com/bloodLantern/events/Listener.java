package com.bloodLantern.events;

/**
 * A class implementing the Listener interface will listen to the raised
 * {@link Event Events}. It must also be added to the listeners list by using
 * {@code com.bloodLantern.events.EventManager.addListener(this, Class<? extends Event>)}.
 *
 * @author BloodLantern
 */
public interface Listener extends java.util.EventListener {
}
