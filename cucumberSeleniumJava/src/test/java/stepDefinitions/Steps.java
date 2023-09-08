package stepDefinitions;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomer;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws Exception
	{
		configProp = new Properties();
		FileInputStream fs = new FileInputStream("config.properties");
		configProp.load(fs);
		
		String browser = configProp.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
	}
	
	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
	  //driver = new ChromeDriver();
	  //driver.manage().window().maximize();
	  lp = new LoginPage(driver);
	}

	@When("User opens url {string}")
	public void user_opens_url(String url) {
	    driver.get(url);
	}

	@When("User enter email id as {string} and password as {string}")
	public void users_enter_enail_id_as_and_password_as(String email, String password) {
	    lp.setusername(email);
	    lp.setPassword(password);
	}

	@When("Click on login")
	public void click_on_login() {
	    lp.clicklogin();
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) {
	   if (driver.getPageSource().contains("Login was unsuccessful."))
	   {
		   driver.close();
		   Assert.assertTrue(false);
	   }
	   else {
		   System.out.println("Page title is " +driver.getTitle());
		   Assert.assertEquals(title, driver.getTitle());
	   }
	}

	/*
	 * @Then("verify the user name is {String}") public void
	 * verify_the_user_name_is(String userName) 
	 * { 
	 * if (driver.getPageSource().contains("Login was unsuccessful."))
	 *  {
	 * driver.close(); 
	 * Assert.assertTrue(false);
	 *  } 
	 * else 
	 * { String name = lp.userverify(); 
	 * Assert.assertEquals(userName, name); } }
	 */

	@When("User clik on logout link")
	public void user_clik_on_logout_link() throws InterruptedException {
	    lp.clicklogOut();
	    Thread.sleep(3000);
	}
	
	//customers features step definition
	@Then("User can view dashboard")
	public void user_can_view_dashboard() {
	    ac = new AddCustomer(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", ac.getTitle());
	}
	
	@When("User click on Customer menu")
	public void user_click_on_customer_menu() throws Exception {
		Thread.sleep(2000);
	    ac.clickonCustomerMenu();
	}
	
	@When("Click on Customer Menu Item")
	public void click_on_customer_menu_item() {
	   ac.clickonCustomerMenuItem();
	}
	
	@When("Click on Add new button")
	public void click_on_add_new_button() {
	    ac.clickonAddNew();
	}
	
	@Then("User can view Add new Customer page")
	public void user_can_view_add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", ac.getTitle());
	}
	
	@When("User enter customer info")
	public void user_enter_customer_info() throws Exception {
	    String email = randomString()+"@gmail.com";
	    ac.setEmail(email);
	    ac.setPassword("Adjoint256");
	    ac.setFirstName("abc");	
	    ac.setLastName("abc");
	    ac.setCustomerRoles("Administrators");
	    Thread.sleep(2000);
	    ac.setGender("male");
	    ac.setManagerOfVendor("Vendor 2");
	    ac.setAdminContent("Adding Customer");
	}
	
	@When("Click on save buttton")
	public void click_on_save_buttton() throws Exception {
	    ac.clickOnSave();
	    
	}
	
	@When("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));
	}
	
	//Steps for searching customers 
	@When("Enter customer email")
	public void enter_customer_email() {
	    sc = new SearchCustomer(driver);
	    sc.setEmail("brenda_lindgren@nopCommerce.com");
	}
	
	@When("Click on search button")
	public void click_on_search_button() throws Exception {
	   sc.clickOnSearch();
	   Thread.sleep(3000);
	}
	
	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
	   boolean status = sc.searchCustomerByEmail("brenda_lindgren@nopCommerce.com");
	   Assert.assertTrue(status);
	}
	

	@When("Enter customer firstName and LastName")
	public void enter_customer_first_name_and_last_name() {
		sc= new SearchCustomer(driver);
	    sc.setFirstName("Arthur");
	    sc.setLastName("Holmes");
	}
	
	@Then("User should found name in the search table")
	public void user_should_found_name_in_the_search_table() {
	    boolean status = sc.searchCustomerByName("Arthur", "Holmes");
	    Assert.assertEquals(true, status);
	}


	@Then("Close browser")
	public void close_browser() {
	    driver.quit();
	}

}
