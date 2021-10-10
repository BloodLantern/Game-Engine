package com.bloodLantern.physics.collisions;

import com.bloodLantern.positionables.Positionable2D;

/**
 * Basic collidable object interface. A class implementing this interface is
 * supposed to collide with other Collidable instances. If the implementation
 * does implement {@link Triggerable the Triggerable interface} instead, it
 * means that it will not collide with other objects but fire detailed events
 * about it instead.
 *
 * @author BloodLantern
 */
public interface Collidable extends Positionable2D {
}
