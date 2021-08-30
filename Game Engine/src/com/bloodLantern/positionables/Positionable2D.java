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

	/**
	 * Getter for the last X coordinate of this Positionable2D object.
	 * 
	 * @return the last X coordinate of this Positionable2D object.
	 */
	public double getXBefore();

	/**
	 * Setter for the last X coordinate of this Positionable2D object.
	 * 
	 * @params xBefore the last X position of this Positionable2D object to set
	 */
	public void setXBefore(double xBefore);

	/**
	 * Getter for the last Y coordinate of this Positionable2D object.
	 * 
	 * @return the last Y coordinate of this Positionable2D object.
	 */
	public double getYBefore();

	/**
	 * Setter for the last Y coordinate of this Positionable2D object.
	 * 
	 * @params yBefore the last Y position of this Positionable2D object to set
	 */
	public void setYBefore(double yBefore);

}
