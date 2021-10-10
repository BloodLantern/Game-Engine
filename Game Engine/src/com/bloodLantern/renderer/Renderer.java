package com.bloodLantern.renderer;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.annotations.Nullable;
import com.bloodLantern.renderer.renderables.Animation;
import com.bloodLantern.renderer.renderables.Renderable2D;
import com.bloodLantern.renderer.renderables.Texture;
import com.bloodLantern.renderer.renderables.movements.Movements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * A Renderer is used to render {@link com.bloodLantern.renderer.renderables
 * Renderable objects}. It acts as a container for components.
 *
 * @author BloodLantern
 */
public class Renderer {

	/**
	 * The default screen refresh rate. Used if and only if {@link #frameRate} is
	 * still equal to -1 at runtime.
	 */
	public static final int FRAME_RATE_DEFAULT = 60;

	/**
	 * The refresh rate of the current screen. Use {@link #FRAME_RATE_DEFAULT}
	 * instead if still equal to -1 at runtime.
	 */
	public static int frameRate = -1;

	/**
	 * The first Renderer instance to be created is stored here. If null, no
	 * Renderer has been created for now.
	 */
	public static Renderer firstRenderer = null;

	/**
	 * List containing every Renderable2D object currently rendered by this Renderer
	 * object.
	 */
	private final ArrayList<Renderable2D> rendering = new ArrayList<>();

	/**
	 * Background Renderable2D
	 */
	private Renderable2D background = null;

	/**
	 * Last frame time (in milliseconds).
	 */
	private long lastFrameTime = -1;

	/**
	 * The time between the last render refresh and this one.
	 */
	private int deltaTime;

	/**
	 * Scene used to render everything.
	 */
	private Scene scene = null;

	/**
	 * Root node where every rendered components must be added.
	 */
	private Group root = new Group();

	static {
		int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode()
				.getRefreshRate();
		frameRate = refreshRate != 0 ? refreshRate : -1;
	}

	/**
	 * Default constructor. Automatically waits for the JavaFXApp instance to set up
	 * the {@link #scene}.
	 */
	public Renderer() {
		if (firstRenderer == null)
			firstRenderer = this;
		else
			throw new IllegalArgumentException("The Renderer class may only be instantiated one time.");
		if (scene == null)
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		// Creating a repeating GUI Task
		Timeline repeatingGuiTask = new Timeline(
				new KeyFrame(Duration.millis(1000.0 / getFrameRate()), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							root.getChildren().clear();
							// Update delta time as well as last frame time
							deltaTime = (int) (System.currentTimeMillis() - lastFrameTime);
							lastFrameTime = System.currentTimeMillis();
							// Actions to execute each frame with the GUI.
							ImageView iView = null;
							// Sets the background
							if (background != null) {
								iView = new ImageView(background.getTexture().getImage());
								iView.setX(0);
								iView.setY(0);
								iView.setFitHeight(scene.getHeight());
								iView.setFitWidth(scene.getWidth());
								iView.setViewOrder(10);
								root.getChildren().add(iView);
							}
							// Renders the Renderable2D objects stored in the rendering list
							for (Renderable2D r : rendering) {
								iView = new ImageView(r.getTexture().getImage());
								iView.setX(r.getX());
								iView.setY(r.getY());
								root.getChildren().add(iView);
							}
						} catch (Exception e) {
							System.err.println("Exception in render update loop.");
							e.printStackTrace();
						}
					}
				}));
		// Setting its cycle count as indefinite: it will loop until the JavaFXApp's
		// stop() method has been called.
		repeatingGuiTask.setCycleCount(javafx.animation.Animation.INDEFINITE);
		repeatingGuiTask.play();
	}

	/**
	 * Used to render a Renderable2D object at a specified location. Render is
	 * actually adding the object to the {@code rendering} list. It will then be
	 * rendered in the next frame.
	 *
	 * @param renderable2D the Component to render.
	 * @param x            the x position where the Component should be rendered.
	 * @param y            the y position where the Component should be rendered.
	 *
	 * @throws NullPointerException if the {@code renderable2D} argument is null.
	 */
	public void render(@NotNull Renderable2D renderable2D, int x, int y) throws NullPointerException {
		if (renderable2D == null)
			throw new NullPointerException("Cannot render a null Renderable2D object!");
		if (!rendering.contains(renderable2D))
			rendering.add(renderable2D);
	}

	/**
	 * Used to render a Renderable2D object at its rounded location. Render is
	 * actually adding the object to the {@code rendering} list. It will then be
	 * rendered in the next frame.
	 *
	 * @param renderable2D the object to render
	 */
	public void render(@NotNull Renderable2D renderable2D) {
		render(renderable2D, renderable2D.getRoundedX(), renderable2D.getRoundedY());
	}

	/**
	 * Force stop rendering a Component.
	 *
	 * @param renderable2D
	 */
	public void stopRender(@NotNull Renderable2D renderable2D) {
		// To prevent a ConcurrentModificationException
		Renderable2D r2d = null;
		for (Renderable2D r : rendering)
			if (r.equals(renderable2D))
				r2d = r;
		rendering.remove(r2d);
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
	 *                     none}, this will have not effect. If this argument is
	 *                     null, it will be equal to
	 *                     {@link com.bloodLantern.renderer.renderables.movements.Movements.Types#IN_OUT
	 *                     in/out}.
	 */
	public void move(@NotNull Renderable2D renderable2D, int xDistance, int yDistance, long duration,
			@Nullable Movements moveType, @Nullable Movements.Types inOut) {
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
							while (lastFrameTime != System.currentTimeMillis()) {
							}
							render(renderable2D);
						}
						cancel();
					}
				}

			}, 0, 1000 / getFrameRate());
		// Other Movement types
		else
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if (System.currentTimeMillis() - startTime > fDuration) {
						cancel();
					}
					try {
						renderable2D
								.setX(((float) type.getClazz()
										.getMethod(inout.getMethodName(), float.class, float.class, float.class,
												float.class)
										.invoke(null, System.currentTimeMillis() - startTime, startingXPos, fXDistance,
												fDuration)));
						renderable2D
								.setY(((float) type.getClazz()
										.getMethod(inout.getMethodName(), float.class, float.class, float.class,
												float.class)
										.invoke(null, System.currentTimeMillis() - startTime, startingYPos, fYDistance,
												fDuration)));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
						cancel();
					}
				}

			}, 0, 1000 / getFrameRate());
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
	public void move(@NotNull Renderable2D renderable2D, int xDistance, int yDistance, long time) {
		move(renderable2D, xDistance, yDistance, time, Movements.NONE, null);
	}

	private void move(@NotNull Renderable2D renderable2D, int xDistance, int yDistance, int power) {
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
	public void setBackground(@NotNull Texture bg) {
		background = bg;
	}

	/**
	 * @Override
	 */
	@Override
	public String toString() {
		String result = "";
		if (firstRenderer.equals(this))
			result += "First ";
		result += "Renderer -> currently rendering:";
		for (Renderable2D r : rendering)
			result += "\n\t- " + r;
		if (rendering.size() == 0)
			result += " Nothing.";
		return result;
	}

	/**
	 * Renders this Animation as this Renderer's background. <strong>Not currently
	 * implemented.</strong>
	 *
	 * @param bg the Animation to set as background.
	 */
	public void setBackground(@NotNull Animation bg) {
		// TODO Renderer animation background
		background = bg;
	}

	/**
	 * @return A list of the currently rendered Renderable2D objects.
	 */
	@NotNull
	public final ArrayList<Renderable2D> getRendering() {
		return rendering;
	}

	/**
	 * @return the frame rate of this screen: {@link #frameRate}. If it couldn't
	 *         find it, returns {@link #FRAME_RATE_DEFAULT} instead.
	 */
	public static final int getFrameRate() {
		return frameRate != -1 ? frameRate : FRAME_RATE_DEFAULT;
	}

	/**
	 * Setter for the Scene of this Renderer.
	 *
	 * @param scene the scene to set
	 */
	public final void setScene(@NotNull Scene scene) {
		this.scene = scene;
	}

	/**
	 * @return the root
	 */
	@Nullable
	public final Group getRootNode() {
		return root;
	}

	/**
	 * @return the deltaTime
	 */
	public final int getDeltaTime() {
		return deltaTime;
	}

}
