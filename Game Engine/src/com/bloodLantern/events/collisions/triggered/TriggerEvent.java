package com.bloodLantern.events.collisions.triggered;

import com.bloodLantern.events.collisions.CollisionEvent;
import com.bloodLantern.physics.collisions.DefaultTriggerable;
import com.bloodLantern.physics.collisions.Triggerable;

/**
 * A TriggerEvent is fired when a
 * {@link com.bloodLantern.physics.collisions.DefaultCollidable Collidable} object
 * implementing the {@link com.bloodLantern.physics.collisions.Triggerable
 * Triggerable} interface collides with another Collidable object even if the
 * second one des not implement this interface.
 * 
 * @author BloodLantern
 */
public class TriggerEvent extends CollisionEvent {

	/**
	 * Constructs a new TriggerEvent. The parameter object1 is supposed to have
	 * collided with object2 but because at least one of them does implement
	 * {@link com.bloodLantern.physics.collisions.Triggerable the Triggerable
	 * interface} they will not collide.
	 * 
	 * @param object1 The object that collided with the other.
	 * @param object2 The object collided by the other.
	 */
	public TriggerEvent(Triggerable object1, Triggerable object2) {
		super(object1, object2);
		if (!(object1 instanceof Triggerable) && !(object2 instanceof Triggerable)) {
			throw new IllegalArgumentException(
					"Constructing a TriggerEvent requires at least one parameter to be an instance of the Triggerable interface.");
		}
	}
	
	@Override
	public TriggerEvent clone() {
		return new TriggerEvent(new DefaultTriggerable(), new DefaultTriggerable());
	}

}
