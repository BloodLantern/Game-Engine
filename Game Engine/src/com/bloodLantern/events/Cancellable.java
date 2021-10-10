package com.bloodLantern.events;

/**
 * Interface <strong>only for Event classes</strong> used to set an Event
 * cancellable. By default, the boolean value {@code cancelled} should be false.
 *
 * @author BloodLantern
 */
public interface Cancellable {

	/**
	 * Getter for the boolean value attached to this Cancellabe Event's
	 * {@code cancelled} field.
	 *
	 * @return whether the Cancellable event is cancelled.
	 */
	public boolean isCancelled();

	/**
	 * Setter for the boolean value attached to this Cancellable Event's
	 * {@code cancelled} field.
	 *
	 * @param cancelled whether the Cancellable Event should be cancelled.
	 */
	public void setCancelled(boolean cancelled);

}
