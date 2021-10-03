package com.bloodLantern.events.collisions.triggered;

import com.bloodLantern.events.collisions.CollisionEvent;
import com.bloodLantern.physics.collisions.Collidable;

/**
 * 
 * @author BloodLantern
 */
public abstract class TriggerEvent extends CollisionEvent {

	/**
	 * 
	 */
	public TriggerEvent(Object source, Collidable object1, Collidable object2) {
		super(source, object1, object2);
	}

}
