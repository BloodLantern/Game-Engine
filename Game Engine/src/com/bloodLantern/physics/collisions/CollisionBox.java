package com.bloodLantern.physics.collisions;

import com.bloodLantern.renderer.renderables.Texture;

/**
 * Basic {@link DefaultCollidable} implementation for a square hitbox.
 *
 * @author BloodLantern
 */
public class CollisionBox extends DefaultCollidable implements Triggerable {

	private double width;
	private double height;
	private boolean trigger;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param trigger
	 */
	public CollisionBox(double x, double y, double width, double height, boolean trigger) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.trigger = trigger;
	}

	/**
	 * @param x
	 * @param y
	 * @param texture used to get collisionBox's size
	 * @param trigger
	 */
	public CollisionBox(double x, double y, Texture texture, boolean trigger) {
		this.x = x;
		this.y = y;
		this.width = texture.getImage().getWidth();
		this.height = texture.getImage().getHeight();
		this.trigger = trigger;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "CollisionBox(" + x + ";" + y + ") of size(" + width + ";" + height + ")"
				+ (trigger ? " Trigger" : " Not trigger");
	}

	/**
	 * Getter for the trigger value.
	 *
	 * @return The trigger to get.
	 */
	@Override
	public boolean isTrigger() {
		return trigger;
	}

	/**
	 * Setter for the trigger value.
	 *
	 * @param trigger The trigger to set.
	 */
	@Override
	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

}
