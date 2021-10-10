package com.bloodLantern.physics.collisions;

/**
 * Basic {@link Collidable} implementation.
 *
 * @author BloodLantern
 * @see com.bloodLantern.events The Event package
 */
public class DefaultCollidable implements Collidable {

	/**
	 * Relative to the object attached with it.
	 */
	protected double x;

	/**
	 * Relative to the object attached with it.
	 */
	protected double y;

	/**
	 * Constructs a new DefaultCollidable object without any default value.
	 */
	public DefaultCollidable() {
	}

	/**
	 * Constructs a new DefaultCollidable object with x and y coordinates.
	 *
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public DefaultCollidable(double x, double y) {
		this.x = x;
		this.y = y;
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

}
