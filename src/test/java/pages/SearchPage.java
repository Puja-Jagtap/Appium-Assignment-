package pages;

import Appium_Assignment.Tester;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import Appium_Assignment.Reporting;

	public class SearchPage extends Tester{

//      Element Declaration
		
		String productName = "com.ebay.mobile:id/textview_item_name";
		String productPrice = "com.ebay.mobile:id/textview_item_price";
		String addToCartBtn = "//android.widget.Button[@text='Add to cart' and @index='1']";
		String cartIcon = "com.ebay.mobile:id/call_to_action_button";
		
		
//		 Description: Method to get the product details
//		 report - class object for generating HTML report and logging

		public String getProductDetails(Reporting report) throws Exception
		{
			
			String productNameUi=getText("id",productName,report);
			System.out.println("Product Name: "+productNameUi);
			Thread.sleep(1000);
			String productpriceui=getText("id",productPrice,report); 
			System.out.println("Product Price: "+productpriceui);
			
//			Scroll down window
			Thread.sleep(1000);		
			MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
			        "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" +
			         ".scrollIntoView(new UiSelector().text(\"Similar sponsored items\"))"));
		
			click("xpath", addToCartBtn, report);
			return productNameUi;

		}
		
//		 Description: Method to navigate to Cart
//		 report - class object for generating HTML report and logging

		public void navigateToCart(Reporting report) throws Exception
		{
			Thread.sleep(1000);
			click("id", cartIcon, report);
		}
}
