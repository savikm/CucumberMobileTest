package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue= {"stepdefinitions","CucumberHooks"},
		tags="@Smoke",
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:target/Reports/TestResult.json",
				"junit:target/Reports/TestResult.xml",
				"rerun:target/failedrerun.txt"},
		
		
		dryRun=false,
		publish=true
)

public class ANZTest {

}
