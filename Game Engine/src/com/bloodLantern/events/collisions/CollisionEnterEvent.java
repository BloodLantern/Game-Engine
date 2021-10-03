package com.bloodLantern.events.collisions;

import com.bloodLantern.events.Cancellable;
import com.bloodLantern.physics.collisions.Collidable;

/**
 * 
 * @author BloodLantern
 */
public class CollisionEnterEvent extends CollisionEvent implements Cancellable {
	
	/**
	 * Whether the Event should be cancelled.
	 */
	protected boolean cancelled = false;

	/**
	 * @param object1
	 * @param object2
	 */
	public CollisionEnterEvent(Object source, Collidable object1, Collidable object2) {
		super(source, object1, object2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Override
	 */
	@Override
	public final boolean isCancelled() {
		return cancelled;
	}

	/**
	 * @Override
	 */
	@Override
	public final void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
