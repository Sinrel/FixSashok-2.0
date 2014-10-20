package org.sinrel.fixsashok.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.sinrel.fixsashok.FixSashok;

public class DownloadSceneController {
	
	private double x,y;
	
	@FXML
	public void closeWindow( ActionEvent e ) {
		FixSashok.getInstance().shutdown();
	}
	
	@FXML 
	public void hideWindow( ActionEvent e ) {
		FixSashok.getInstance().stage.setIconified(true);
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
	
}
