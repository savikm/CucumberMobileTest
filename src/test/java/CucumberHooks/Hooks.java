package CucumberHooks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import  com.qa.factory.enums.Context;
import com.qa.factory.DriverFactory;
import com.qa.factory.TestContext;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	private ConfigReader confReader;	
	
	 public static HashMap<String ,String> HooksInfo;
	 TestContext testContext;
	 
		/*
		 * public Hooks(){ //or even inside of your singleton's getInstance();
		 * if(scenarios == null) scenarios = new HashMap <Integer,String>(); }
		 */
	
	 public Hooks(TestContext context) {
		 testContext = context;
		 }
	 
	 @Before(value="@skip_scenario",order=0)
	 public void SkipTests(Scenario sc)
	 {	
			System.out.println("=======Skipping following scenarios======");
			System.out.println(sc.getName()+"  "+sc.getId());
		 Assume.assumeTrue(false);
	 }

	@Before(order=1)
	public void LaunchTests(Scenario sc) 
	{
		System.out.println("=================Executing following scenario============");
		System.out.println(sc.getName()+"  "+sc.getId());
		confReader= new ConfigReader();
		String[] scpart = sc.getId().split("/");
		String scpartName = scpart[scpart.length-1];
		
		
		 testContext.scenarioContext.setContext(Context.scenarioId, scpartName);
		 testContext.scenarioContext.setContext(Context.scenarioName, sc.getName());
		 
			System.out.println("Scenario Id  "+Context.scenarioId);
			System.out.println("Scenario name  "+Context.scenarioName);
	}
	
	
	@After
	public void CloseBrowsers(Scenario sc)
	{
		System.out.println("Closing the launched browsers");
		System.out.println("Scenario Status is:"+sc.getStatus());
		if (sc.isFailed())
		{
			String screenshotname=sc.getName().replaceAll(" ", "_");
			byte[] sourcepath=((TakesScreenshot)testContext.getDriverFactory().get_driver()).getScreenshotAs(OutputType.BYTES);
			sc.attach(sourcepath, "image/png", screenshotname);
		}
		testContext.getDriverFactory().get_driver().quit();
	
		
	}

}
