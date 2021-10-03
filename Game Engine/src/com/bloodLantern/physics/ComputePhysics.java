package com.bloodLantern.physics;

import com.bloodLantern.renderer.Renderer;
import com.bloodLantern.renderer.renderables.Renderable2D;

/**
 * 
 * @author BloodLantern
 */
public class ComputePhysics implements Runnable {

	private Renderer renderer;

	/**
	 * 
	 */
	public ComputePhysics(Renderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * All physics calculations must be made through this method.
	 * 
	 * @Override
	 */
	@Override
	public void run() {
		while (true) {
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
	}

}
