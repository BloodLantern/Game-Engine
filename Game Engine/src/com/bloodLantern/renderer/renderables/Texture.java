package com.bloodLantern.renderer.renderables;

import com.bloodLantern.annotations.NotNull;

import javafx.scene.image.Image;

/**
 * A Texture object represents an image for a
 * {@link com.bloodLantern.renderer.renderables.Renderable2D Renderable object}.
 * 
 * @author BloodLantern
 */
public class Texture implements Renderable2D {

	/**
	 * Image used to represent this Texture object.
	 */
	private final Image image;

	/**
	 * X coordinate of this Renderable object. If nested into another object
	 * (example: {@link Animation}), it is an offset.
	 */
	private double x = 0;

	/**
	 * Y coordinate of this Renderable object. If nested into another object
	 * (example: {@link Animation}), it is an offset.
	 */
	private double y = 0;

	/**
	 * Last X position.
	 */
	private double xBefore = 0;

	/**
	 * Last Y position.
	 */
	private double yBefore = 0;

	/**
	 * Integer equivalence of {@link #x}.
	 */
	private int roundedX = 0;

	/**
	 * Integer equivalence of {@link #y}.
	 */
	private int roundedY = 0;

	/**
	 * Default constructor.
	 */
	public Texture(@NotNull String filePath) {
		image = new Image(filePath);
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Getter for the image's height.
	 * 
	 * @return the image's height
	 */
	public double getHeight() {
		return image.getHeight();
	}

	/**
	 * Getter for the image's width.
	 * 
	 * @return the image's width
	 */
	public double getWidth() {
		return image.getWidth();
	}

	public double getXBefore() {
		return xBefore;
	}

	public void setXBefore(double xBefore) {
		this.xBefore = xBefore;
	}

	public double getYBefore() {
		return yBefore;
	}

	public void setYBefore(double yBefore) {
		this.yBefore = yBefore;
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
	 * Because it is already a Texture object, it returns itself.
	 * 
	 * @Override
	 */
	@Override
	public Texture getTexture() {
		return this;
	}

	/**
	 * @Override
	 */
	@Override
	public int getRoundedX() {
		return roundedX;
	}

	/**
	 * @Override
	 */
	@Override
	public void setRoundedX(int x) {
		roundedX = x;
	}

	/**
	 * @Override
	 */
	@Override
	public int getRoundedY() {
		return roundedY;
	}

	/**
	 * @Override
	 */
	@Override
	public void setRoundedY(int y) {
		roundedY = y;
	}

}
