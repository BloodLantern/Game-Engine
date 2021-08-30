package com.bloodLantern.physics;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.positionables.Positionable2D;

/**
 * 
 * @author BloodLantern
 */
public interface Physic2D extends Positionable2D {

	/**
	 * Adds momentum to this Physic2D object.
	 * 
	 * @param vX X axis momentum to add
	 * @param vY Y axis momentum to add
	 */
	public void addMomentum(double vX, double vY);
	
	/**
	 * Sets momentum to this Physic2D object.
	 * 
	 * @param vX new X axis momentum
	 * @param vY new Y axis momentum
	 */
	public void setMomentum(double vX, double vY);
	
	/**
	 * Getter for the X axis momentum.
	 * 
	 * @return the X axis momentum
	 */
	public double getVX();
	
	public void setVX(double vX);

	/**
	 * Getter for the Y axis momentum.
	 * 
	 * @return the Y axis momentum
	 */
	public double getVY();
	
	public void setVY(double vY);
	
	public boolean isAffectedByGravity();
	
	public void setAffectedByGravity(boolean affectedByGravity);
	
	public float getBouciness();
	
	public void setBounciness(float bounciness);
	
	@NotNull
	public CollisionBox getCollisionBox();
	
	public boolean hasCollisionBox();
	
	public float getFriction();

	public void setFriction(float friction);
	
	public float getWeight();
	
	public void setWeight(float weight);
	
}
