package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);			//4th one
	}
	
	@FindBy (id = "Email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (id = "Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy (xpath = "//button[contains(text(),'Log in')]")
	@CacheLookup
	WebElement btLogin;
	
	@FindBy (linkText =  "Logout")
	@CacheLookup
	WebElement lnkLogout;
	
	/*
	 * @FindBy (xpath = "//a[contains(text(), 'John Smith')]")
	 * 
	 * @CacheLookup WebElement verifyName;
	 */
	
	public void setusername(String uName)	{
		txtEmail.clear();
		txtEmail.sendKeys(uName);
	}
	
	public void setPassword(String pwd)	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clicklogin() {
		btLogin.click();
	}
	public void clicklogOut() {
		lnkLogout.click();
	}
	
	/*
	 * public String userverify() { return verifyName.getText(); }
	 */
	
}
