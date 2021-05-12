package managers;

import org.openqa.selenium.WebDriver;

import com.pages.BorrwoingCalcPage;

public class PageObjectManager {

	private WebDriver driver;

	private BorrwoingCalcPage borrwoingCalcPage;


	

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}



	public BorrwoingCalcPage getBorrowingCalcPage(){

		return (borrwoingCalcPage == null) ? borrwoingCalcPage = new BorrwoingCalcPage(driver) : borrwoingCalcPage;

	}


}