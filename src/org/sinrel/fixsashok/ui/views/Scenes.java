package org.sinrel.fixsashok.ui.views;

import java.io.IOException;
import java.util.ResourceBundle;

import org.sinrel.fixsashok.FixSashok;
import org.sinrel.fixsashok.localization.Translator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/* SINGLETON */
public class Scenes {
	
	private Scene mainScene, optionsScene, dialogScene, downloadScene;

	private static Scenes instance;
	
	private Scenes() throws IOException {
		ResourceBundle lang = Translator.getInstance().getCurrentLang();
		
		mainScene = new Scene( (Parent) FXMLLoader.load( getClass().getResource("../views/MainSceneView.fxml"), lang ) );
		optionsScene = new Scene( (Parent) FXMLLoader.load( getClass().getResource("../views/OptionsSceneView.fxml"), lang ) );
		dialogScene = new Scene( (Parent) FXMLLoader.load( getClass().getResource("../views/ProcessSceneView.fxml"), lang ) );
		downloadScene = new Scene( (Parent) FXMLLoader.load( getClass().getResource("../views/DownloadSceneView.fxml"), lang ) );
		
		mainScene.getStylesheets().add( FixSashok.class.getResource("ui/styles/MainSceneStyle.css").toExternalForm() );
		optionsScene.getStylesheets().add( FixSashok.class.getResource("ui/styles/OptionsSceneStyle.css").toExternalForm() );
		dialogScene.getStylesheets().add( FixSashok.class.getResource("ui/styles/ProcessSceneStyle.css").toExternalForm() );
		downloadScene.getStylesheets().add( FixSashok.class.getResource("ui/styles/DownloadSceneStyle.css").toExternalForm() );
		
		mainScene.setFill( Color.TRANSPARENT );
		optionsScene.setFill( Color.TRANSPARENT );
		dialogScene.setFill( Color.TRANSPARENT );
		downloadScene.setFill( Color.TRANSPARENT );
	}
	
	public static Scenes getInstance() {
		try {
			if( instance == null ) instance = new Scenes();
		}catch( Exception e ) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public Scene getMainScene() {
		return mainScene;
	}
	
	public Scene getOptionsScene() {
		return optionsScene;
	}
	
	public Scene getDownloadScene() {
		return downloadScene;
	}
	
	public Scene getDialogScene() {
		return dialogScene;
	}
	
}
