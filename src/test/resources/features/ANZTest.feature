#Author: savik67@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


Feature: ANZ Loan Repayment Calculator

@Smoke
  Scenario: Borrowing Estimate
    Given user navigated to URL "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    #When Application Type is "Single"
    #And Number Of dependents are 0
    #And Property Type is "Home To live In"
    #And Income Amount is 80000
    #And Other Income is 10000
    #And Living Expense is 500
    #And Current Home Loan repayment is 100
    #And Total credit card repayment is 10000
    When user filled up the application and proceed
    Then Estimate You Could Borrow should be 502000
@Smoke  @skip_scenario  
   Scenario: Carousal Navigation
    Given user navigated to URL "https://www.noon.com/uae-en/"
    When "Recommended For You"  section is loaded properly
    Then user should be able to Retrieve the items
    
