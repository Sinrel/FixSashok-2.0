package org.sinrel.fixsashok.ui.controllers;

import java.io.IOException;

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
    
    @SuppressWarnings("static-access")
	private Controllers() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	loader.load( getClass().getResource("../views/MainSceneView.fxml"), Translator.getInstance().getCurrentLang() );
		mainController = (MainSceneController) loader.getController();
		
		loader.load( getClass().getResource("../views/OptionsSceneView.fxml"), Translator.getInstance().getCurrentLang());
		optionsController = (OptionsSceneController) loader.getController();

		loader.load( getClass().getResource("../views/DownloadSceneView.fxml"), Translator.getInstance().getCurrentLang() );
		downloadController = (DownloadSceneController) loader.getController();
		
		loader.load( getClass().getResource("../views/DialogSceneView.fxml"), Translator.getInstance().getCurrentLang() );
		dialogController = (DialogSceneController) loader.getController();
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
