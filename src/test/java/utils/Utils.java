package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	
	/* Read a values from a property file */
	
	public static String getProperty(String propertyKey) throws IOException {
		
		Properties prop =  new Properties();
		
		ClassLoader loader = Utils.class.getClassLoader();
		FileInputStream fileInputStream = new FileInputStream(loader.getResource("global.properties").getFile());
		prop.load(fileInputStream); 
		System.out.println("URL is:"+ prop.getProperty(propertyKey));
		return prop.getProperty(propertyKey);
	}
	
	/* Converts comma separated list into String array */
	public static String[] convertStringToArray(String itemList) {
		
		return itemList.split(",");	
		
	}

}
