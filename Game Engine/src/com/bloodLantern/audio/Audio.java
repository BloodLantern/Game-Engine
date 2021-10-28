package com.bloodLantern.audio;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * An Audio object is used to manage audio files.
 * <p>
 * It is made of a {@link javafx.scene.media.Media Media} as well as a
 * {@link javafx.scene.media.MediaPlayer MediaPlayer} object. It means for
 * example that to change the audio volume you have to do:
 * {@code getMediaPlayer().setVolume(double)} and as well to get the volume:
 * {@code getMediaPlayer().getVolume()} but to get bac the audio file, you have
 * to use the Media object instead: {@code getMedia().getSource()} which will
 * return a URI corresponding to the audio file path. Moreover, if you want to
 * play, pause or stop the audio, you have to use the MediaPlayer object.
 * <p>
 * You can also add custom Runnable action to execute when a certain method is
 * used with the MediaPlayer object. To do so, you can use its method:
 * {@code setOnPause(Runnable)} for example and after: {@code getOnPause()}.
 * <p>
 * Remember that you have to keep your Audio instance somewhere or to modify it
 * from an EventListener annotated method to assume that the garbage collector
 * won't finalize its MediaPlayer.
 * 
 * @author BloodLantern
 */
public class Audio {

	public static final double DEFAULT_VOLUME = 0.5;

	/**
	 * The Media object linked to this Audio file.
	 * <p>
	 * The difference with {@link #mediaPlayer} is that this object is used to
	 * control everything about the audio file.
	 */
	private final Media media;

	/**
	 * The MediaPlayer used to read the Media file.
	 * <p>
	 * The difference with {@link #media} is that this object is used to control
	 * everything about the audio parameters and values.
	 */
	private final MediaPlayer mediaPlayer;

	/**
	 * Constructs a new Audio object with an audio file path as argument.
	 */
	public Audio(String path) {
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(DEFAULT_VOLUME);
	}

	/**
	 * Getter for the media value.
	 * <p>
	 * The difference with {@link #getMediaPlayer()} is that this object is used to
	 * control everything about the audio file.
	 * 
	 * @return The media to get.
	 */
	public final Media getMedia() {
		return media;
	}

	/**
	 * Getter for the mediaPlayer value.
	 * <p>
	 * The difference with {@link #getMedia()} is that this object is used to
	 * control everything about the audio parameters and values.
	 * 
	 * @return The mediaPlayer to get.
	 */
	public final MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

}
