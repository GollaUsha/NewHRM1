package DriverFile;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Win {

public static WebDriver driver ;
public static void winTab()
{
Set<String> id = driver.getWindowHandles();
Iterator<String> tab = id.iterator();
while (tab.hasNext()) {
	tab.next();
	String tab1Window = tab.next();
	driver.switchTo().window(tab1Window);
	System.out.println(tab1Window);
	driver.findElement(By.linkText("Report a problem")).click();
}
	}
}
