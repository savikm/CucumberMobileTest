package com.pages;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.codoid.products.exception.FilloException;
import com.qa.util.ExcelReader;

public class BorrwoingCalcPage {
	 private WebDriver driver;
	 private String scenario_name;
	 private String scenario_id;
	 private ExcelReader datareader=new ExcelReader();
	 private By rdpApplType=By.xpath("//label[contains(text(),'Application type')]/parent::div//label[contains(text(),'placeholder')]");
	 private By listNumOfDepend=By.xpath("//Select[@title='Number of dependants']");
	 private By rdpBorType=By.xpath("//label[contains(text(),'Property you would like to buy')]/parent::div//label[@for='placeholder']");
	 private By edtIncBeforeTax=By.xpath("//label[text()='Your income (before tax)']/parent::div//input");
	 private By edtOtherIncome=By.xpath("//label[text()='Your other income']/parent::div//input");
	 private By edtLivingExpenses=By.xpath("//label[text()='Living expenses']/parent::div//input");
	 private By edtCurHomeRepaymnt=By.xpath("//label[text()='Current home loan repayments']/parent::div//input");
	 private By edtOthLoanRepaymnt=By.xpath("//label[text()='Other loan repayments']/parent::div//input");
	 private By edtOthCommit=By.xpath("//label[text()='Other commitments']/parent::div//input");
	 private By edtTotLoanCommit=By.xpath("//label[text()='Total credit card limits']/parent::div//input");
	 private By btnRepayCalc= By.xpath("//a[text()='ANZ repayments calculator']");
	 private By sldRoleSlider=By.xpath("//div[@id='loan']//div[@role='slider']");
	 private By edtloanAmount=By.id("loanAmount");

	public BorrwoingCalcPage(WebDriver driver) {
		this.driver=driver;
	}

	public void setdataQueryParams(String Scenario_id,String Scenario_name) {
		this.scenario_name=Scenario_name;
		this.scenario_id=Scenario_id;
		
		System.out.println("scenario name is: "+Scenario_name + " scenario id is :"+ Scenario_id);
	}
	
	
	public void enterLoanParamsDetails() throws FilloException {

		List<Map <String, String>> datalist=datareader.getData("BorrowData",scenario_id,scenario_name);
		Map <String, String> datacat=datalist.get(0);
		By rdpApplTypeMod=By.xpath(rdpApplType.toString().split(":",2)[1].replace("placeholder", datacat.get("ApplicationTypeSel")));
		driver.findElement(rdpApplTypeMod).click();
		Select NumOfDependents=new Select(driver.findElement(listNumOfDepend));
		NumOfDependents.selectByVisibleText(datacat.get("NumOfDependentsSel"));
		
		By rdpBorTypeHome=By.xpath(rdpBorType.toString().split(":",2)[1].replace("placeholder", datacat.get("PropertyTypeSel")));
		//driver.findElement(rdpBorTypeHome).click();
		
		driver.findElement(edtIncBeforeTax).sendKeys(datacat.get("IncBeforeTaxset"));
		ExtentCucumberAdapter.addTestStepLog("Data entered within edtIncBeforeTax as :"+datacat.get("IncBeforeTaxset") );
		
		driver.findElement(edtOtherIncome).sendKeys(datacat.get("OtherIncSet"));
		ExtentCucumberAdapter.addTestStepLog("Data entered within edtOtherIncome as :"+datacat.get("OtherIncSet") );
		driver.findElement(edtLivingExpenses).sendKeys(datacat.get("LivExpenseSet"));
		driver.findElement(edtCurHomeRepaymnt).sendKeys(datacat.get("CurHomeLoanRepaySet"));
		
		driver.findElement(edtOthLoanRepaymnt).sendKeys(datacat.get("OthLoanComitSet"));
		driver.findElement(edtOthCommit).sendKeys(datacat.get("OthComitSet"));
		
		driver.findElement(edtTotLoanCommit).sendKeys(datacat.get("CredLimitSet"));
		driver.findElement(btnRepayCalc).click();
		
		
	}
	
	 public void verifyLoanAmount(int LoanAmount) {
		  WebElement LoanAmountVer = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(sldRoleSlider));

		  WebElement LoanAmountVerRe= driver.findElement(edtloanAmount);

		  String LoanAmountApp = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",LoanAmountVerRe);
		  Assert.assertEquals(LoanAmountApp.replaceAll(",", ""),LoanAmount);
	 }
}
