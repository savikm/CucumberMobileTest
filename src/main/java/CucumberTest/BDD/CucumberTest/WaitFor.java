package CucumberTest.BDD.CucumberTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitFor {

	  public static ExpectedCondition<Boolean> elementContainsText(String Textval)
	  {
		  return new ElementContainsText(Textval);
	  }
}
	  
	  class ElementContainsText implements ExpectedCondition<Boolean> 
	  {
		  private String textToFind;
		  
		  public ElementContainsText( final String textToFind )
		  {
			  this.textToFind=textToFind;
		  }

		@Override
		public Boolean apply(WebDriver driver) {

			return driver.findElement(By.id("abc")).getText().contains(textToFind);
		}
		  
	  }
