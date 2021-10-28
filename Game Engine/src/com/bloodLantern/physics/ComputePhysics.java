package com.bloodLantern.physics;

import java.util.Timer;
import java.util.TimerTask;

import com.bloodLantern.main.GameEngine;
import com.bloodLantern.renderer.Renderable2D;
import com.bloodLantern.renderer.Renderer;

/**
 * Class containing the physic update loop.
 *
 * @author BloodLantern
 */
public class ComputePhysics {

	/**
	 * Whether an instance of this class has been created yet.
	 */
	private static boolean created = false;

	/**
	 * The Renderer attached to the instance.
	 */
	private Renderer renderer;

	/**
	 * Default constructor.
	 */
	public ComputePhysics(Renderer renderer) {
		if (created)
			throw new IllegalArgumentException("The ComputePhysics class may only be instantiated one time.");
		else
			created = true;
		this.renderer = renderer;
		begin();
	}

	private void begin() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			/**
			 * All physics calculations must be made through this method.
			 *
			 * @Override
			 */
			@Override
			public void run() {
				if (!GameEngine.isRunning())
					cancel();
				try {
					// Calculates momentum for each Physic object
					for (Renderable2D c : renderer.getRendering())
						if (c instanceof Apply2DPhysics)
							Physics2D.calcSpeed((Apply2DPhysics) c, renderer);
				} catch (Exception e) {
					System.err.println("Exception in physic update loop.");
					e.printStackTrace();
				}
			}
		}, 0, 1000 / Renderer.getFrameRate());
	}

}
