package org.sinrel.fixsashok.ui.controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import org.sinrel.fixsashok.localization.Translator;

import javafx.fxml.FXMLLoader;

/* SINGLETON */
public class Controllers {
	
    private static Controllers instance;
    
    private static DownloadSceneController downloadController;
    private static OptionsSceneController optionsController;
    private static MainSceneController mainController;
    private static DialogSceneController dialogController;
    
    public static Controllers getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new Controllers();
        }
        return instance;
    }
    
	private Controllers() throws IOException {
		FXMLLoader loader;
		ResourceBundle lang = Translator.getInstance().getCurrentLang();
		
    	loader = new FXMLLoader( getClass().getResource("../views/MainSceneView.fxml"), lang );	
    	loader.load();
		mainController = loader.getController();
		//
		loader = new FXMLLoader( getClass().getResource("../views/OptionsSceneView.fxml"), lang );
		loader.load();
		optionsController = loader.getController();
		//
		loader = new FXMLLoader( getClass().getResource("../views/DownloadSceneView.fxml"), lang );
		loader.load();
		downloadController = loader.getController();
		//
		loader = new FXMLLoader( getClass().getResource("../views/DialogSceneView.fxml"), lang );
		loader.load();
		dialogController = loader.getController();
    }
    
    public MainSceneController getMainController() {
    	return mainController;
    }
    
    public OptionsSceneController getOptionsController() {
    	return optionsController;
    }
    
    public DownloadSceneController getDownloadController() {
    	return downloadController;
    }
    
    public DialogSceneController getDialogSceneController() {
    	return dialogController;
    }

}
