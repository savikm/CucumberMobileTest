package stepdefinitions;

import org.junit.Assert;

import com.codoid.products.exception.FilloException;
import com.pages.BorrwoingCalcPage;
import com.qa.factory.enums.Context;
import com.qa.factory.DriverFactory;
import com.qa.factory.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoanCalcSteps {
	
	TestContext testContext;
	BorrwoingCalcPage borrwoingCalcPage;
	 
	 public LoanCalcSteps(TestContext context) {
	 testContext = context;
	 borrwoingCalcPage = testContext.getPageObjectManager().getBorrowingCalcPage();
	 borrwoingCalcPage.setdataQueryParams((String)testContext.scenarioContext.getContext(Context.scenarioId),(String)testContext.scenarioContext.getContext(Context.scenarioName));
	 }
	 
  
	@Given("user navigated to URL {string}")
	public void user_navigated_to_url(String appURL) {
		testContext.driverFactory.get_driver().get(appURL);

	}

	
	@When("user filled up the application and proceed")
	public void user_filled_up_the_application_and_proceed() throws FilloException {
		borrwoingCalcPage.enterLoanParamsDetails();
	}
	
	
	@Then("Estimate You Could Borrow should be {int}")
	public void estimate_you_could_borrow_should_be(Integer borrowAmount) {
		System.out.println("Borrowing Amount is: "+borrowAmount );
		borrwoingCalcPage.verifyLoanAmount(borrowAmount);

}

}
