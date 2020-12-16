package pages;

import java.util.StringTokenizer;
import Appium_Assignment.Tester;
import Appium_Assignment.Reporting;

public class CartPage extends Tester{

//		Element Declaration
		
		String productName="com.ebay.mobile:id/item_title";
		String productCount = "com.ebay.mobile:id/item_quantity_label";
		String checkOutBtn = "com.ebay.mobile:id/shopping_cart_checkout";
		
		
//		 Description: Method to compare the products in cart
//		 report - class object for generating HTML report and logging

		public void compareProductDetails(Reporting report,String prodName) throws Exception
		{
			Thread.sleep(1000);

			String uiProdName=getText("id",productName, report);
			System.out.println();
			StringTokenizer st=new StringTokenizer(uiProdName, "...");

			System.out.println("Cart ProdName is: "+uiProdName);
			stringContains(st.nextToken(),prodName, report);
			Thread.sleep(1000);
			String elemCount=getText("id", productCount, report);
			stringContains(elemCount, "Qty 1", report);
			System.out.println("Assertion passed.");
			Thread.sleep(1000);
		}

//		 Description: Method to navigate to checkout page

		public void navigateToCheckout(Reporting report) throws Exception
		{
			Thread.sleep(1000);
			click("id",checkOutBtn, report);
			System.out.println("CheckOut Successful..");
			Thread.sleep(1000);
		}
}
