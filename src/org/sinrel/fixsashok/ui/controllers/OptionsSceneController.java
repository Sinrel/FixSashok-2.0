package org.sinrel.fixsashok.ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import net.launcher.utils.BaseUtils;

import org.sinrel.fixsashok.FixSashok;
import org.sinrel.fixsashok.ui.views.Scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OptionsSceneController implements Initializable {

	@FXML
	private CheckBox cleanDir, playMusic, loadNews, fullscreen;
	
	@FXML
	private TextField memoryField;
	
	@FXML
	private Button closeOptions;
	
	private double x,y;
	
	@FXML
	public void closeWindow( ActionEvent e ) {
		FixSashok.getInstance().shutdown();
	}
	
	@FXML
	public void onMousePressedEvent( MouseEvent event ) {
		if( event.getButton() != MouseButton.PRIMARY ) { event.consume(); return; }
			Stage stage = FixSashok.getInstance().stage;
			x = stage.getX() - event.getScreenX();
			y = stage.getY() - event.getScreenY();
	}
	
	@FXML
	public void onMouseDraggedEvent( MouseEvent event ) {
		if( event.getButton() != MouseButton.PRIMARY ) { event.consume(); return; }
			Stage stage = FixSashok.getInstance().stage;
			stage.setX(event.getScreenX() + x);
			stage.setY(event.getScreenY() + y);
	}
	
	@FXML 
	public void hideWindow( ActionEvent e ) {
		FixSashok.getInstance().stage.setIconified(true);
	}
	
	@FXML
	public void closeOptions( ActionEvent e ) throws IOException {
		if(!BaseUtils.isNumeric( memoryField.getText() ) )
		{
			try
			{
				int i = Integer.parseInt( memoryField.getText());
				BaseUtils.setProperty("memory", i);
				
				BaseUtils.setProperty("fullscreen", fullscreen.isSelected() );
				BaseUtils.setProperty("loadNews", loadNews.isSelected() );
				BaseUtils.setProperty("playMusic", playMusic.isSelected() );
			} catch(Exception e1){}
		}
		
		FixSashok instance = FixSashok.getInstance();
		instance.stage.setScene( Scenes.getInstance().getMainScene() );
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadOptions();
	}
	
	public void loadOptions() {
		String memory = BaseUtils.getPropertyString("memory");
		memoryField.setText( memory == null ? "1024": memory );
		
		fullscreen.setSelected( BaseUtils.getPropertyBoolean( "fullscreen" ) );
		loadNews.setSelected( BaseUtils.getPropertyBoolean( "loadNews" ) );
		fullscreen.setSelected( BaseUtils.getPropertyBoolean( "playMusic" ) );
	}
}
