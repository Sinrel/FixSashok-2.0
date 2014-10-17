package org.sinrel.fixsashok.ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartupErrorController {

	@FXML
	private Button close;
	
	@FXML
	public void closeWindow( ActionEvent e ) {
		Platform.exit();
	}
	
}
