package DriverFile;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void StartApp() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
	WebDriver	driver= new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
	System.out.println("Comments added");
		
	}

}