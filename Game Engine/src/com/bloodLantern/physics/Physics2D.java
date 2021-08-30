package com.bloodLantern.physics;

import static java.awt.geom.Line2D.linesIntersect;

import java.util.ArrayList;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.Renderer;
import com.bloodLantern.renderer.renderables.Renderable2D;

/**
 * Class containing useful static methods when working with Physics (Physic
 * objects).
 * 
 * @author BloodLantern
 */
public final class Physics2D {

	/**
	 * G constant. Increasing this value will cause objects to fall faster.
	 */
	private static final double GRAVITY = 0.01;

	/**
	 * Cannot be instantiated.
	 */
	private Physics2D() {
	}

	/**
	 * This method is used to calculate the momentum of a {@link Physic2D} object.
	 */
	public static void calcMomentum(Physic2D object) {
		object.setXBefore(object.getX());
		object.setYBefore(object.getY());

		object.setX(object.getVX() / 50 + object.getX());
		object.setY(object.getVY() / 50 + object.getY());

		object.addMomentum(-object.getVX() / 400, -object.getVY() / 400);

		if (object.isAffectedByGravity())
			object.addMomentum(0, Math.abs(GRAVITY * object.getVY()) + 0.5);
	}

	/**
	 * This method is used to check whether there is a collision between an object
	 * and another one in the Renderer.
	 * 
	 * @param object   the object to check
	 * @param renderer the Renderer of the object
	 * @return a List of Physic2D object colliding with this object
	 */
	@Nullable
	private static ArrayList<Physic2D> collisionCheck(@NotNull Physic2D object, @NotNull Renderer renderer) {
		ArrayList<Physic2D> list = new ArrayList<Physic2D>();
		for (Renderable2D r : renderer.getRendering())
			if (r instanceof Physic2D) {
				// If the checked object is equal to the one we check then continue loop
				if (r.equals(object))
					continue;

				// Get variables
				Physic2D other = (Physic2D) r;
				CollisionBox otherBox = other.getCollisionBox();
				CollisionBox objectBox = object.getCollisionBox();
				// Big check for collision
				if ((object.getX() + objectBox.getX() + objectBox.getWidth() >= other.getX() + otherBox.getX())
						&& (object.getX() + objectBox.getX() <= other.getX() + otherBox.getX() + otherBox.getWidth())
						&& (object.getY() + objectBox.getY() + objectBox.getHeight() >= other.getY() + otherBox.getY())
						&& (object.getY() + objectBox.getY() <= other.getY() + otherBox.getY() + otherBox.getHeight()))
					// Add to the list if it collides and if it isn't already in
					if (!list.contains(other))
						list.add(other);
			}
		return list;
	}

	/**
	 * This method calculates the collisions of this Physic2D object in the
	 * specified Renderer.
	 * 
	 * @param object   the object to check
	 * @param renderer the Renderer containing the object
	 */
	public static void calcCollisions(Physic2D object, Renderer renderer) {
		ArrayList<Physic2D> list = collisionCheck(object, renderer);
		// If the list isn't empty then it hit something
		if (list.size() != 0) {
			CollisionBox objectCb = object.getCollisionBox();
			double xb = 0;
			double yb = 0;
			double x = 0;
			double y = 0;
			double otherCenterX = 0;
			double otherCenterY = 0;
			double objectCenterX = object.getX() + (objectCb.getX() + objectCb.getWidth() / 2);
			double objectCenterY = object.getY() + (objectCb.getY() + objectCb.getHeight() / 2);

			// Check to find what side have been hit
			for (Physic2D other : list) {
				CollisionBox otherCb = other.getCollisionBox();
				otherCenterX = other.getX() + (otherCb.getX() + otherCb.getWidth()) / 2;
				otherCenterY = other.getY() + (otherCb.getY() + otherCb.getHeight()) / 2;

				// Doesn't collide if set to trigger
				if (!otherCb.isTrigger() && !objectCb.isTrigger()) {
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
							object.setX(object.getX() - depthX);
						} else {
							// Collision along the Y axis.
							object.setY(object.getY() - depthY);
						}
					}
					if ((linesIntersect(x, y, xb, yb, other.getX() + otherCb.getX(), other.getY() + otherCb.getY(),
							other.getX() + otherCb.getX() + otherCb.getWidth(), other.getY() + otherCb.getY()))
							|| (linesIntersect(x, y, xb, yb, other.getX() + otherCb.getX(),
									other.getY() + otherCb.getY() + otherCb.getHeight(),
									other.getX() + otherCb.getX() + otherCb.getWidth(),
									other.getY() + otherCb.getY() + otherCb.getHeight()))) {
						object.setVY(-object.getVY() * object.getBouciness() * other.getBouciness());
						object.setY(object.getYBefore());
						object.setVX(object.getVX() - object.getVX() * object.getFriction() * other.getFriction() / 10);
					} else {
						object.setVX(-object.getVX() * object.getBouciness() * other.getBouciness());
						object.setX(object.getXBefore());
						object.setVY(object.getVY() - object.getVY() * object.getFriction() * other.getFriction() / 10);
					}
				}
			}
		}
	}

}
