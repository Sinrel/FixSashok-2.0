package org.sinrel.fixsashok.ui.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import net.launcher.utils.BaseUtils;

import org.sinrel.fixsashok.FixSashok;
import org.sinrel.fixsashok.Settings;
import org.sinrel.fixsashok.localization.Translator;
import org.sinrel.fixsashok.ui.views.Scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainSceneController implements Initializable {
	
	@FXML
	private Button toGame, toOptions, hideButton, closeButton;
	
	@FXML
	private Pane mainSceneRoot, dragger;
	
	@FXML
	private Label windowTitle, serverStatus, loadingDesc;
	
	@FXML
	private ChoiceBox<String> servers;
	
	@FXML
	private Hyperlink firstLink, secondLink, thirdLink;
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private WebView newsBrowser;
	
	@FXML
	private ImageView loadingIcon;
	
	private double x,y;
	private Pane scene;
	
	@FXML
	public void closeWindow( ActionEvent e ) {
		FixSashok.getInstance().shutdown();
	}
	
	@FXML 
	public void hideWindow( ActionEvent e ) {
		FixSashok.getInstance().stage.setIconified(true);
	}
	
	@FXML
	public void toGame( ActionEvent e ) {
		setLoading(	Translator.getInstance().getCurrentLang().getString("loading.auth"), 3000 );
		//TODO
	}
	
	@FXML
	public void toOptions( ActionEvent e ) throws IOException {
		Controllers.getInstance().getOptionsController().loadOptions();
		FixSashok.getInstance().stage.setScene( Scenes.getInstance().getOptionsScene() );
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
	
	private void openLink( String address ) {
		try {
			Desktop.getDesktop().browse(new URI(address));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onFirstLinkAction( ActionEvent e ) {
		openLink( Settings.firstLink );
	}
	
	@FXML
	public void onSecondLinkAction( ActionEvent e ) {
		openLink( Settings.secondLink );
	}

	@FXML
	public void onThirdLinkAction( ActionEvent e ) {
		openLink( Settings.thirdLink );
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scene = (Pane) newsBrowser.getParent();
		ObservableList<Node> children = scene.getChildren();
		
		loadingDesc.setVisible(false);
		loadingIcon.setVisible(false);
		
		if( Settings.newsLink == null || Settings.newsLink.equals( "" ) ) {
			children.remove(newsBrowser);
		}else{
			Platform.runLater( new Runnable() {
				public void run() {
					newsBrowser.getEngine().load(Settings.newsLink);
				}
			});
		}
		
		if( isEmptyLink( Settings.firstLink ) ) children.remove( firstLink );
		if( isEmptyLink( Settings.secondLink ) ) children.remove( secondLink );
		if( isEmptyLink( Settings.thirdLink ) ) children.remove( thirdLink );
		
		servers.getItems().addAll( BaseUtils.getServerNames() );
		servers.getSelectionModel().select(0);
	}
	
	private boolean isEmptyLink( String link ) {
		if( link == null || link.equals("") ) return true;
		return false;
	}
	
	public void setLoading( String reason, int delay ) {
		mainSceneRoot.setDisable(true);
		
		loadingDesc.setVisible(true);
		loadingIcon.setVisible(true);
		
		loadingDesc.setText( reason );
		double width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth( loadingDesc.getText(), loadingDesc.getFont());
		loadingDesc.setLayoutX( scene.getWidth() / 2 - ( width / 2 ) );
		
		new Timeline(new KeyFrame(
		        Duration.millis(delay),
		        ae -> cancelLoading()))
		    .play();
	}
	
	public void cancelLoading() {
		loadingDesc.setVisible(false);
		loadingIcon.setVisible(false);
		
		mainSceneRoot.setDisable(false);
	}
		
}
