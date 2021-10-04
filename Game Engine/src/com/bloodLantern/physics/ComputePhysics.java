package com.bloodLantern.physics;

import java.util.Timer;
import java.util.TimerTask;

import com.bloodLantern.renderer.Renderer;
import com.bloodLantern.renderer.renderables.Renderable2D;

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
		start();
	}
	
	private void start() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			/**
			 * All physics calculations must be made through this method.
			 * 
			 * @Override
			 */
			@Override
			public void run() {
				try {
					try {
						synchronized (this) {
							wait(Physics2D.UPDATE_RATE);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Calculates momentum for each Physic object
					for (Renderable2D c : renderer.getRendering())
						if (c instanceof Physic2D)
							Physics2D.calcMomentum((Physic2D) c, renderer);
				} catch (Exception e) {
					System.err.println("Exception in physic update loop.");
					e.printStackTrace();
				}
			}
		}, 0, Physics2D.UPDATE_RATE);
	}

}
