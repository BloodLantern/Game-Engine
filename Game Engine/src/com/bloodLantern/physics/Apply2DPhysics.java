package com.bloodLantern.physics;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.positionables.Positionable2D;

/**
 * 
 * @author BloodLantern
 */
public interface Apply2DPhysics extends Positionable2D {

	/**
	 * Adds momentum to this Physic2D object.
	 * 
	 * @param vX X axis speed to add
	 * @param vY Y axis speed to add
	 */
	public void addSpeed(double vX, double vY);
	
	/**
	 * Sets momentum to this Physic2D object.
	 * 
	 * @param vX new X axis speed
	 * @param vY new Y axis speed
	 */
	public void setSpeed(double vX, double vY);
	
	/**
	 * Getter for the X axis speed.
	 * 
	 * @return the X axis speed
	 */
	public double getVX();
	
	public void setVX(double vX);

	/**
	 * Getter for the Y axis speed.
	 * 
	 * @return the Y axis speed
	 */
	public double getVY();
	
	public void setVY(double vY);
	
	public boolean isAffectedByGravity();
	
	public void setAffectedByGravity(boolean affectedByGravity);
	
	public boolean isMovable();
	
	public void setMovable(boolean movable);
	
	public float getBouciness();
	
	public void setBounciness(float bounciness);
	
	@NotNull
	public CollisionBox getCollisionBox();
	
	public boolean hasCollisionBox();
	
	public float getFriction();

	public void setFriction(float friction);
	
	public float getWeight();
	
	public void setWeight(float weight);
	
	public float getAerodynamismX();
	
	public void setAerodynamismX(float aerodynamismX);

	public float getAerodynamismY();
	
	public void setAerodynamismY(float aerodynamismY);
	
}
