package com.bloodLantern.physics.collisions;

import com.bloodLantern.renderer.renderables.Texture;

/**
 * Represents a colliding object.
 * 
 * @author BloodLantern
 */
public class CollisionBox extends Collidable {
	
	private double width;
	private double height;

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

}
