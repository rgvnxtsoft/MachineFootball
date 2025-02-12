package com.testautomation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties readProperties;
	String path ="C:\\Users\\Admin\\eclipse-workspace\\TestAutomationV1\\Configuration\\config.properties";
	
	public ReadConfig()
	{
		readProperties = new Properties();

	try{
		FileInputStream fis= new FileInputStream(path);
		readProperties.load(fis);
		}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public String getBaseURL()
	{
		String value = readProperties.getProperty("baseUrl");
		
		if(value!=null)
			return value;
		else
		throw new RuntimeException("URL not found");
	}
	
	public String getBrowser()
	{
		String value = readProperties.getProperty("browser");
	
		if(value!=null)
			return value;
		else
		throw new RuntimeException("Browser Value not found");
	}
	
	
}
