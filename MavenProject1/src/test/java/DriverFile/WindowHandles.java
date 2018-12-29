package DriverFile;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandles extends Win {
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
		driver= new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get("http://naukri.com");
	driver.findElement(By.linkText("Employer Zone")).click();
	Win.winTab();
	System.out.println("windoh");
	//driver.findElement(By.linkText("Report a problem")).click();
	Win.winTab();
	driver.findElement(By.linkText("Post Jobs")).click();
	}
}