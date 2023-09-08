Feature: Login


@sanity
Scenario: Successful Login with valid credentials
Given User launch chrome browser
When User opens url "https://admin-demo.nopcommerce.com/login"
And User enter email id as "admin@yourstore.com" and password as "admin"
And Click on login
Then page title should be "Dashboard / nopCommerce administration"
When User clik on logout link 
And Close browser

@sanity
Scenario Outline: Login Data Driven
Given User launch chrome browser
When User opens url "https://admin-demo.nopcommerce.com/login"
And User enter email id as "<email>" and password as "<password>"
And Click on login
Then page title should be "Dashboard / nopCommerce administration"
When User clik on logout link 
And Close browser

	Examples:
		|email	|	password|
		|admin@yourstore.com	|	admin|
		|admin1@yourstore.com	|	admin123|