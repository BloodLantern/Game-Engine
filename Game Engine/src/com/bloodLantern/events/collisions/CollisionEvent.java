package com.bloodLantern.events.collisions;

import com.bloodLantern.events.Event;
import com.bloodLantern.physics.collisions.Collidable;
import com.bloodLantern.physics.collisions.DefaultCollidable;

/**
 * A CollisionEvent is raised when a Collidable object collides with another
 * Collidable object. Keep in mind that if one is set to trigger, this Event
 * will not be raised but
 * {@link com.bloodLantern.events.collisions.triggered.TriggerEvent
 * TriggerEvent} instead.
 * 
 * @author BloodLantern
 */
public class CollisionEvent extends Event {

	protected final Collidable object1;
	protected final Collidable object2;

	/**
	 * Constructs a new CollisionEvent. The parameter object1 is supposed to have
	 * collided with object2.
	 * 
	 * @param object1 The object that collided with the other.
	 * @param object2 The object collided by the other.
	 */
	public CollisionEvent(Collidable object1, Collidable object2) {
		super(object1);
		this.object1 = object1;
		this.object2 = object2;
	}

	/**
	 * Getter for tht object that collided with the other.
	 * 
	 * @return The object that collided with the other.
	 */
	public final Collidable getObject1() {
		return object1;
	}

	/**
	 * Getter for the object collided by the other.
	 * 
	 * @return The object collided by the other.
	 */
	public final Collidable getObject2() {
		return object2;
	}
	
	/**
	 * Basically creates a new CollisionEvent with 2 blank Collidable objects.
	 */
	@Override
	public CollisionEvent clone() {
		return new CollisionEvent(new DefaultCollidable(), new DefaultCollidable());
	}

}
