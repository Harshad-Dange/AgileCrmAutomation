package com.agilecrmautomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
	
	private Properties properties;
	
	public PropertyHandling() {
		//config file path
		String cofigFilePath=System.getProperty("user.dir")+"//config.properties";
		try {
			FileInputStream input=new FileInputStream(cofigFilePath);
			
			properties=new Properties();
			
			properties.load(input);   //load config file into Properties class to access properties
		
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}	

	/**
	 * This method will return the value against the provided key in string format
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}













