package org.sinrel.fixsashok;

import org.sinrel.fixsashok.ui.audio.MusicUtils;
import org.sinrel.fixsashok.ui.views.Scenes;

import net.launcher.utils.BaseUtils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

import static net.launcher.utils.BaseUtils.moveFaviconToAssetsPath;

public class FixSashok extends Application {
	
	public Scene startupErrorScene;
	
	public Stage stage;

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
				Scenes.getInstance();

				stage.setTitle( Settings.title );
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.centerOnScreen();
				
				stage.setResizable(false);
				stage.getIcons().add( new Image(getClass().getResourceAsStream("ui/images/favicon.png")) );
				stage.setScene( Scenes.getInstance().getMainScene() );
		
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
	
	public void shutdown() {
		if( Settings.playMusic ) MusicUtils.playShutdownSound();
		Platform.exit();
		System.exit(0);
	}
	
}


