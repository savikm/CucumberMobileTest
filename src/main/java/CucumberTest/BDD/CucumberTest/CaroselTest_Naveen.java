package CucumberTest.BDD.CucumberTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CaroselTest_Naveen {

  
	     
	     @Test
	     public void extractpropDetails() throws InterruptedException
	     {

	   
	  	   // System Property for Chrome Driver   
	    	 String currentUsersHomeDir = System.getProperty("user.home");
	    	 System.out.println("Curent User Home  "+currentUsersHomeDir);
	         System.setProperty("webdriver.chrome.driver", currentUsersHomeDir+"\\eclipse-workspace\\CucumberTest\\BDD\\src\\test\\resources\\chromedriver.exe");  
             // Instantiate a ChromeDriver class.     
	         WebDriver driver=new ChromeDriver(); 
	            // Launch Website  
	         driver.navigate().to("https://www.noon.com/uae-en/");  
	         By rdpApplType=By.xpath("//label[contains(text(),'Application type')]/parent::div//label[contains(text(),'placeholder')]");
	         System.out.println("Object Val: "+rdpApplType.toString());
	         
	          //Maximize the browser  
	           driver.manage().window().maximize();   
	           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	           String aTitle = driver.getTitle();
	           System.out.println("Driver Title is "+aTitle );
		    	 JavascriptExecutor js = (JavascriptExecutor) driver;
		    	 js.executeScript("window.scrollBy(0,1000)"); //Scroll vertically down by 1000 pixels	
	    	 List <WebElement> ItemDetails= driver.findElements(By.xpath("//h3[text()='Recommended For You']/ancestor::div[2]/following::div[@class='swiper-wrapper'][1]//div[contains(@class,'swiper-slide')]"));
	    	 int i=0;
	    	 boolean flag=true;
	    	 WebElement  CarousalNext=driver.findElement(By.xpath("//h3[text()='Recommended For You']/ancestor::div[2]/following::div[contains(@class,'swiper-button-next')][1]"));
	    	 for (WebElement inditem:ItemDetails)
	    	 {

	    		 if( i==7)
	    		 {
	    			
	    		if (CarousalNext.isEnabled())
	    			
	    		try {
	    			CarousalNext.click();
                    Thread.sleep(1000);
	    		}
	    	catch(org.openqa.selenium.ElementNotInteractableException e )
	    		 {
	    			 
	    		   System.out.println("Tried to click element");
	    		   //js.executeScript("arguments[0].click();", CarousalNext); 
	    		 }
	    			if (flag) {
	    			
	    			  js.executeScript("window.scrollBy(0,-250)"); //Scroll vertically down by 1000 pixels
	    			  flag=false;
	    			}
	    			 i=0;
	    		 }
	    		 i++;
	 
	    
	    		 System.out.println("Items Description===============================>");
	    		 
	    		 if (inditem.getText().trim().equals(""))
	    			 System.out.println("blank Content");
	    		 else
	    		 System.out.println(inditem.getText());
	    	
	    		 
	    	 }
	    	 
	    	 driver.close();
	     }
	     
	     
	   

}
