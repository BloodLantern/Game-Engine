package com.bloodLantern.physics.collisions;

import com.bloodLantern.positionables.Positionable2D;

/**
 * 
 * @author BloodLantern
 */
public abstract class Collidable implements Positionable2D {

	/**
	 * Relative to the object attached with it.
	 */
	protected double x;

	/**
	 * Relative to the object attached with it.
	 */
	protected double y;
	
	/**
	 * Last X position.
	 */
	protected double xBefore = 0;
	
	/**
	 * Last Y position.
	 */
	protected double yBefore = 0;
	
	/**
	 * If true, this Collidable object will not collide with other objects but will fire a TriggerEvent instead.
	 */
	protected boolean trigger;
	
	/**
	 * 
	 */
	protected Collidable() {
	}

	/**
	 * @Override
	 */
	@Override
	public double getX() {
		return x;
	}

	/**
	 * @Override
	 */
	@Override
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @Override
	 */
	@Override
	public double getY() {
		return y;
	}

	/**
	 * @Override
	 */
	@Override
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @Override
	 */
	@Override
	public double getXBefore() {
		return xBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public void setXBefore(double xBefore) {
		this.xBefore = xBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public double getYBefore() {
		return yBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public void setYBefore(double yBefore) {
		this.yBefore = yBefore;
	}

	/**
	 * @return the trigger
	 */
	public boolean isTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

}
