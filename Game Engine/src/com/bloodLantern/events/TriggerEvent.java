package com.bloodLantern.events;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.main.GameEngine;
import com.bloodLantern.physics.collisions.Collidable;
import com.bloodLantern.physics.collisions.DefaultCollidable;
import com.bloodLantern.physics.collisions.DefaultTriggerable;
import com.bloodLantern.physics.collisions.Triggerable;

/**
 * A TriggerEvent is fired when a
 * {@link com.bloodLantern.physics.collisions.DefaultCollidable Collidable}
 * object implementing the
 * {@link com.bloodLantern.physics.collisions.Triggerable Triggerable} interface
 * collides with another Collidable object even if the second one des not
 * implement this interface.
 *
 * @author BloodLantern
 */
public class TriggerEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -382703208280633493L;

	/**
	 * The object that triggered the other.
	 */
	@NotNull
	private final Triggerable object1;

	/**
	 * The object triggered by the other.
	 */
	@NotNull
	private final Collidable object2;

	/**
	 * Constructs a new TriggerEvent. The parameter object1 is supposed to have
	 * collided with object2 but because at least one of them does implement
	 * {@link com.bloodLantern.physics.collisions.Triggerable the Triggerable
	 * interface} they will not collide.
	 *
	 * @param object1 The object that collided with the other.
	 * @param object2 The object collided by the other.
	 */
	public TriggerEvent(@NotNull Triggerable object1, @NotNull Collidable object2) {
		super(object1);
		GameEngine.verifyNotNull("Cannot create a TriggerEvent with a null Collidable/Triggerable object!", object2);
		this.object1 = object1;
		this.object2 = object2;
	}

	@Override
	@NotNull
	public TriggerEvent clone() {
		return new TriggerEvent(new DefaultTriggerable(), new DefaultCollidable());
	}

	@Override
	@NotNull
	public String toString() {
		return "TriggerEvent between " + object1 + " and " + object2;
	}

	/**
	 * Getter for the object1 value.
	 *
	 * @return The object1 to get.
	 */
	@NotNull
	public final Triggerable getObject1() {
		return object1;
	}

	/**
	 * Getter for the object2 value.
	 *
	 * @return The object2 to get.
	 */
	@NotNull
	public final Collidable getObject2() {
		return object2;
	}

}
