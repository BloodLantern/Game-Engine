package com.bloodLantern.positionables;

/**
 *
 * @author BloodLantern
 */
public interface Positionable3D extends Positionable2D {

	public double getZ();

	public void setZ(double vZ);

	public double getZBefore();

	public void setZBefore(double zBefore);

}
