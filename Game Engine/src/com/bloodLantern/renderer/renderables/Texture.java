package com.bloodLantern.renderer.renderables;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	/*
	 * Integer equivalence of {@link x}.
	 */
	private int roundedX = 0;
	/**
	 * Integer equivalence of {@link y}.
	 */
	private int roundedY = 0;

	/**
	 * Default constructor.
	 * 
	 * @throws IOException if the image file isn't a valid file.
	 */
	public Texture(File image) throws IOException {
		this.image = (Image) ImageIO.read(image);
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
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
