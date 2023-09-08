Feature: Customers

 Background: Common steps in these scenerios
 Given User launch chrome browser
 When User opens url "https://admin-demo.nopcommerce.com/login"
 And User enter email id as "admin@yourstore.com" and password as "admin"
 And Click on login
 Then User can view dashboard

@sanity
Scenario: Add a new Customer
When User click on Customer menu
And Click on Customer Menu Item
And Click on Add new button
Then User can view Add new Customer page
When User enter customer info
And Click on save buttton
And User can view confirmation message "The new customer has been added successfully."
And Close browser

@regression
Scenario: Search Customer by emailId
When User click on Customer menu
And Click on Customer Menu Item
And Enter customer email
When Click on search button
Then User should found Email in the search table
And Close browser

@regression
Scenario: Search Customer by Name
When User click on Customer menu
And Click on Customer Menu Item
And Enter customer firstName and LastName
When Click on search button
Then User should found name in the search table
And Close browser


