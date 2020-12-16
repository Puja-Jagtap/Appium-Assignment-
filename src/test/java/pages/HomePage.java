package pages;

import java.util.concurrent.TimeUnit;

import Appium_Assignment.Tester;
import Appium_Assignment.Reporting;

public class HomePage extends Tester{
	
//	    Element declaration
	
		String searchBox = "com.ebay.mobile:id/search_box";
		String dropdown = "com.ebay.mobile:id/search_src_text";
		String product = "com.ebay.mobile:id/text";
		String prod = "//android.widget.RelativeLayout[@text ='' and @index='2']";		
		
//		 Description: Method to Search a Product
//		 report - class object for generating HTML report and logging

		public void searchProduct(Reporting report, String prod) throws Exception
		{
			Thread.sleep(1000);
			click("id", searchBox, report);

			Thread.sleep(1000);
			inputValue("id", dropdown, report, prod);

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Thread.sleep(1000);
			click("id", product, report);

		}

//		 Description: Method to select a searched product 
//		 report - class object for generating HTML report and logging

		public void selectSearchProduct(Reporting report) throws Exception 
		{
			Thread.sleep(1000);
			click("xpath",prod, report);
			Thread.sleep(1000);

		}
}
