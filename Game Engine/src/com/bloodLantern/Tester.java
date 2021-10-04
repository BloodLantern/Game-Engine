package com.bloodLantern;

import com.bloodLantern.main.GameEngine;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.renderables.Texture;

/**
 * Just a class containing a {@code public static void main(String[] args)}
 * method to test some features.
 * 
 * @author BloodLantern, cc_hunter_boy
 */
final class Tester {

	/**
	 * First method to be called when the program launches.
	 */
	public static void main(String[] args) {
		GameEngine.setup(args, "Game Engine Tests", 800, 600);
		
		Texture texture = new Texture("Image.png");
		Texture texture2 = new Texture("Image2.png");
		ComponentTester renderable = new ComponentTester(texture, 50, 100, 0, 0, false, true, 0.5f, 0.3f, 10.0f,
				2500.0f, 2500.0f, new CollisionBox(0, 0, texture, false));
		ComponentTester renderable2 = new ComponentTester(texture2, 500, 100, 0, 0, false, true, 1.0f, 0.3f, 10.0f,
				2500.0f, 2500.0f, new CollisionBox(0, 0, texture2, false));
		GameEngine.getRenderer().render(renderable);
		GameEngine.getRenderer().render(renderable2);
		renderable.addSpeed(500, 0);
		renderable2.addSpeed(-500, 0);
	}
}
