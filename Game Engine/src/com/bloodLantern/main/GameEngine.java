package com.bloodLantern.main;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.physics.ComputePhysics;
import com.bloodLantern.renderer.Renderer;

import javafx.application.Application;

/**
 * Class where the library user can get this engine's values.
 *
 * @author BloodLantern
 */
public final class GameEngine {

	private static boolean running = false;

	/**
	 * The Renderer created by calling {@link #setup(String[], String, int, int) the
	 * setup method}.
	 */
	private static Renderer renderer;

	/**
	 * Might be useful. Created by calling {@link #setup(String[], String, int, int)
	 * the setup method}.
	 */
	private static ComputePhysics computePhysics;

	/**
	 * Cannot be instantiated.
	 */
	private GameEngine() {
	}

	/**
	 * Setups the Game Engine with default values.
	 *
	 * @param javaFXArgs  Additional String arguments for the JavaFX Application
	 *                    implementation.
	 * @param frameTitle  The title of the frame (window).
	 * @param frameWidth  The width of the frame (window).
	 * @param frameHeight The height of the frame (window).
	 */
	public static void setup(String[] javaFXArgs, String frameTitle, int frameWidth, int frameHeight) {
		// Initializing values
		JavaFXApp.title = frameTitle;
		JavaFXApp.width = frameWidth;
		JavaFXApp.height = frameHeight;

		// Running the window in a background Thread
		new Thread() {
			@Override
			public void run() {
				setName("JavaFX Initializer");
				Application.launch(JavaFXApp.class, javaFXArgs);
			}
		}.start();

		setRunning(true);

		renderer = new Renderer();
		computePhysics = new ComputePhysics(renderer);
	}

	/**
	 * Throws a NullPointerException for each null parameter.
	 *
	 * @param objects Objects to verify the non-nullity.
	 */
	public static void verifyNotNull(@NotNull Object... objects) {
		for (Object o : objects)
			if (o == null)
				throw new NullPointerException("This method needs a non-null object!");
	}

	/**
	 * Throws a NullPointerException for each null parameter with a custom message.
	 *
	 * @param message A custom message to use for the Exception thrown.
	 * @param objects Objects to verify the non-nullity.
	 */
	public static void verifyNotNull(@NotNull String message, @NotNull Object... objects) {
		if (message == null)
			throw new NullPointerException("The custom message mustn't be null!");
		for (Object o : objects)
			if (o == null)
				throw new NullPointerException(message);
	}

	/**
	 * @return The renderer created by calling
	 *         {@link #setup(String[], String, int, int) the setup method}.
	 */
	public static final Renderer getRenderer() {
		return renderer;
	}

	/**
	 * @return The computePhysics instance created by calling
	 *         {@link #setup(String[], String, int, int) the setup method}. Might be
	 *         useful.
	 */
	public static final ComputePhysics getComputePhysics() {
		return computePhysics;
	}

	/**
	 * @return the running
	 */
	public static boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public static void setRunning(boolean running) {
		GameEngine.running = running;
	}

}
