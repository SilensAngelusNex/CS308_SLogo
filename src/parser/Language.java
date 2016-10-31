package parser;

import java.util.ResourceBundle;

/**
 * This enum keeps tracks of all the possible languages and their resource files.
 * 
 * @author Daniel Chai
 */
public enum Language {
	ENGLISH ("English"),
	CHINESE ("Chinese"),
	FRENCH ("French"),
	GERMAN ("German"),
	ITALIAN ("Italian"),
	RUSSIAN ("Russian"),
	SPANISH ("Spanish");
	
	public static final String PATH_PREFIX = "resources/languages/";
	private final String pathSuffix;
	
	private Language(String pathSuffix) {
		this.pathSuffix = pathSuffix;
	}
	
	public String getPath() {
		return PATH_PREFIX + pathSuffix;
	}
	
	public ResourceBundle getResource() {
		return ResourceBundle.getBundle(PATH_PREFIX + pathSuffix);
	}
}
