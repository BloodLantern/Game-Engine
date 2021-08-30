package com.bloodLantern.physics.collisions;

import com.bloodLantern.renderer.renderables.Texture;

/**
 * Represents a colliding object.
 * 
 * @author BloodLantern
 */
public class CollisionBox extends Collidable {
	
	private int width;
	private int height;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param trigger
	 */
	public CollisionBox(int x, int y, int width, int height, boolean trigger) {
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
	public CollisionBox(int x, int y, Texture texture, boolean trigger) {
		this.x = x;
		this.y = y;
		this.width = texture.getImage().getWidth(null);
		this.height = texture.getImage().getHeight(null);
		this.trigger = trigger;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
