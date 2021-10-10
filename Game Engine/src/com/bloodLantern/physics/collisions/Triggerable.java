package com.bloodLantern.physics.collisions;

/**
 * A class implementing this interface is supposed to be extending the
 * {@link DefaultCollidable} class. It means that it will not collide with other
 * objects but fire detailed events about it instead.
 *
 * @author BloodLantern
 * @see com.bloodLantern.events The Event package
 */
public interface Triggerable extends Collidable {
	/**
	 * Whether or not this Collidable object is meant to fire events instead of
	 * colliding with other Collidable objects.
	 *
	 * @return True if this object does fire events instead of colliding. False
	 *         otherwise.
	 */
	public boolean isTrigger();

	/**
	 * Changes the trigger value. If true, this object will fire events instead of
	 * colliding.
	 *
	 * @param trigger True if this object does fire events instead of colliding.
	 *                False otherwise.
	 */
	public void setTrigger(boolean trigger);
}
