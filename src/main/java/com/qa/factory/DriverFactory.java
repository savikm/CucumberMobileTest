package com.qa.factory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.factory.enums.DriverType;
import com.qa.factory.enums.EnvironmentType;

import io.github.bonigarcia.wdm.WebDriverManager;
import managers.FileReaderManager;

/**
 * @author savik
 *
 */
public class DriverFactory {
	
	public WebDriver driver;
    public  DesiredCapabilities caps= new DesiredCapabilities();
	public static ThreadLocal<WebDriver>tldriver=new ThreadLocal<>();
	public static ThreadLocal<RemoteWebDriver>tldriverRemote=new ThreadLocal<>();
	
	
		 private static DriverType driverType;
		 private static EnvironmentType environmentType;
		 private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
		 
			
			  public DriverFactory() throws IOException { driverType =
			  FileReaderManager.getInstance().getConfigReader().getBrowser();
			  environmentType =FileReaderManager.getInstance().getConfigReader().getEnvironment(); }
			 
		 
		 
			
			  public WebDriver getDriver() { if(driver == null) driver =
			  init_driver(); return driver; }
			 
	
	public WebDriver init_driver() {
		
		switch (environmentType) {	    
        case LOCAL : driver = createLocalDriver();
        	break;
        case REMOTE : driver = createRemoteDriver();
        	break;
	   }
	   return driver;
}

private WebDriver createRemoteDriver() {
	 switch (driverType.name())
	 {
     case "APPIUM" :
     	  caps.setCapability("deviceName", "Yoga Tablet_2_830LC");
	   	  caps.setCapability("udid", "192.168.0.172:5555");
	   	  caps.setCapability("platformName", "Android");
	   	  caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	   	  caps.setCapability(CapabilityType.VERSION, "5.0.1");
   	  
		try {
			tldriver.set(new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	 }
	return get_driver();
}

private WebDriver createLocalDriver() {

		System.out.println("Browser Value is : "+ driverType.name());
		
		  switch (driverType.name()) {     
	        case "FIREFOX" : 
	        WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
	      break;
	        case "CHROME" : 
	        	WebDriverManager.chromiumdriver().setup();
				tldriver.set(new ChromeDriver());
	     break;
	        //case "INTERNETEXPLORER" : driver = new InternetExplorerDriver();
	     //break;
	     
	   
	     
	     default:
	        	System.out.println("Please specify the correct browser name : "+ driverType.name());
		  }
		get_driver().manage().deleteAllCookies();
		get_driver().manage().window().maximize();
		
		return get_driver();
	}


	/**
	 * Method that has been used to return the Driver instance
	 */
	public static synchronized WebDriver get_driver()
	{
		return tldriver.get();
		
	}
}
