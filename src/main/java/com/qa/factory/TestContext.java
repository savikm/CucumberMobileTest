package com.qa.factory;



import java.io.IOException;
import java.util.Properties;

import com.qa.util.ConfigReader;

import managers.PageObjectManager;

public class TestContext {
	public ScenarioContext scenarioContext;
	public DriverFactory driverFactory;
	private PageObjectManager pageObjectManager;
	
	
	
	
	public TestContext() throws IOException{
		 scenarioContext = new ScenarioContext();
		 driverFactory = new DriverFactory();
		 pageObjectManager = new PageObjectManager(driverFactory.init_driver());
	}
	
	public ScenarioContext getScenarioContext() {
		 return scenarioContext;
}
	
	public DriverFactory getDriverFactory() {
		 return driverFactory;
	}
		 
	 public PageObjectManager getPageObjectManager() {
	    return pageObjectManager;
	 }
		 

}