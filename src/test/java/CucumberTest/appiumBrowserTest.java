package CucumberTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

public class appiumBrowserTest {
	
  WebDriver driver;
  DesiredCapabilities caps= new DesiredCapabilities();
  

  
  @BeforeTest
  public void initConfig()
  {
	 
	  caps.setCapability("deviceName", "Yoga Tablet_2_830LC");
	  caps.setCapability("udid", "192.168.0.172:5555");
	  caps.setCapability("platformName", "Android");
		// caps.setCapability("platformVersion", "5.0.1");

	  caps.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
	  caps.setCapability(CapabilityType.VERSION, "5.0.1");
	 
  }
	
  

  @Test
  public void ANZWebAppTest() throws MalformedURLException, InterruptedException {
	  
	  //Automatically start appium server
		/*
		 * String Appium_Node_Path="C:\\Program Files\\nodejs\\node.exe"; String
		 * Appium_JS_Path=
		 * "C:\\Users\\savik\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\appium.js";
		 * AppiumDriverLocalService appiumService;
		 * 
		 * appiumService = AppiumDriverLocalService.buildService(new
		 * AppiumServiceBuilder().usingPort(4723).usingDriverExecutable(new
		 * File(Appium_Node_Path)). withAppiumJS(new File(Appium_JS_Path)));
		 * appiumService.start();
		 */
      
      
	  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
	  driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
	  
	  WebElement ApplicationType= driver.findElement(By.xpath("//label[contains(text(),'Application type')]/parent::div//label[contains(text(),'Single')]"));
	  ApplicationType.click();
	  
		/*
		 * AndroidDriver driverapp= (AndroidDriver) new AppiumDriver(new
		 * URL("http://127.0.0.1:4723/wd/hub"),caps); driverapp.pressKey(new
		 * KeyEvent(AndroidKey.BACK));
		 */

	  
		/*
		 * WebDriverWait w1=new WebDriverWait(driver,15);
		 * 
		 * w1.wait(new ExpectedConditions<Boolean>(){public Boolean apply(AppiumDriver
		 * driver) { });
		 */
	
	  
	  // Another Expected Condition implementation with Class
	  
		/*
		 * WebDriverWait w1=new WebDriverWait(driver,15);
		 * w1.until(WaitFor.elementContainsText("abc"));
		 */
	  
	  
	  Select NumOfDependents=new Select(driver.findElement(By.xpath("//Select[@title='Number of dependants']")));
	  NumOfDependents.selectByVisibleText("1");
	
	  WebElement PropertyType= driver.findElement(By.xpath("//label[contains(text(),'Property you would like to buy')]/parent::div//label[@for='borrow_type_home']"));
	  PropertyType.click();
	  
	  WebElement IncBeforeTax= driver.findElement(By.xpath("//label[text()='Your income (before tax)']/parent::div//input"));
	  IncBeforeTax.sendKeys("80000");
	  
	  WebElement OtherInc= driver.findElement(By.xpath("//label[text()='Your other income']/parent::div//input"));
	  OtherInc.sendKeys("500");
	  
	  WebElement LivExpense= driver.findElement(By.xpath("//label[text()='Living expenses']/parent::div//input"));
	  LivExpense.sendKeys("10000");
	  
	  WebElement CurHomeLoanRepay= driver.findElement(By.xpath("//label[text()='Current home loan repayments']/parent::div//input"));
	  CurHomeLoanRepay.sendKeys("100");
	  
	  WebElement OthLoanComit= driver.findElement(By.xpath("//label[text()='Other loan repayments']/parent::div//input"));
	  OthLoanComit.sendKeys("10000");
	  
	  WebElement OthComit= driver.findElement(By.xpath("//label[text()='Other commitments']/parent::div//input"));
	  OthComit.sendKeys("40");
	  
	  WebElement CredLimit= driver.findElement(By.xpath("//label[text()='Total credit card limits']/parent::div//input"));
	  CredLimit.sendKeys("50");
	  
	  WebElement AnzRepaycal= driver.findElement(By.xpath("//a[text()='ANZ repayments calculator']"));
	  AnzRepaycal.click();
	  
	  
	  WebElement LoanAmountVer = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loan']//div[@role='slider']")));
	  
	  Thread.sleep(5000);
	  
	  WebElement LoanAmountVerRe= driver.findElement(By.xpath("//div[@id='loan']//div[@role='slider']"));
	  System.out.println("Loan amount calculated as: "+LoanAmountVerRe.getAttribute("aria-valuemin"));
	  System.out.println("Loan amount calculated as new: "+LoanAmountVerRe.getAttribute("aria-valuenow"));
	  
	  driver.quit();
	  //Assert.assertEquals("50001", LoanAmountVer.getAttribute("aria-valuenow"));
		/*
		 * JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; String text =
		 * (String) jsExecutor.executeScript("return arguments[0].value",
		 * LoanAmountVerRe); System.out.println("value is "+text);
		 */
	  

	  
  }
  

}
