package Appium_Assignment;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;

public class Appium_Test extends Tester{
	
	Reporting extentReport;
	
//	 Description: Constructor for initializing the class variables and the parent class
//	 Created by: Puja Jagtap
	 
	public Appium_Test() {
		super();
	}
	
//	 Description: Before test method to initialize the drivers and reporting file

	@BeforeTest
	public void testready() throws Exception 
	{
		Appium_Test test=new Appium_Test();

//		Reporting for the Test 

		extentReport=new Reporting();
		extentReport.extentReportInit();
		extentReport.logger=extentReport.report.createTest("eBay App Test");

//		Initialize the driver
		setup(extentReport);


	}

//	 Description: Test method for executing the eBay App
	
	@Test
	public void eBayProductAddition() throws Exception
	{ 
//		calling pages classes for adding the product
		new HomePage().searchProduct(extentReport, "65 Inch TV 4k android");
		new HomePage().selectSearchProduct(extentReport);
		String prodName=new SearchPage().getProductDetails(extentReport);
		new SearchPage().navigateToCart(extentReport);
		new CartPage().compareProductDetails(extentReport, prodName);
		new CartPage().navigateToCheckout(extentReport);

	}
	    
  @AfterMethod
  public void teardown(ITestResult result) throws Exception {
//	  close the driver
	  if(ITestResult.FAILURE==result.getStatus())
		{
			extentReport.extentReportFail(result.getThrowable().getMessage());
		}
		driver.quit();
		extentReport.report.flush();
  }
}
