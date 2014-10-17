package org.sinrel.fixsashok.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {

	private ResourceBundle ru = ResourceBundle.getBundle( "org.sinrel.fixsashok.localization.lang" , Locale.forLanguageTag("ru"));
	private ResourceBundle en = ResourceBundle.getBundle( "org.sinrel.fixsashok.localization.lang" , Locale.forLanguageTag("en"));
	
	private ResourceBundle current;
	
	public Translator() {
		current = ru;
	}
	
	public ResourceBundle getCurrentLang() {
		return current;
	}
	
	public void setCurrentLang( Locale locale ) {
		if ( locale.getLanguage().equals( new Locale("ru").getLanguage() ) ) {
			current = ru;
		}else if ( locale.getLanguage().equals( new Locale("en").getLanguage() ) ) {
				current = en;
			}else current = ru;
	}
	
}
