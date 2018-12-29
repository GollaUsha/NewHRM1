package DriverFile;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	public void startTest() throws Throwable {
		ExcelFileUtil excel = new ExcelFileUtil();
		for (int i = 0; i <= excel.rowCount("MasterTestCases"); i++) 
		{
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")) {
				String TCModule = excel.getData("MasterTestCases", i, 1);
				String ModuleStatus = "";
				report=new ExtentReports("./Reports/"+TCModule+".html"+"_"+FunctionLibrary.generateDate());
				logger=report.startTest(TCModule);
			    int rowCount = excel.rowCount(TCModule);
				for (int j = 0; j <= rowCount; j++)
				{
					String Description = excel.getData(TCModule, j, 0);
					String Object_Type = excel.getData(TCModule, j, 1);
					String Locator_Type = excel.getData(TCModule, j, 2);
					String Locator_Value = excel.getData(TCModule, j, 3);
					String Test_Data = excel.getData(TCModule, j, 4);
					try
					{
						if (Object_Type.equalsIgnoreCase("startBrowser")) 
						{
							driver = FunctionLibrary.startBrowser(driver);
						logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("openApplication")) 
						{
							FunctionLibrary.openApplication(driver);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("waitForElement")) 
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("closeApplication")) 
						{
							FunctionLibrary.closeApplication(driver);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("title_Validations"))
						{
							FunctionLibrary.title_Validations(driver, Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("searchEmpId"))
						{
						FunctionLibrary.searchEmpId(driver, Locator_Type, Locator_Value);
							
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_Type.equalsIgnoreCase("empID"))
						{
							FunctionLibrary.tableValidation(driver,Test_Data);
					
							logger.log(LogStatus.INFO, Description);
						}
						
						excel.setData(TCModule, j, 5, "Pass");
						ModuleStatus = "true";
						logger.log(LogStatus.PASS, Description + "  Pass");
						}//tery
					catch (Exception e) 
					{
						excel.setData(TCModule, j, 5, "Fail");
						ModuleStatus = "false";
						logger.log(LogStatus.FAIL, Description+"  Fail");
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("./Screenshots/"+"_"+FunctionLibrary.generateDate()+".jpg"));
						break;
						}
					catch (AssertionError e) 
					{
						excel.setData(TCModule, j, 5, "Fail");
						logger.log(LogStatus.FAIL, Description+" Fail");
						ModuleStatus = "false";
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File("./Screenshots/"+"_"+FunctionLibrary.generateDate()+".jpg"));
						break;
					}
				}//j
				if (ModuleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCases", i, 3, "Pass");
				} else
				{
					excel.setData("MasterTestCases", i, 3, "Fail");
				} 
			}
			else 
			{
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
			
			}//i
		
	report.endTest(logger);
	report.flush();
	
	}
}
