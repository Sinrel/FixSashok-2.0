package org.sinrel.fixsashok.ui.audio;

import java.net.URL;

import org.sinrel.fixsashok.Settings;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicUtils {

	public static final String START_SOUND = "start.mp3",
						 CLICK_SOUND = "click.mp3",
						 SHUTDOWN_SOUND = "shutdown.mp3";
	
	public static void playStartSound() {
		playAudioFile( START_SOUND );
	}
	
	public static void playClickSound() {
		playAudioFile( CLICK_SOUND );
	}
	
	public static void playShutdownSound() {
		playAudioFile( SHUTDOWN_SOUND );
	}
	
	private static void playAudioFile( String filename ) {
		final URL resource = MusicUtils.class.getResource( filename );
	    final Media media = new Media( resource.toString() );
	    final MediaPlayer mediaPlayer = new MediaPlayer( media );
	    mediaPlayer.volumeProperty().setValue( Settings.musicVolume );
	    mediaPlayer.play();
	}
	
}
