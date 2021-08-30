package com.bloodLantern.renderer.renderables;

import com.bloodLantern.positionables.Positionable2D;

/**
 * A class implementing this interface can be rendered by
 * {@link com.bloodLantern.renderer.Renderer this Game Engine's Renderer class}.
 * 
 * @author BloodLantern
 */
public interface Renderable2D extends Positionable2D {

	/**
	 * Getter for the Texture object of this Component.
	 * 
	 * @return the Texture object
	 */
	public Texture getTexture();

	/**
	 * Getter for the rounded X coordinate value of this Component.
	 * 
	 * @return the rounded X coordinate
	 */
	public int getRoundedX();

	/**
	 * Setter for the rounded X coordinate value of this Component.
	 * 
	 * @param x the rounded X coordinate to set
	 */
	public void setRoundedX(int x);

	/**
	 * Getter for the rounded Y coordinate value of this Component.
	 * 
	 * @return the rounded Y coordinate
	 */
	public int getRoundedY();

	/**
	 * Setter for the rounded Y coordinate value of this Component.
	 * 
	 * @param y the rounded Y coordinate to set
	 */
	public void setRoundedY(int y);

}
