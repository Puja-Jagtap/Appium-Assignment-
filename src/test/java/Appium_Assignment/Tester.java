package Appium_Assignment;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Tester {
	public static AppiumDriver<MobileElement> driver;
	
	public void setup(Reporting extentReport) throws Exception {

//		  Set Capabilities for device
		  try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "puja");
			cap.setCapability("udid", "WGPF59OBIVP7XWPV");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "10");
			cap.setCapability("appPackage", "com.ebay.mobile");
			cap.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
			cap.setCapability("noReset", "true");
			
//			login to eBay App
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			
			driver = new AppiumDriver<MobileElement>(url,cap);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("Application Started");
			Thread.sleep(1000);
			
		  }catch (Exception e) {
				e.printStackTrace();
				Assert.assertTrue(false, e.getMessage());

			}
	  }

//	 Description: Reusable function to click on an element
//	 Attribute: elementType- Element type String passed is an id or xpath
//	  		    identifier- unique element identifier
//	  			report- Class object of Reporting to generate extent report

	public void click(String elementType,String identifier,Reporting report) 
	{
		try
		{
			if(elementType.equalsIgnoreCase("id"))
				driver.findElement(By.id(identifier)).click();
			else if(elementType.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(identifier)).click();

			report.extentReportPass(identifier+ "is clicked");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}

//	 Description: Reusable function to fetch the text from an object 
//	 Attribute: elementType- element type String passed is an id or xpath
//	 		    identifier- unique element identifier
//			    report- Class object of Reporting to generate extent report

	public String getText(String elementType,String identifier,Reporting report)
	{

		String text="";
		try
		{
			if(elementType.equalsIgnoreCase("id"))
				text=driver.findElement(By.id(identifier)).getText();
			else if(elementType.equalsIgnoreCase("xpath"))
				text=driver.findElement(By.xpath(identifier)).getText();

			report.extentReportPass(text+" is retrieved from "+identifier);
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
		}
		return text;
	}
	
//	 Description: Reusable function to input value in a text field
//	 Attribute: elementType- Element type String passed is an id or xpath
// 			    identifier- unique element identifier
//	 			report- Class object of Reporting to generate extent report
//	  			value- String value to be inserted in the text field


	public void inputValue(String elementType,String identifier,Reporting report, String value)
	{
		try {
			if(elementType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(identifier)).click();
				driver.findElement(By.id(identifier)).clear();
				driver.findElement(By.id(identifier)).sendKeys(value);

				report.extentReportPass(identifier+ "is inputed with "+value);
			}
			else if(elementType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(identifier)).click();
				driver.findElement(By.xpath(identifier)).clear();
				driver.findElement(By.xpath(identifier)).sendKeys(value);

				report.extentReportPass(identifier+ "is inputed with "+value);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
//	 Description: Reusable function to compare 2 strings 
//	 Attribute: smallString-String value to be compared with
//	 			bigString- String value to be compared in
//	  			report- Class object of the Reporting class for generating extent report

	public void stringContains(String smallString,String bigString,Reporting report)
	{
		if(bigString.contains(smallString))
		{
			Assert.assertTrue(true, "String comparison Successful");
			report.extentReportPass("String compared successfully");
		}
		else
		{
			Assert.assertTrue(false, "String comparison Failed expected:"+bigString+" contains "+smallString );
			report.extentReportFail("String comparison failed");
		}
	}

}
