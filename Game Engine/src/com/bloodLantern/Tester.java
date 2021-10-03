package com.bloodLantern;

import com.bloodLantern.physics.ComputePhysics;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.JavaFXApp;
import com.bloodLantern.renderer.Renderer;
import com.bloodLantern.renderer.renderables.Texture;

import javafx.application.Application;

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
		// Running the window in a background Thread
		new Thread() {
			@Override
			public void run() {
				setName("JavaFX Initializer");
				Application.launch(JavaFXApp.class, args);
			}
		}.start();

		Renderer renderer = new Renderer();

		Texture texture = new Texture("C:\\Users\\maxim\\git\\Game-Engine\\Game Engine\\Image.png");
		Texture texture2 = new Texture("C:\\Users\\maxim\\git\\Game-Engine\\Game Engine\\Image2.png");
		ComponentTester renderable = new ComponentTester(texture, 50, 100, 0, 0, false, 0.5f, 0.3f, 2.0f,
				new CollisionBox(0, 0, texture, false));
		ComponentTester renderable2 = new ComponentTester(texture2, 500, 100, 0, 0, false, 1.0f, 0.3f, 1.0f,
				new CollisionBox(0, 0, texture2, false));
		renderer.render(renderable);
		renderer.render(renderable2);
		renderable.addSpeed(100, 0);
		renderable2.addSpeed(-100, 0);

		new Thread(new ComputePhysics(renderer)) {
			{
				setName("Physics Handler");
				setDaemon(true);
			}
		}.start();
	}
}
