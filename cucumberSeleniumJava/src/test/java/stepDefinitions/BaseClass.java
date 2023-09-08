package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomer;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomer ac;
	public SearchCustomer sc;
	public Properties configProp;
	
	public static String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
}
