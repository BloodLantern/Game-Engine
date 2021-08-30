package com.bloodLantern.events.collisions;

import com.bloodLantern.events.Event;
import com.bloodLantern.physics.collisions.Collidable;

/**
 * A CollisionEvent is raised when a Collidable object collides with another
 * Collidable object. Keep in mind that if one is set to trigger, this Event
 * will not be raised but
 * {@link com.bloodLantern.events.collisions.triggered.TriggerEvent TriggerEvent}
 * will be instead.
 * 
 * @author BloodLantern
 */
public abstract class CollisionEvent extends Event {

	protected final Collidable object1;
	protected final Collidable object2;

	/**
	 * 
	 */
	public CollisionEvent(Collidable object1, Collidable object2) {
		this.object1 = object1;
		this.object2 = object2;
	}

	/**
	 * @return the object1
	 */
	public final Collidable getObject1() {
		return object1;
	}

	/**
	 * @return the object2
	 */
	public final Collidable getObject2() {
		return object2;
	}

}
