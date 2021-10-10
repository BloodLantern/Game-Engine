package com.bloodLantern;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;
import com.bloodLantern.physics.Apply2DPhysics;
import com.bloodLantern.physics.collisions.Collidable;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.renderables.Renderable2D;
import com.bloodLantern.renderer.renderables.Texture;

public class ComponentTester implements Apply2DPhysics, Renderable2D {

	private final Texture texture;
	private int roundedX = 0;
	private int roundedY = 0;
	private double x = 0;
	private double y = 0;
	/**
	 * In p/s.
	 */
	private double vX = 0;
	/**
	 * In p/s.
	 */
	private double vY = 0;
	private boolean affectedByGravity = true;
	private boolean movable = true;
	/**
	 * In Kg.
	 */
	private float weight = 1.0f;
	/**
	 * Bounciness between 0 and 1.
	 */
	private float bounciness = 0.5f;
	/**
	 * Friction between 0 and 1.
	 */
	private float friction = 0.3f;
	/**
	 * Aerodynamism is surface in p2 multiplied by a coefficient for the shape. (X
	 * axis)
	 */
	private float aerodynamismX = 2500.0f;
	/**
	 * Aerodynamism is surface in p2 multiplied by a coefficient for the shape. (Y
	 * axis)
	 */
	private float aerodynamismY = 2500.0f;
	private CollisionBox collisionBox;

	public ComponentTester(Texture texture) {
		this.texture = texture;
	}

	/**
	 * @param texture
	 * @param x
	 * @param y
	 * @param vX
	 * @param vY
	 * @param affectedByGravity
	 * @param bounciness
	 * @param friction
	 * @param collisionBox
	 */
	public ComponentTester(@NotNull Texture texture, int x, int y, double vX, double vY, boolean affectedByGravity,
			boolean movable, float bounciness, float friction, float weight, float aerodynamismX, float aerodynamismY,
			@Nullable CollisionBox collisionBox) {
		if (texture == null)
			throw new IllegalArgumentException(
					"The Texture argument from the ComponentTester constructor musn't be null!");
		this.texture = texture;
		this.roundedX = x;
		this.roundedY = y;
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
		this.affectedByGravity = affectedByGravity;
		this.movable = movable;
		this.bounciness = bounciness;
		this.friction = friction;
		this.weight = weight;
		this.aerodynamismX = aerodynamismX;
		this.aerodynamismY = aerodynamismY;
		this.collisionBox = collisionBox;
	}

	/**
	 * Returns a String representation of this object as this one:
	 * {@code ComponentTester - x, y, texture.getName()}
	 *
	 * @Override
	 */
	@Override
	public String toString() {
		return "ComponentTester - (" + x + " ; " + y + ")";
	}

	/**
	 * @Override
	 */
	@Override
	public void addSpeed(double vX, double vY) {
		this.vX += vX;
		this.vY += vY;
	}

	/**
	 * @Override
	 */
	@Override
	public void setSpeed(double vX, double vY) {
		this.vX = vX;
		this.vY = vY;
	}

	/**
	 * @Override
	 */
	@Override
	public Texture getTexture() {
		return texture;
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
		this.x = x;
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
		this.y = y;
	}

	/**
	 * @Override
	 */
	@Override
	public double getVX() {
		return vX;
	}

	/**
	 * @Override
	 */
	@Override
	public void setVX(double vX) {
		this.vX = vX;
	}

	/**
	 * @Override
	 */
	@Override
	public double getVY() {
		return vY;
	}

	/**
	 * @Override
	 */
	@Override
	public void setVY(double vY) {
		this.vY = vY;
	}

	/**
	 * @Override
	 */
	@Override
	public boolean isAffectedByGravity() {
		return affectedByGravity;
	}

	/**
	 * @Override
	 */
	@Override
	public void setAffectedByGravity(boolean affectedByGravity) {
		this.affectedByGravity = affectedByGravity;
	}

	/**
	 * @Override
	 */
	@Override
	public boolean isMovable() {
		return movable;
	}

	/**
	 * @Override
	 */
	@Override
	public void setMovable(boolean movable) {
		this.movable = movable;
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
	public void setX(double vX) {
		x = vX;
		roundedX = (int) Math.round(x);

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
	public void setY(double vY) {
		y = vY;
		roundedY = (int) Math.round(y);
	}

	/**
	 * @Override
	 */
	@Override
	public float getBouciness() {
		return bounciness;
	}

	/**
	 * @Override
	 */
	@Override
	public void setBounciness(float bounciness) {
		this.bounciness = bounciness;
	}

	/**
	 * @Override
	 */
	@Override
	public Collidable getCollisionBox() {
		return collisionBox;
	}

	/**
	 * @Override
	 */
	@Override
	public boolean hasCollisionBox() {
		return collisionBox != null;
	}

	/**
	 * @Override
	 */
	@Override
	public float getFriction() {
		return friction;
	}

	/**
	 * @Override
	 */
	@Override
	public void setFriction(float friction) {
		this.friction = friction;
	}

	/**
	 * @Override
	 */
	@Override
	public final float getWeight() {
		return weight;
	}

	/**
	 * @Override
	 */
	@Override
	public final void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * @Override
	 */
	@Override
	public final float getAerodynamismX() {
		return aerodynamismX;
	}

	/**
	 * @Override
	 */
	@Override
	public final void setAerodynamismX(float aerodynamismX) {
		this.aerodynamismX = aerodynamismX;
	}

	/**
	 * @Override
	 */
	@Override
	public final float getAerodynamismY() {
		return aerodynamismY;
	}

	/**
	 * @Override
	 */
	@Override
	public final void setAerodynamismY(float aerodynamismY) {
		this.aerodynamismY = aerodynamismY;
	}

}