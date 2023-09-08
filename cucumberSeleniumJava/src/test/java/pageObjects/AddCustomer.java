package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomer {
	public WebDriver ldriver;

	public AddCustomer(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);			//4th one
	}
	
		By lnkCustomers_menu = By.xpath("/html[1]/body[1]/div[3]/aside[1]/div[1]/div[4]/div[1]/div[1]/nav[1]/ul[1]/li[4]/a[1]");
		By lnkCustomers_menuItem = By.xpath("/html[1]/body[1]/div[3]/aside[1]/div[1]/div[4]/div[1]/div[1]/nav[1]/ul[1]/li[4]/ul[1]/li[1]/a[1]");
		
		By btnAddNew = By.xpath("//a[@class='btn btn-primary']");
		
		By txtEmail = By.xpath("//input[@id='Email']");
		By txtPassword = By.xpath("//input[@id='Password']");
		By txtFirstName = By.xpath("//input[@id='FirstName']");
		By txtLastName = By.xpath("//input[@id='LastName']");
		By txtDOB = By.xpath("//input[@id='DateOfBirth']");
	
		By txtCustomerRoles = By.xpath("/html[1]/body[1]/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/nop-cards[1]/nop-card[1]/div[1]/div[2]/div[10]/div[2]/div[1]/div[1]/div[1]/div[1]");
		By lstAdministrators = By.xpath("//li[contains(text(), 'Administrators')]");
		By lstForunMod = By.xpath("//li[contains(text(), 'Forum Moderators')]");
		By lstGuests = By.xpath("//li[contains(text(), 'Guests')]");
		By lstVendors =By.xpath(")//li[contains(text(), 'Vendors')]");
		
		By drpManager = By.xpath("//select[@id='VendorId']");
		By genderMale = By.xpath("//input[@id='Gender_Male']");
		By generFemale = By.xpath("//input[@id='Gender_Female']");
		
		By txtComment = By.xpath("//textarea[@id='AdminComment']");
		
		By btnSave = By.xpath("//button[@name='save']");
		
		//Action methods
		public void clickonCustomerMenu()
		{
			ldriver.findElement(lnkCustomers_menu).click();
		}
		
		public void clickonCustomerMenuItem()
		{
			ldriver.findElement(lnkCustomers_menuItem).click();
		}
		
		public void clickonAddNew()
		{
			ldriver.findElement(btnAddNew).click();
		}
		
		public void setEmail(String email)
		{
			ldriver.findElement(txtEmail).sendKeys(email);
		}
		
		public void setPassword(String password)
		{
			ldriver.findElement(txtPassword).sendKeys(password);
		}
		
		public void setFirstName(String FirstName)
		{
			ldriver.findElement(txtFirstName).sendKeys(FirstName);
		}
		
		public void setLastName(String LastName)
		{
			ldriver.findElement(txtLastName).sendKeys(LastName);
		}
		
		public void setGender(String gender)
		{
			if(gender.equalsIgnoreCase("male"))
			{
				ldriver.findElement(genderMale).click();
			}
			if (gender.equalsIgnoreCase("female"))
			{
				ldriver.findElement(generFemale).click();
			}
			else
			{
				ldriver.findElement(generFemale).click();
			}
		}
		
		public void setCustomerRoles (String role) throws Exception
		{
			
			ldriver.findElement(txtCustomerRoles).click();
			
			/*
			 * WebElement listItem = null; Thread.sleep(2000);
			 */
			if(role.equalsIgnoreCase("Administrators"))
			{
				ldriver.findElement(lstAdministrators).click();
			}
			if(role.equalsIgnoreCase("Forum Moderators"))
			{
				 ldriver.findElement(lstForunMod).click();
			}
			if(role.equalsIgnoreCase("Guests"))
			{
				ldriver.findElement(lstGuests).click();
			}
			if(role.equalsIgnoreCase("Vendors"))
			{
				ldriver.findElement(lstVendors).click();
			}
			
			//listItem.click();
			
			/*
			 * JavascriptExecutor js = (JavascriptExecutor)ldriver;
			 * js.executeScript("arguments[0].click();", listItem);
			 */
		}
		
		public void setManagerOfVendor(String value)
		{
			Select sel = new Select(ldriver.findElement(drpManager));
			sel.selectByVisibleText(value);
		}
		
		public void setAdminContent (String content)
		{
			ldriver.findElement(txtComment).sendKeys(content);
		}
		
		public void clickOnSave() throws Exception
		{	
			
			ldriver.findElement(btnSave).click();
			Thread.sleep(3000);	
			
			/*
			 * WebElement save = ldriver.findElement(btnSave); JavascriptExecutor js =
			 * (JavascriptExecutor)ldriver; js.executeScript("arguments[0].click();", save);
			 */
		}
		
		public String getTitle()
		{
			return ldriver.getTitle();
		}
}
