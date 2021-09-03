package com.bloodLantern.renderer;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;
import com.bloodLantern.renderer.renderables.Animation;
import com.bloodLantern.renderer.renderables.Renderable2D;
import com.bloodLantern.renderer.renderables.Texture;
import com.bloodLantern.renderer.renderables.movements.Movements;

/**
 * A Renderer is an object to add to a frame. It is used to render
 * {@link com.bloodLantern.renderer.renderables Renderable objects}. It acts as
 * a Container for Components.
 * 
 * @author BloodLantern
 */
public class Renderer extends JPanel implements Runnable {

	/**
	 * The frame rate of the screen used to display the application.
	 */
	public static final int FRAME_RATE = 60;
	/**
	 * List containing every Renderable2D object currently rendered by this Renderer
	 * object.
	 */
	private final ArrayList<Renderable2D> rendering = new ArrayList<Renderable2D>();
	/**
	 * Whether the Renderer is currently active.
	 */
	public boolean running = true;

	/**
	 * Default constructor for Swing JFrame.
	 * 
	 * @param jFrame a Swing JFrame.
	 */
	public Renderer(JFrame jFrame) {
		jFrame.add(this);
	}

	/**
	 * Default constructor for AWT Frame.
	 * 
	 * @param frame an AWT Frame.
	 */
	public Renderer(Frame frame) {
		frame.add(this);
	}

	/**
	 * Used to render a Renderable2D object at a specified location.
	 * 
	 * @param renderable2D the Component to render.
	 * @param x            the x position where the Component should be rendered.
	 * @param y            the y position where the Component should be rendered.
	 * 
	 * @throws NullPointerException if the {@code component} argument is null.
	 */
	public void render(Renderable2D renderable2D, int x, int y) throws NullPointerException {
		getGraphics().drawImage(renderable2D.getTexture().getImage(), x, y, null);
		if (!rendering.contains(renderable2D))
			rendering.add(renderable2D);
	}

	/**
	 * Used to render a Renderable2D object at its rounded location.
	 * 
	 * @param renderable2D the object to render
	 */
	public void render(Renderable2D renderable2D) {
		render(renderable2D, renderable2D.getRoundedX(), renderable2D.getRoundedY());
	}

	/**
	 * Renders every single Renderable2D object of the {@code rendering} list.
	 */
	public void renderComponents() {
		if (rendering.size() > 0)
			for (Renderable2D r : rendering)
				render(r, r.getRoundedX(), r.getRoundedY());
	}

	/**
	 * Force stopping rendering a Component.
	 * 
	 * @param renderable2D
	 */
	public void stopRender(Renderable2D renderable2D) {
		Renderable2D r2d = null;
		for (Renderable2D r : rendering)
			if (r.equals(renderable2D))
				r2d = r;
		rendering.remove(r2d);
	}

	/**
	 * Stops rendering a Renderable2D object of the {@code rendering} list, renders
	 * every components using {@link #renderComponents()} and then renders this
	 * object again using {@link #render(Renderable2D)}.
	 * 
	 * @param renderable2D the object to refresh
	 */
	public void refreshRendering(Renderable2D renderable2D) {
		stopRender(renderable2D);
		getGraphics().setColor(getBackground());
		getGraphics().fillRect(0, 0, getWidth(), getHeight());
		renderComponents();
		render(renderable2D);
	}

	/**
	 * Used to move a {@link com.bloodLantern.renderer.renderables.Renderable2D
	 * Renderable2D object} on a {@link com.bloodLantern.renderer.Renderer Renderer
	 * object} with a movement's animation type.
	 * 
	 * @param renderable2D the Renderable2D object to move.
	 * @param xDistance    the X axis distance.
	 * @param yDistance    the Y axis distance.
	 * @param duration     the movement's duration (in milliseconds). Must be
	 *                     positive. Use 0 for an instant movement.
	 * @param moveType     the movement's animation type. Will be
	 *                     {@link com.bloodLantern.renderer.renderables.movements.Movements#NONE
	 *                     none} if null.
	 * @param inOut        an alternate argument. Must be one of
	 *                     {@link com.bloodLantern.renderer.renderables.movements.Movements.Types
	 *                     Movement types}. Used to specify if a movement's
	 *                     animation type should be used only when moving in or when
	 *                     moving out or both. If type parameter is null or equal to
	 *                     {@link com.bloodLantern.renderer.renderables.movements.Movements#NONE
	 *                     none}, this will have not effect.
	 */
	public void move(@NotNull Renderable2D renderable2D, int xDistance, int yDistance, long duration,
			@Nullable Movements moveType, @Nullable Movements.Types inOut) {
		running = true;
		if (moveType == null)
			moveType = Movements.NONE;
		final Movements type = moveType;
		if (inOut == null)
			inOut = Movements.Types.IN_OUT;
		final Movements.Types inout = inOut;

		if (xDistance == 0 && yDistance == 0)
			return;

		if (duration == 0) {
			renderable2D.setRoundedX(renderable2D.getRoundedX() + xDistance);
			renderable2D.setRoundedY(renderable2D.getRoundedY() + yDistance);
			return;
		} else if (duration < 0) {
			throw new IllegalArgumentException("Duration argument can't be negative!");
		}

		// Final variables declarations (used for the anonymous TimerTask)
		final long fDuration = duration;
		// X distance
		final int fXDistance = xDistance;
		// Y distance
		final int fYDistance = yDistance;
		// starting X pos
		final int startingXPos = renderable2D.getRoundedX();
		// starting Y pos
		final int startingYPos = renderable2D.getRoundedY();
		// starting time
		final long startTime = System.currentTimeMillis();

		Timer timer = new Timer();
		// NONE Movement type
		if (type.equals(Movements.NONE))
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					synchronized (this) {
						while (System.currentTimeMillis() - startTime < duration) {
							// Wait
							try {
								wait(duration / (Math.abs(xDistance) + Math.abs(yDistance)));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// Remove last position image by rendering everything except the component
							stopRender(renderable2D);

							move(renderable2D, xDistance, yDistance, 1);

							renderComponents();
							getGraphics().setColor(getBackground());
							getGraphics().fillRect(0, 0, getWidth(), getHeight());
							render(renderable2D);
						}
						running = false;
						cancel();
					}
				}

			}, 0, FRAME_RATE);
		// Other Movement types
		else
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if (System.currentTimeMillis() - startTime > fDuration) {
						running = false;
						cancel();
					}
					try {
						renderable2D
								.setRoundedX((int) ((float) type.getClazz()
										.getMethod(inout.getMethodName(), float.class, float.class, float.class,
												float.class)
										.invoke(null, System.currentTimeMillis() - startTime, startingXPos, fXDistance,
												fDuration)));
						renderable2D
								.setRoundedY((int) ((float) type.getClazz()
										.getMethod(inout.getMethodName(), float.class, float.class, float.class,
												float.class)
										.invoke(null, System.currentTimeMillis() - startTime, startingYPos, fYDistance,
												fDuration)));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
						running = false;
						cancel();
					}
				}

			}, 0, FRAME_RATE);
	}

	/**
	 * Used to move a {@link com.bloodLantern.renderer.renderables.Renderable2D
	 * Component object} on a {@link com.bloodLantern.renderer.Renderer Renderer
	 * object} without movement's animation type
	 * ({@link com.bloodLantern.renderer.renderables.movements.Movements Movements
	 * enum's fields}).
	 * <p>
	 * Equal to:
	 * <li>{@code move(component, xDistance, yDistance, time, null)}
	 * <li>{@code move(component, xDistance, yDistance, time, Movements.NONE)}.
	 * 
	 * @param renderable2D the Component object to move.
	 * @param xDistance    the X axis distance.
	 * @param yDistance    the Y axis distance.
	 * @param time         the movement's duration.
	 * 
	 * @see com.bloodLantern.renderer.Renderer#move(Renderable2D, int, int, long,
	 *      Movements, Movements.Types) Full move method
	 */
	public void move(Renderable2D renderable2D, int xDistance, int yDistance, long time) {
		move(renderable2D, xDistance, yDistance, time, Movements.NONE, null);
	}

	private void move(Renderable2D renderable2D, int xDistance, int yDistance, int power) {
		// X movement
		if (xDistance > 0)
			renderable2D.setRoundedX(renderable2D.getRoundedX() + power);
		else if (xDistance < 0)
			renderable2D.setRoundedX(renderable2D.getRoundedX() - power);
		// Y movement
		if (yDistance > 0)
			renderable2D.setRoundedY(renderable2D.getRoundedY() + power);
		else if (yDistance < 0)
			renderable2D.setRoundedY(renderable2D.getRoundedY() - power);
	}

	/**
	 * Renders this Texture's Image as this Renderer's background.
	 * 
	 * @param bg the Texture of the Image to set as background.
	 */
	public void setBackground(Texture bg) {
		getGraphics().drawImage(bg.getImage(), 0, 0, null);
	}

	/**
	 * Renders this Animation as this Renderer's background.
	 * 
	 * @param bg the Animation to set as background.
	 */
	public void setBackground(Animation bg) {
		// TODO Renderer animation background
	}

	@Override
	public void run() {
		synchronized (this) {
			while (running) {
				try {
					wait(FRAME_RATE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				renderComponents();
			}
		}
	}

	/**
	 * @return the rendering
	 */
	public final ArrayList<Renderable2D> getRendering() {
		return rendering;
	}

}
