package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomer {
	public WebDriver ldriver;
	public WaitHelper waithelp;
	public SearchCustomer(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waithelp = new WaitHelper(ldriver);
	}
	
	@FindBy (id = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy (id = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy (id = "SearchLastName")
	@CacheLookup
	WebElement txtLastname;
	
	
	//By txtmailEmail = By.id("SearchEmail");
	//By txtFirstName = By.id("SearchFirstName");
	//By txtLastname = By.id("SearchLastName");
	
	By btnSearch = By.id("search-customers");
	
	@FindBy (how = How.XPATH, using = "/html[1]/body[1]/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]")
	WebElement table;
	
	@FindBy (xpath = "/html[1]/body[1]/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	public void setEmail (String email)
	{
		waithelp.waitForElement(txtEmail, 10);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName (String firstName)
	{
		waithelp.waitForElement(txtFirstName, 10);
		txtFirstName.clear();
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName (String lastName)
	{
		waithelp.waitForElement(txtLastname, 10);
		txtLastname.clear();
		txtLastname.sendKeys(lastName);
	}
	
	public void clickOnSearch()
	{
		ldriver.findElement(btnSearch).click();
	}
	
	public int getNoOfRows()
	{
		return (tableRows.size());
	}
	
	public boolean searchCustomerByEmail(String Email)
	{
		boolean flag = false;
		for (int i=1; i<=getNoOfRows(); i++)
		{
			String emailId = ldriver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr["+i+"]/td[2]")).getText();
			if(emailId.equals(Email))
			{
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String FirstName, String LastName)
	{
		boolean flag = false;
		for (int i=1; i<=getNoOfRows(); i++)
		{
			String name = ldriver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr["+i+"]/td[3]")).getText();
			String Names[]= name.split(" ");
			if(Names[0].equals(FirstName) && Names[1].equals(LastName))
			{
				flag = true;
			}
		}
		return flag;
	}
}
