package com.bloodLantern.physics;

import java.util.ArrayList;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.events.CollisionEvent;
import com.bloodLantern.events.EventManager;
import com.bloodLantern.events.TriggerEvent;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.physics.collisions.Triggerable;
import com.bloodLantern.renderer.Renderable2D;
import com.bloodLantern.renderer.Renderer;

/**
 * Class containing useful static methods when working with Physics (Physic
 * objects).
 *
 * @author cc_hunter_boy
 */
public final class Physics2D {

	/**
	 * G constant in p/s2. Increasing this value will cause objects to fall faster.
	 */
	private static final double GRAVITY = 490;

	/**
	 * Constant in kg/p3. Increasing this value will cause objects to lose their
	 * speed faster.
	 */
	private static final double AIR_DENSITY = 0.00001;

	/**
	 * Cannot be instantiated.
	 */
	private Physics2D() {
	}

	/**
	 * This method is used to calculate the momentum of an {@link Apply2DPhysics}
	 * object.
	 */
	public static void calcMomentum(@NotNull Apply2DPhysics object, @NotNull Renderer renderer) {

		object.setX(object.getVX() / Renderer.getFrameRate() + object.getX());
		object.setY(object.getVY() / Renderer.getFrameRate() + object.getY());

		object.addSpeed(
				-Math.abs(object.getVX()) * object.getVX() * AIR_DENSITY * object.getAerodynamismX()
						/ (2 * Renderer.getFrameRate() * object.getWeight()),
				-Math.abs(object.getVY()) * object.getVY() * AIR_DENSITY * object.getAerodynamismY()
						/ (2 * Renderer.getFrameRate() * object.getWeight()));

		if (object.isAffectedByGravity())
			object.addSpeed(0, GRAVITY / Renderer.getFrameRate());

		calcCollisions(object, renderer);

	}

	/**
	 * This method is used to check whether there is a collision between an object
	 * and another one in the Renderer.
	 *
	 * @param object   the object to check
	 * @param renderer the Renderer of the object
	 * @return a List of Physic2D object colliding with this object
	 */
	@NotNull
	private static ArrayList<Apply2DPhysics> collisionCheck(@NotNull Apply2DPhysics object,
			@NotNull Renderer renderer) {
		ArrayList<Apply2DPhysics> list = new ArrayList<>();
		for (Renderable2D r : renderer.getRendering())
			if (r instanceof Apply2DPhysics) {
				// If the checked object is equal to the one we check then continue loop
				if (r.equals(object))
					continue;

				// Get variables
				Apply2DPhysics other = (Apply2DPhysics) r;
				CollisionBox otherBox = (CollisionBox) other.getCollisionBox();
				CollisionBox objectBox = (CollisionBox) object.getCollisionBox();
				// Big check for collision
				if ((object.getX() + objectBox.getX() + objectBox.getWidth() > other.getX() + otherBox.getX())
						&& (object.getX() + objectBox.getX() < other.getX() + otherBox.getX() + otherBox.getWidth())
						&& (object.getY() + objectBox.getY() + objectBox.getHeight() > other.getY() + otherBox.getY())
						&& (object.getY() + objectBox.getY() < other.getY() + otherBox.getY() + otherBox.getHeight()))
					// Add to the list if it collides and if it isn't already in
					if (!list.contains(other))
						list.add(other);

			}
		return list;
	}

	/**
	 * This method calculates the collisions of this {@link Apply2DPhysics} object
	 * in the specified {@link com.bloodLantern.renderer.Renderer Renderer}.
	 *
	 * @param object   the object to check
	 * @param renderer the Renderer containing the object
	 */
	public static void calcCollisions(@NotNull Apply2DPhysics object, @NotNull Renderer renderer) {
		ArrayList<Apply2DPhysics> list = collisionCheck(object, renderer);
		// If the list isn't empty then it hit something
		if (list.size() != 0) {
			CollisionBox objectCb = (CollisionBox) object.getCollisionBox();
			double otherCenterX = 0;
			double otherCenterY = 0;
			double objectCenterX = object.getX() + (objectCb.getX() + objectCb.getWidth() / 2);
			double objectCenterY = object.getY() + (objectCb.getY() + objectCb.getHeight() / 2);
			double objectVX = object.getVX();
			double objectVY = object.getVY();

			// Check to find what side have been hit
			for (Apply2DPhysics other : list) {
				CollisionBox otherCb = (CollisionBox) other.getCollisionBox();
				otherCenterX = other.getX() + (otherCb.getX() + otherCb.getWidth()) / 2;
				otherCenterY = other.getY() + (otherCb.getY() + otherCb.getHeight()) / 2;
				double otherVX = other.getVX();
				double otherVY = other.getVY();

				// Doesn't collide if set to trigger
				if (!otherCb.isTrigger() && !objectCb.isTrigger())
					if (EventManager.fireEvent(new CollisionEvent(objectCb, otherCb))) {

						// Calculate the distance between centers
						double diffX = objectCenterX - otherCenterX;
						double diffY = objectCenterY - otherCenterY;

						// Calculate the minimum distance to separate along X and Y
						double minXDist = objectCb.getWidth() / 2 + otherCb.getWidth() / 2;
						double minYDist = objectCb.getHeight() / 2 + otherCb.getHeight() / 2;

						// Calculate the depth of collision for both the X and Y axis
						double depthX = diffX > 0 ? minXDist - diffX : -minXDist - diffX;
						double depthY = diffY > 0 ? minYDist - diffY : -minYDist - diffY;

						// Now that you have the depth, you can pick the smaller depth and move
						// along that axis.
						if (depthX != 0 && depthY != 0) {

							if (Math.abs(depthX) < Math.abs(depthY)) {
								// Collision along the X axis. React accordingly

								double object2VX = (objectVX * (object.getWeight() - other.getWeight())
										+ 2 * other.getWeight() * otherVX) / (object.getWeight() + other.getWeight());
								double other2VX = (otherVX * (other.getWeight() - object.getWeight())
										+ 2 * object.getWeight() * objectVX) / (object.getWeight() + other.getWeight());

								// Bounciness & Friction
								if (!other.isMovable()) {
									object.setX(object.getX() + depthX);
									object.setVX(-objectVX * object.getBouciness() * other.getBouciness());
									object.setVY(objectVY
											- (objectVY - otherVY) * object.getFriction() * other.getFriction());
								} else if (!object.isMovable()) {
									other.setX(other.getX() - depthX);
									other.setVX(-otherVX * object.getBouciness() * other.getBouciness());
									other.setVY(otherVY
											- (otherVY - objectVY) * object.getFriction() * other.getFriction());
								} else {
									object.setX(object.getX() + depthX);
									object.setVY(objectVY
											- (objectVY - otherVY) * object.getFriction() * other.getFriction());
									other.setVY(otherVY
											- (otherVY - objectVY) * object.getFriction() * other.getFriction());
									if (objectVX != 0 && otherVX != 0) {
										if (objectVX / Math.abs(objectVX) == otherVX / Math.abs(otherVX)) {
											if (Math.abs(objectVX) > Math.abs(otherVX)) {
												object.setVX(object2VX * object.getBouciness() * other.getBouciness());
												other.setVX(otherVX
														+ other2VX * object.getBouciness() * other.getBouciness());
											} else {
												object.setVX(objectVX
														+ object2VX * object.getBouciness() * other.getBouciness());
												other.setVX(other2VX * object.getBouciness() * other.getBouciness());
											}
										} else {
											object.setVX(object2VX * object.getBouciness() * other.getBouciness());
											other.setVX(other2VX * object.getBouciness() * other.getBouciness());
										}
									} else {
										object.setVX(object2VX * object.getBouciness() * other.getBouciness());
										other.setVX(other2VX * object.getBouciness() * other.getBouciness());
									}
								}

							} else {
								// Collision along the Y axis.

								double object2VY = (objectVY * (object.getWeight() - other.getWeight())
										+ 2 * other.getWeight() * otherVY) / (object.getWeight() + other.getWeight());
								double other2VY = (otherVY * (other.getWeight() - object.getWeight())
										+ 2 * object.getWeight() * objectVY) / (object.getWeight() + other.getWeight());

								// Bounciness & Friction
								if (!other.isMovable()) {
									object.setY(object.getY() + depthY);
									object.setVY(-objectVY * object.getBouciness() * other.getBouciness());
									object.setVX(objectVX
											- (objectVX - otherVX) * object.getFriction() * other.getFriction());
								} else if (!object.isMovable()) {
									other.setY(other.getY() - depthY);
									other.setVY(-otherVY * object.getBouciness() * other.getBouciness());
									other.setVX(otherVX
											- (otherVX - objectVX) * object.getFriction() * other.getFriction());
								} else {
									object.setY(object.getY() + depthY);
									object.setVX(objectVX
											- (objectVX - otherVX) * object.getFriction() * other.getFriction());
									other.setVX(otherVX
											- (otherVX - objectVX) * object.getFriction() * other.getFriction());
									if (objectVY != 0 && otherVY != 0) {
										if (objectVY / Math.abs(objectVY) == otherVY / Math.abs(otherVY)) {
											if (Math.abs(objectVY) > Math.abs(otherVY)) {
												object.setVY(object2VY * object.getBouciness() * object.getBouciness());
												other.setVY(otherVY
														+ other2VY * object.getBouciness() * other.getBouciness());
											} else {
												object.setVY(objectVY
														+ object2VY * object.getBouciness() * other.getBouciness());
												other.setVY(other2VY * object.getBouciness() * other.getBouciness());
											}
										} else {
											object.setVY(object2VY * object.getBouciness() * other.getBouciness());
											other.setVY(other2VY * object.getBouciness() * other.getBouciness());
										}
									} else {
										object.setVY(object2VY * object.getBouciness() * other.getBouciness());
										other.setVY(other2VY * object.getBouciness() * other.getBouciness());
									}
								}
							}
						}
						if (Math.abs(object.getVX()) < 0.01)
							object.setVX(0);
						if (Math.abs(other.getVX()) < 0.01)
							other.setVX(0);
						if (Math.abs(object.getVY()) < 0.01)
							object.setVY(0);
						if (Math.abs(other.getVY()) < 0.01)
							other.setVY(0);
					} else {
					}
				else {
					// Find which of the two objects is the Triggerable (they may be both)
					boolean objectIsTriggerable = false;
					if (objectCb instanceof Triggerable)
						objectIsTriggerable = true;
					// Fire a TriggerEvent
					EventManager.fireEvent(new TriggerEvent(objectIsTriggerable ? objectCb : otherCb,
							objectIsTriggerable ? otherCb : objectCb));
				}
			}
		}
	}

}
