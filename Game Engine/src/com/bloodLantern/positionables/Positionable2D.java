package com.bloodLantern.positionables;

/**
 * Represents something positionable on a 2D surface.
 *
 * @author BloodLantern
 */
public interface Positionable2D {

	/**
	 * Getter for the X coordinate of this Positionable2D object.
	 *
	 * @return the X coordinate of this Positionable2D object.
	 */
	public double getX();

	/**
	 * Setter for the X coordinate of this Positionable2D object.
	 *
	 * @params y the X position of this Positionable2D object to set
	 */
	public void setX(double x);

	/**
	 * Getter for the Y coordinate of this Positionable2D object.
	 *
	 * @return the Y coordinate of this Positionable2D object.
	 */
	public double getY();

	/**
	 * Setter for the Y coordinate of this Positionable2D object.
	 *
	 * @params y the Y position of this Positionable2D object to set
	 */
	public void setY(double y);

}
