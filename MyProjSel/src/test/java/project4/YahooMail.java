package project4;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class YahooMail {
	WebDriver driver;
	
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
	public void tear() {
		driver.quit();
	}
	
	@Parameters("url")
	@Test
	public void launchApp(String url) {
		driver.get(url);
	}
	
	@Parameters ({"email", "password", "recepient1", "recepient2", "recepient3" })
	@Test
	public void yahoo(String email, String pass, String r1, String r2, String r3) throws InterruptedException {
		driver.findElement(By.id("login-username")).sendKeys(email);
		driver.findElement(By.name("signin")).click();
		
			Thread.sleep(20000);
		
		driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		driver.findElement(By.xpath("//a[@id='ybarMailLink']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(04));
		
		//compose and sending mail
		driver.findElement(By.linkText("Compose")).click();
		driver.findElement(By.xpath("//input[@id='message-to-field']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.xpath("//input[@id='message-to-field']")).click();
		driver.findElement(By.xpath("//input[@id='message-to-field']")).sendKeys(r1);
		driver.findElement(By.xpath("//button[contains(text(),'Cc / Bcc')]")).click();
		driver.findElement(By.xpath("//input[@id='message-cc-field']")).click();
		driver.findElement(By.xpath("//input[@id='message-cc-field']")).sendKeys(r2);
		driver.findElement(By.xpath("//input[@id='message-bcc-field']")).click();
		driver.findElement(By.xpath("//input[@id='message-bcc-field']")).sendKeys(r3);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath("//input[@class='q_T y_Z2hYGcu je_0 jb_0 X_0 N_fq7 G_e A_6EqO c1AVi73_6FsP C_Z281SGl ir_0 P_0 bj3_Z281SGl b_0 j_n d_72FG em_N']")).click();
		driver.findElement(By.xpath("//input[@class='q_T y_Z2hYGcu je_0 jb_0 X_0 N_fq7 G_e A_6EqO c1AVi73_6FsP C_Z281SGl ir_0 P_0 bj3_Z281SGl b_0 j_n d_72FG em_N']"))
		.sendKeys("Regarding FCI Result Declaration");
		driver.findElement(By.xpath("//div[@class='rte em_N ir_0 iy_A iz_h N_6Fd5']")).click();
		driver.findElement(By.xpath("//body[@class='bold-focus pointer-mode']/div[@id='mail-app-container']/div[@class='pointer-mode H_6D6F']/div[@class='D_F ek_BB H_6D6F aw_2941hk ba_10I1Qt az_oOItw ay_Z1nkUQx I_kt4zd']/div[@id='app']/div[@class='I_ZS20V7 D_F em_N o_h W_6D6F H_6D6F']/div[@class='D_F em_N o_h s_1HCsWR']/div[@id='mail-app-component-container']/div[@id='mail-app-component']/div[@class='em_N gl_C o_h D_F p_R U_0']/div[@class='p_R W_6D6F H_6D6F R_0 T_0 L_0 B_0 D_F']/div[contains(@class,'I_ZkbNhI j_n k_w W_6D6F D_F ek_BB em_N P_ZzJed p_R')]/div[@class='em_N D_F ek_BB p_R o_h']/div[@data-test-id='compose-toolbar']/div[@class='z_Z14vXdP D_F ab_C I_ZkbNhI W_6D6F p_R B_0']/div[@class='en_N J_x o_h cZ1RN91d_n']/span[2]/button[1]")).click();
		driver.findElement(By.xpath("//div[@class='rte em_N ir_0 iy_A iz_h N_6Fd5']")).sendKeys("FCI Results announced on 01June2023.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath("//div[@class='rte em_N ir_0 iy_A iz_h N_6Fd5']")).sendKeys("  (Mail has been sent through using Java Code...!)");
		driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
		//sent box
		driver.findElement(By.xpath("//span[contains(text(),'Sent')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(04));
		driver.findElement(By.xpath("//span[@class='en_N o_h G_e J_x']")).click();    //opening the sent mail
		String text = driver.findElement(By.xpath("//span[@data-test-id='message-group-subject-text']")).getText();	//get the internal text
		System.out.println(text);
		
		String expt = "Regarding FCI Result Declaration";	//expected text
		//validating the text present
		if(expt.equals(text))
		{
			System.out.println("Test is passed");
		}
		else
		{
			System.out.println("Test is failed");
		}	

		//signing out
		Actions act = new Actions(driver);
		WebElement prof = driver.findElement(By.xpath("//label[@id='ybarAccountMenuOpener']"));
		WebElement signout = driver.findElement(By.xpath("//a[@id='profile-signout-link']"));
		act.moveToElement(prof).moveToElement(signout).click().build().perform();
	}
}
