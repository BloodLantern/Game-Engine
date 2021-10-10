package com.bloodLantern.main;

import com.bloodLantern.annotations.NotNull;
import com.bloodLantern.renderer.Renderer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class inheriting from the {@link Application} class. It is used to set up
 * everything for the JavaFX part of this Game Engine.
 *
 * @author BloodLantern
 */
public class JavaFXApp extends Application {

	static double width = 800;
	static double height = 600;
	@NotNull
	static String title = "Game Engine";

	/**
	 * Method called when the {@code launch(String)} method is called. It is the
	 * starting point of a JavaFX program.
	 *
	 * @Override
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Waiting for the first Renderer to be instantiated
		while (Renderer.firstRenderer == null) {
		}
		// Creating a new Scene with the Renderer's root Node
		Scene scene = new Scene(Renderer.firstRenderer.getRootNode(), width, height);
		// Setting the new Scene to the Renderer
		Renderer.firstRenderer.setScene(scene);
		// Awaking the main Thread
		synchronized (Renderer.firstRenderer) {
			Renderer.firstRenderer.notify();
		}

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			/**
			 * Called when closing the stage (window).
			 *
			 * @Override
			 */
			@Override
			public void handle(WindowEvent event) {
				if (event.isConsumed())
					return;
				GameEngine.setRunning(false);
				System.exit(0);
			}
		});
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

}
