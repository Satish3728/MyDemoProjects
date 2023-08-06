package project3;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Set;

public class HotelPlanisphere {
WebDriver driver;
SoftAssert sf = new SoftAssert();

@Parameters("browserName")
@BeforeTest
public void initialiseBrowser(String browserName) {
	switch (browserName.toLowerCase()) {
	case "chrome":
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		break;
	case "firefox":
		 driver = new FirefoxDriver();
		break;
	case "edge":
		driver = new EdgeDriver();
		break;
	default:
		System.out.println("Invalid browser name");
		break;
	}
}

@AfterTest
public void close() {
driver.close();
sf.assertAll();
}

@Parameters("url")
@Test
public void launchApp(String url) {
	driver.get(url);
	driver.findElement(By.linkText("Go to Top Page")).click();
}

@Parameters ({"email", "password"})
@Test
public void signUp(String e, String pass) {
	driver.findElement(By.linkText("Sign up")).click();
	driver.findElement(By.id("email")).sendKeys(e);
	driver.findElement(By.id("password")).sendKeys(pass);
	driver.findElement(By.id("password-confirmation")).sendKeys(pass);
	driver.findElement(By.id("username")).sendKeys("abcd");
	boolean premium = driver.findElement(By.id("rank-premium")).isSelected();
	sf.assertTrue(premium, "premium membership is selected");
	if (premium == true) {
		driver.findElement(By.id("rank-premium")).click();
	}
	driver.findElement(By.id("address")).sendKeys("abcd");
	driver.findElement(By.id("tel")).sendKeys("09876543211");
	WebElement gender = driver.findElement(By.id("gender"));
	Select sel = new Select(gender);
	sel.selectByIndex(0);
	boolean notification = driver.findElement(By.id("notification")).isSelected();
	sf.assertFalse(notification, "notification is not default selected");
	if(notification ==false)
	{
		driver.findElement(By.id("notification")).click();
	}
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String actulcon = driver.findElement(By.xpath("//h2[@class='my-3']")).getText();
	String expectedcon = "MyPage";
	sf.assertEquals(actulcon, expectedcon, "Signup successfull");
}

@Parameters({"email", "password"})
@Test
public void login(String e, String pass) {
	driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
	driver.findElement(By.id("email")).sendKeys(e);
	driver.findElement(By.id("password")).sendKeys(pass);
	driver.findElement(By.id("login-button")).click();
	String actulcon = driver.findElement(By.xpath("//h2[@class='my-3']")).getText();
	String expectedcon = "MyPage";
	sf.assertEquals(actulcon, expectedcon, "Signup not successfull");
}

@Test
public void logOut() {
	driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();
}


@Test
public void reserveRoom() {
	driver.findElement(By.xpath("//a[contains(text(),'Reserve')]")).click();
	String parent = driver.getWindowHandle();
	System.out.println(parent);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	driver.findElement(By.xpath("//div[@id='plan-list']//child::div[4]//descendant::div[2]//a")).click();
	Set <String> address = driver.getWindowHandles();
	for (String id:address)
	{
		System.out.println(id);
		if (id!=parent) 
		{
			driver.switchTo().window(id);
		}	
	}
	//Reservation | HOTEL PLANISPHERE - Website for Practice Test Automation child page
	System.out.println(driver.getTitle());
	//checkin date calender handle
	driver.findElement(By.id("date")).click();
	String checkIn="October 2023";
	String day = "25";
	while (true)
	{
		String month = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
		if (month.equals(checkIn))
		{
			break;
		}
		else
		{
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
		}
	}
	
	driver.findElement(By.xpath("//div[2]/table[1]/tbody[1]/tr/td/a[contains(text(), "+day+")]")).click();
	String checkinAct = driver.findElement(By.id("date")).getText();
	System.out.println(checkinAct);
	
	//Staying days
	Actions act = new Actions(driver);
	WebElement Staying = driver.findElement(By.id("term"));
	act.moveToElement(Staying).click().keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyDown(Keys.NUMPAD3).build().perform();
	WebElement guests = driver.findElement(By.id("head-count"));
	act.moveToElement(guests).click().keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyDown(Keys.NUMPAD2).build().perform();
	driver.findElement(By.id("breakfast")).click();
	driver.findElement(By.id("early-check-in")).click();
	driver.findElement(By.id("sightseeing")).click();
	driver.findElement(By.id("username")).sendKeys("ABC");
	WebElement confirmation = driver.findElement(By.id("contact"));
	Select sel = new Select(confirmation);
	sel.selectByValue("email");
	driver.findElement(By.id("email")).sendKeys("clark@example.com");
	driver.findElement(By.id("comment")).sendKeys("arrangements and facilities should be in nextlevel");
	String actualTotal = driver.findElement(By.id("total-bill")).getText();
	String ExpectedTotal = "$640.00";
	Assert.assertEquals(actualTotal, ExpectedTotal);
	driver.findElement(By.id("submit-button")).click();
	String actBill = driver.findElement(By.id("total-bill")).getText();
	String ExpBill = "Total $640.00 (included taxes)";
	Assert.assertEquals(actBill, ExpBill);
	if (actBill.equals(ExpBill))
	{
		driver.findElement(By.xpath("//button[contains(text(),'Submit Reservation')]")).click();
	}
	String tit = driver.switchTo().activeElement().getText();
	System.out.println(tit);
	driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
	driver.close();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	driver.switchTo().window(parent);
	
}
}