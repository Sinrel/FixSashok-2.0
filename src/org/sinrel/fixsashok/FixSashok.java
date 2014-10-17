package org.sinrel.fixsashok;

import java.io.IOException;

import org.sinrel.fixsashok.localization.Translator;
import org.sinrel.fixsashok.ui.audio.MusicUtils;

import net.launcher.utils.BaseUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import static net.launcher.utils.BaseUtils.moveFaviconToAssetsPath;

public class FixSashok extends Application {
	
	public Scene MAIN_SCENE, OPTIONS_SCENE, PROCESS_SCENE, DOWNLOAD_SCENE;
	public Scene startupErrorScene;
	
	public Stage stage;
	public Translator translator;
	
	private static FixSashok instance;
	
	public static FixSashok getInstance() {
		return instance; 
	}
	
	//This method doesn't using in normal JavaFX application. Don't write down code here 
	public static void main( String[] args ) {
		launch( args );
	}

	@Override
	public void start( Stage stage ) {
		this.stage = stage;
		instance = this;
		translator = new Translator();
		
		try{ 
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override public void handle(WindowEvent t) {
			        shutdown();
			    }
			});
			
			if( BaseUtils.isDublicated() ) {
	    		Parent root = FXMLLoader.load( FixSashok.class.getResource("ui/views/StartupErrorView.fxml") );
	    		startupErrorScene =  new Scene( root, 500, 100 );
	    		startupErrorScene.getStylesheets().add( FixSashok.class.getResource("ui/styles/StartupErrorStyle.css").toExternalForm() );
	    		
	    		stage.setScene( startupErrorScene );
	        	stage.setTitle("Лаунчер уже запущен");
	        	stage.setResizable(false);
	        	stage.centerOnScreen();
	        	stage.getIcons().add( new Image(getClass().getResourceAsStream("ui/images/startupErrorIcon.png")) );
	        	
	        	stage.show();
			}else{
				moveFaviconToAssetsPath();
				loadViews();

				stage.setTitle( Settings.title );
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.centerOnScreen();
				
				stage.setResizable(false);
				stage.getIcons().add( new Image(getClass().getResourceAsStream("ui/images/favicon.png")) );
				stage.setScene( MAIN_SCENE );
		
				stage.show();
				
				if(BaseUtils.getPropertyBoolean("playMusic", true))
				{
					if( Settings.playMusic ) MusicUtils.playStartSound();
				}			
			}
		}catch( Exception e ) {
			e.printStackTrace();
			//Add SLE error window
			shutdown();
		}
	}
		
	private void loadViews() throws IOException {
		//Loading views
		MAIN_SCENE = new Scene( (Parent) FXMLLoader.load( getClass().getResource("ui/views/MainSceneView.fxml"), translator.getCurrentLang() ) );
		OPTIONS_SCENE = new Scene( (Parent) FXMLLoader.load( getClass().getResource("ui/views/OptionsSceneView.fxml"), translator.getCurrentLang() ) );
		PROCESS_SCENE = new Scene( (Parent) FXMLLoader.load( getClass().getResource("ui/views/ProcessSceneView.fxml"), translator.getCurrentLang() ) );
		DOWNLOAD_SCENE = new Scene( (Parent) FXMLLoader.load( getClass().getResource("ui/views/DownloadSceneView.fxml"), translator.getCurrentLang() ) );
		//Loading stylesheets
		MAIN_SCENE.getStylesheets().add( FixSashok.class.getResource("ui/styles/MainSceneStyle.css").toExternalForm() );
		OPTIONS_SCENE.getStylesheets().add( FixSashok.class.getResource("ui/styles/OptionsSceneStyle.css").toExternalForm() );
		PROCESS_SCENE.getStylesheets().add( FixSashok.class.getResource("ui/styles/ProcessSceneStyle.css").toExternalForm() );
		DOWNLOAD_SCENE.getStylesheets().add( FixSashok.class.getResource("ui/styles/DownloadSceneStyle.css").toExternalForm() );
		//
		MAIN_SCENE.setFill( Color.TRANSPARENT );
		OPTIONS_SCENE.setFill( Color.TRANSPARENT );
	}
	
	public void shutdown() {
		if( Settings.playMusic ) MusicUtils.playShutdownSound();
		Platform.exit();
		System.exit(0);
	}
	
}


