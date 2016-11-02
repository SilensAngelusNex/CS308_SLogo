package parser;

import java.util.ResourceBundle;

/**
 * This enum keeps tracks of all the possible languages and their resource files.
 * 
 * @author Daniel Chai
 */
public enum Language {
	ENGLISH ("English", "English"),
	CHINESE ("Chinese", "Chinese"),
	FRENCH ("French", "French"),
	GERMAN ("German", "German"),
	ITALIAN ("Italian", "Italian"),
	RUSSIAN ("Russian", "Russian"),
	SPANISH ("Spanish", "Spanish");
	
	private static final String PATH_PREFIX = "resources/languages/";
	private final String pathSuffix;
	private final String langName;
	
	private Language(String langName, String pathSuffix) {
		this.langName = langName;
		this.pathSuffix = pathSuffix;
	}
	
	public String getLangName() {
		return langName;
	}
	
	public String getPath() {
		return PATH_PREFIX + pathSuffix;
	}
	
	public ResourceBundle getResource() {
		return ResourceBundle.getBundle(PATH_PREFIX + pathSuffix);
	}
}
