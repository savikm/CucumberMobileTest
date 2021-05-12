package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.factory.enums.DriverType;
import com.qa.factory.enums.EnvironmentType;

public class ConfigReader {
	
	private FileInputStream fis ;
    private Properties prop ;
    
	public ConfigReader()  {
    try {
       fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\Config.properties");
       prop = new Properties();
       prop.load(fis);
    } catch(FileNotFoundException fnfe) {
       fnfe.printStackTrace();
    } catch(IOException ioe) {
       ioe.printStackTrace();
    } finally {
       try {
		fis.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
 }
	
	
	 public String getDriverPath(){
		 String driverPath = prop.getProperty("driverPath");
		 if(driverPath!= null) return driverPath;
		 else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath"); 
		 }
		 
		 public long getImplicitlyWait() { 
		 String implicitlyWait = prop.getProperty("implicitlyWait");
		 if(implicitlyWait != null) {
		 try{
		 return Long.parseLong(implicitlyWait);
		 }catch(NumberFormatException e) {
		 throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
		 }
		 }
		 return 30; 
		 }
		 
		 public String getApplicationUrl() {
		 String url = prop.getProperty("url");
		 if(url != null) return url;
		 else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
		 }
		 
		 public DriverType getBrowser() {
		 String browserName = prop.getProperty("browser");
		 if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		 else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		 else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		 else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
		 }
		 
			
	  public EnvironmentType getEnvironment() { String environmentName =
	   prop.getProperty("environment"); if(environmentName == null ||
	  environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL; else
	  if(environmentName.equals("remote")) return EnvironmentType.REMOTE; else
	  throw new
	  RuntimeException("Environment Type Key value in Configuration.properties is not matched : "
	  + environmentName); }
	 
		 
		 public Boolean getBrowserWindowSize() {
		 String windowSize = prop.getProperty("windowMaximize");
		 if(windowSize != null) return Boolean.valueOf(windowSize);
		 return true;
		 }
		 
		 

}
