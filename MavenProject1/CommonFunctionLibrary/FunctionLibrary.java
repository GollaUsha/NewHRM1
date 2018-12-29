package CommonFunctionLibrary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {

	public static WebDriver startBrowser(WebDriver driver) throws Throwable, Throwable {
		if (PropertyFileUtil.getValaueForkey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
			driver = new ChromeDriver();

		}
		if (PropertyFileUtil.getValaueForkey("Browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "CommonJarFiles/geckodriver.exe");
			driver = new FirefoxDriver();

		}
		if (PropertyFileUtil.getValaueForkey("Browser").equalsIgnoreCase("internetExplorer")) {
			System.setProperty("webdriver.ie.driver", "CommonJarFiles/internetExplorerdriver.exe");
			driver = new InternetExplorerDriver();

		}
		return driver;

	}

	public static void openApplication(WebDriver driver) throws Throwable, Throwable {
		driver.get(PropertyFileUtil.getValaueForkey("URL"));
		driver.manage().window().maximize();
	}

	public static void typeAction(WebDriver driver, String locator_Type, String locator_Value, String data) {
		if (locator_Type.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator_Value)).clear();
			driver.findElement(By.id(locator_Value)).sendKeys(data);

		} else if (locator_Type.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator_Value)).clear();
			driver.findElement(By.name(locator_Value)).sendKeys(data);
		} else if (locator_Type.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator_Value)).clear();
			driver.findElement(By.xpath(locator_Value)).sendKeys(data);
		}
	}

	public static void clickAction(WebDriver driver, String locator_Type, String locator_Value) {
		if (locator_Type.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator_Value)).click();
		} else if (locator_Type.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator_Value)).click();
		} else if (locator_Type.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator_Value)).click();
		} else if (locator_Type.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locator_Value)).click();
		} else if (locator_Type.equalsIgnoreCase("partialLinkText")) {
			driver.findElement(By.partialLinkText(locator_Value)).click();
		}
	}

	public static void waitForElement(WebDriver driver, String locator_Type, String locator_Value) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		if (locator_Type.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator_Value)));
		} else if (locator_Type.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator_Value)));
		}

		else if (locator_Type.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_Value)));
		}

	}

	public static void title_Validations(WebDriver driver, String exp_Title) {
		String act_title = driver.getTitle();
		Assert.assertEquals(act_title, exp_Title);
	}

	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_SS");
		return sdf.format(date);
	}
	public static void  searchEmpId(WebDriver driver,String locator_Type, String locator_Value) throws Throwable
	{
		String empid=driver.findElement(By.id(locator_Value)).getAttribute("value");
		System.out.println("empid"+empid);
		FileWriter fw=new FileWriter("./ConfigFile/WriteTxtFile.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(empid);
		bw.close();
		fw.close();
	}
	public static void mouseActions(WebDriver driver,String locator_Type,String locator_Value)
	{
		Actions act=new Actions(driver);
		if(locator_Type.equalsIgnoreCase("id"))
		{
			act.moveToElement(driver.findElement(By.id(locator_Value))).build().perform();
		}
		else if(locator_Type.equalsIgnoreCase("xpath"))
		{
			act.moveToElement(driver.findElement(By.xpath(locator_Value))).build().perform();
		}
	}
	public static void tableValidation(WebDriver driver,String colNum) throws Throwable
	{
		FileReader ff=new FileReader("./ConfigFile/WriteTxtFile.txt");
		BufferedReader br=new BufferedReader(ff);
		String exp_data=br.readLine();
		int colNum1=Integer.parseInt(colNum);
		if(driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.box"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.panel"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.panel"))).sendKeys(exp_data);
		}
		else {
			driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.box"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.panel"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("search.panel"))).sendKeys(exp_data);
		}
		
		WebElement webTable=driver.findElement(By.xpath(PropertyFileUtil.getValaueForkey("WebTable.path")));
		List<WebElement> rows=driver.findElements(By.tagName("tr"));
		for(int i=1;i<=rows.size();i++)
		{
			String act_data=driver.findElement(By.xpath
					("//*[@id='tableWrapper']/table[@class='table hover']/tbody/tr["+i+"]/td["+colNum1+"]")).getText();
		}
	
	}
	public static void closeApplication(WebDriver driver) {
		driver.close();
	}

}
