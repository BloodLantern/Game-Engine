package com.bloodLantern.events;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.main.GameEngine;
import com.bloodLantern.physics.collisions.Collidable;
import com.bloodLantern.physics.collisions.DefaultCollidable;

/**
 * A CollisionEvent is raised when a Collidable object collides with another
 * Collidable object. Keep in mind that if one is set to trigger, this Event
 * will not be raised but {@link com.bloodLantern.events.TriggerEvent
 * TriggerEvent} instead.
 *
 * @author BloodLantern
 */
public class CollisionEvent extends Event implements Cancellable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6862840906570015108L;

	/**
	 * The object that collided with the other.
	 */
	@NotNull
	private final Collidable object1;

	/**
	 * The object collided by the other.
	 */
	@NotNull
	private final Collidable object2;

	/**
	 * Whether or not the Event has been cancelled. Implemented or sort of from
	 * {@link Cancellable}.
	 */
	private boolean cancelled = false;

	/**
	 * Constructs a new CollisionEvent. The parameter object1 is supposed to have
	 * collided with object2.
	 *
	 * @param object1 The object that collided with the other.
	 * @param object2 The object collided by the other.
	 */
	public CollisionEvent(@NotNull Collidable object1, @NotNull Collidable object2) {
		super(object1);
		GameEngine.verifyNotNull("Cannot create a CollisionEvent with a null Collidable object!", object2);
		this.object1 = object1;
		this.object2 = object2;
	}

	/**
	 * Getter for tht object that collided with the other.
	 *
	 * @return The object that collided with the other.
	 */
	@NotNull
	public final Collidable getObject1() {
		return object1;
	}

	/**
	 * Getter for the object collided by the other.
	 *
	 * @return The object collided by the other.
	 */
	@NotNull
	public final Collidable getObject2() {
		return object2;
	}

	/**
	 * Basically creates a new CollisionEvent with 2 blank Collidable objects.
	 */
	@Override
	@NotNull
	public CollisionEvent clone() {
		return new CollisionEvent(new DefaultCollidable(), new DefaultCollidable());
	}

	@Override
	@NotNull
	public String toString() {
		return "CollisionEvent between " + object1 + " and " + object2;
	}

	/**
	 * Getter for the cancelled value.
	 *
	 * @return The cancelled to get.
	 */
	@Override
	public final boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Setter for the cancelled value.
	 *
	 * @param cancelled The cancelled to set.
	 */
	@Override
	public final void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
