package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"@target/failedrerun.txt"},
		glue= {"stepdefinitions","CucumberHooks"},
		tags="@Smoke",
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failedrerun.txt"},
		
		
		dryRun=false,
		publish=true
		
		)
public class FailedRerun {


}
