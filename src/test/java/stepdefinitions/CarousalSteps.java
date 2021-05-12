package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarousalSteps {
	

@When("{string}  section is loaded properly")
public void section_is_loaded_properly(String string) {
  System.out.println("String loaded properly as: "+string);
}

@Then("user should be able to Retrieve the items")
public void user_should_be_able_to_retrieve_the_items() {
	System.out.println("Retrieved Items");
}

}
