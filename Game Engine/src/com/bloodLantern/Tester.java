package com.bloodLantern;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.bloodLantern.physics.Physic2D;
import com.bloodLantern.physics.Physics2D;
import com.bloodLantern.physics.collisions.CollisionBox;
import com.bloodLantern.renderer.Renderer;
import com.bloodLantern.renderer.renderables.Renderable2D;
import com.bloodLantern.renderer.renderables.Texture;

/**
 * Just a class containing a {@code public static void main(String[] args)}
 * method to test some features.
 * 
 * @author BloodLantern
 */
final class Tester {

	/**
	 * First method called when the program launches.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setTitle("Frame");
		frame.setLocationRelativeTo(null);

		Renderer renderer = new Renderer(frame);
		// EventQueue.invokeLater((Runnable) renderer);
		renderer.setBackground(Color.BLACK);

		ComponentTester renderable = null/*, renderable2 = null, renderable3 = null, renderable4 = null,
				renderable5 = null*/, renderable6 = null;
		try {
			Texture texture = new Texture(new File("Image.png"));
			Texture texture2 = new Texture(new File("Image2.png"));
			renderable = new ComponentTester(texture, 50, 100, 0, 0, false, 0.5f, 0.3f, 1.0f,
					new CollisionBox(0, 0, texture, false));
			renderable6 = new ComponentTester(texture2, 500, 100, 0, 0, false, 1.0f, 0.3f, 1.0f,
					new CollisionBox(0, 0, texture2, false));
			/*renderable2 = new ComponentTester(new Texture(new File("Sol.png")), 50, 0, 0, 0, false, 1.0f, 0.3f,
					new CollisionBox(0, 0, 900, 90, false));
			renderable3 = new ComponentTester(new Texture(new File("Sol.png")), 50, 600, 0, 0, false, 1.0f, 0.3f,
					new CollisionBox(0, 0, 900, 90, false));
			renderable4 = new ComponentTester(new Texture(new File("Mur.png")), 0, 0, 0, 0, false, 1.0f, 0.0f,
					new CollisionBox(0, 0, 90, 900, false));
			renderable5 = new ComponentTester(new Texture(new File("Mur.png")), 1000, 0, 0, 0, false, 1.0f, 0.0f,
					new CollisionBox(0, 0, 90, 900, false));*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderer.render(renderable);
		renderer.render(renderable6);
		renderable.addMomentum(200, 0);
		renderable6.addMomentum(00, 0);
		while (true) {

			/*renderer.render(renderable2);
			renderer.render(renderable3);
			renderer.render(renderable4);
			renderer.render(renderable5);*/


			synchronized (renderable) {
				while (/*renderable.getVX() > 1 || renderable.getVY() > 1 || renderable.getVX() < -1
						|| renderable.getVY() < -1*/ true) {
					try {
						renderable.wait(Renderer.FRAME_RATE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// Calculates momentum for each Physic object
					for (Renderable2D c : renderer.getRendering())
						if (c instanceof Physic2D)
							Physics2D.calcMomentum((Physic2D) c, renderer);

					// Refreshes rendering
					renderer.refreshRendering(renderable);
					renderer.refreshRendering(renderable6);
				}
			}
			/*
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}

	}
}
