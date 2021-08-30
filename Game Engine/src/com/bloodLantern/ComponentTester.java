package com.bloodLantern;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;
import com.bloodLantern.physics.Physic2D;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.renderables.Renderable2D;
import com.bloodLantern.renderer.renderables.Texture;

public class ComponentTester implements Physic2D, Renderable2D {

	private final Texture texture;
	private int roundedX = 0;
	private int roundedY = 0;
	private double x = 0;
	private double y = 0;
	private double vX = 0;
	private double vY = 0;
	private double xBefore = 0;
	private double yBefore = 0;
	private boolean affectedByGravity = true;
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
			float bounciness, float friction, float weight, @Nullable CollisionBox collisionBox) {
		this.texture = texture;
		this.roundedX = x;
		this.roundedY = y;
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
		this.affectedByGravity = affectedByGravity;
		this.bounciness = bounciness;
		this.friction = friction;
		this.weight = weight;
		if (collisionBox != null)
			this.collisionBox = collisionBox;
	}

	/**
	 * @Override
	 */
	@Override
	public void addMomentum(double vX, double vY) {
		this.vX += vX;
		this.vY += vY;
	}

	/**
	 * @Override
	 */
	@Override
	public void setMomentum(double vX, double vY) {
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
	public CollisionBox getCollisionBox() {
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
	public double getXBefore() {
		return xBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public void setXBefore(double xBefore) {
		this.xBefore = xBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public double getYBefore() {
		return yBefore;
	}

	/**
	 * @Override
	 */
	@Override
	public void setYBefore(double yBefore) {
		this.yBefore = yBefore;
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

}