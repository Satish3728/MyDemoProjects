package project2;
import static org.testng.Assert.assertFalse;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class OpenCart {
	WebDriver driver;
	SoftAssert sf = new SoftAssert();
	@Parameters("browserName")
	@BeforeTest
	public void initialiseBrowser(String browserName ) {
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
	driver.quit();
	sf.assertAll("confirmation messages are not displaying properly");
	}
	
	@Parameters("url")
	@Test
	public void launchApp(String url) {
		driver.get(url);
	}
	
	@Parameters({"email", "password", "firstName", "lastName", "telephone"})
	@Test
	public void register(String email, String password, String firstName, String lastName, String telephone) {
		driver.findElement(By.xpath("//span[contains(text(), 'My Account')]")).click();
		driver.findElement(By.linkText("Register")).click();
		Actions act = new Actions(driver);
		WebElement FirstName = driver.findElement(By.id("input-firstname"));
		WebElement LastName = driver.findElement(By.id("input-lastname"));
		WebElement EMail = driver.findElement(By.id("input-email"));
		WebElement TelePhone = driver.findElement(By.id("input-telephone"));
		WebElement PassWord = driver.findElement(By.id("input-password"));
		WebElement ConfPass = driver.findElement(By.id("input-confirm"));

		act.moveToElement(FirstName).click().sendKeys(firstName).build().perform();
		act.moveToElement(LastName).click().sendKeys(lastName).build().perform();
		act.moveToElement(EMail).click().sendKeys(email).build().perform();
		act.moveToElement(TelePhone).click().sendKeys(telephone).build().perform();
		act.moveToElement(PassWord).click().sendKeys(password).build().perform();
		act.moveToElement(ConfPass).click().sendKeys(password).build().perform();
		//Subscribe to news letter
		boolean radio = driver.findElement(By.xpath("//label[text()='Yes']")).isSelected();
		assertFalse(radio);
		if(radio == false)
		{
			driver.findElement(By.xpath("//label[text()='Yes']")).click();
		}
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String Actual = driver.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
		String Expected = "Your Account Has Been Created!";
		sf.assertEquals(Actual, Expected, "Error in creation");

		driver.findElement(By.xpath("//a[contains(text(), 'Continue')]")).click();
	}

	@Test
	public void logOut() {
		driver.findElement(By.xpath("//span[contains(text(), 'My Account')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
	}

	
	@Parameters({"email", "password"})
	@Test
	public void login(String email, String password) {
		driver.findElement(By.xpath("//span[contains(text(), 'My Account')]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	
	@Test
	public void addToCart() {
		Actions act = new Actions(driver);
		WebElement Components = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Components')]"));
		WebElement monitor = driver.findElement(By.xpath("//a[contains(text(),'Monitors (2)')]"));
		act.moveToElement(Components).moveToElement(monitor).click().build().perform();
		driver.findElement(By.linkText("Samsung SyncMaster 941BW")).click();
		//adding the quantity
		WebElement quantity = driver.findElement(By.id("input-quantity"));
		act.moveToElement(quantity).click().keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyDown(Keys.NUMPAD2).build().perform();
		driver.findElement(By.id("button-cart")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		String actualmsg = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		System.out.println(actualmsg);
		String expectedmsg = "Success: You have added Samsung SyncMaster 941BW to your shopping cart!";
		sf.assertEquals(actualmsg, expectedmsg);
		//Buy option
		driver.findElement(By.linkText("shopping cart")).click();
		String actualconfirmation = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		String expectedconfirmation = "Products marked with *** are not available in the desired quantity or not in stock!";
		sf.assertEquals(actualconfirmation, expectedconfirmation);
		driver.findElement(By.xpath("//i[@class='fa fa-refresh']")).click();
		String actualupdate = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		String expectedupdate = "Success: You have modified your shopping cart! ";
		sf.assertNotEquals(actualupdate, expectedupdate);
	}
}
