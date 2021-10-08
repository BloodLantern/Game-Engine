package com.bloodLantern.physics.collisions;

/**
 * Default implementation of the Triggerable interface. Note that it extends
 * DefaultCollidable.
 * 
 * @author BloodLantern
 */
public class DefaultTriggerable extends DefaultCollidable implements Triggerable {

	private boolean trigger;

	/**
	 * Constructs a new DefaultTriggerable object without any default value.
	 */
	public DefaultTriggerable() {
	}

	/**
	 * Constructs a new DefaultTriggerable object with a boolean value for its
	 * trigger field.
	 * 
	 * @param trigger Whether or not this Collidable object should fire events
	 *                instead of colliding.
	 */
	public DefaultTriggerable(boolean trigger) {
		this.trigger = trigger;
	}

	/**
	 * Getter for the trigger value.
	 * 
	 * @return The trigger to get.
	 */
	@Override
	public boolean isTrigger() {
		return trigger;
	}

	/**
	 * Setter for the trigger value.
	 * 
	 * @param trigger The trigger to set.
	 */
	@Override
	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

}
