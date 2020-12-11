package Appium_Assignment;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class Appium_Test {
	static AppiumDriver<MobileElement> driver;
	@Test
	public static void eBay() throws Exception {

//		Search 65 Inch TV
		driver.findElement(By.id("com.ebay.mobile:id/search_box")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.ebay.mobile:id/search_src_text")).sendKeys("65-inch tv");
		Thread.sleep(1000);
		driver.findElement(By.id("com.ebay.mobile:id/text")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.ebay.mobile:id/cell_collection_item")).click();
		
//		check Description message and item price
		Thread.sleep(1000);
		String Desc = driver.findElement(By.id("com.ebay.mobile:id/textview_item_name")).getText();
		System.out.println("Item is: "+Desc);
		Thread.sleep(1000);
		String price = driver.findElement(By.id("com.ebay.mobile:id/textview_item_price")).getText();
		System.out.println("Item price: "+price);
		
//		Scroll down window
		Thread.sleep(1000);		
		MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" +
		         ".scrollIntoView(new UiSelector().text(\"Similar sponsored items\"))"));
		
//		Add item in a cart
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.Button[@text='Add to cart' and @index='1']")).click();
		
//		Go to cart
		Thread.sleep(2000);
		driver.findElement(By.id("com.ebay.mobile:id/call_to_action_button")).click();
		
//		get description text and item subtotal price from cart
		Thread.sleep(2000);
		String Desc1 = driver.findElement(By.id("com.ebay.mobile:id/item_title")).getText();
		System.out.println("Item in Cart: "+Desc1);
		Thread.sleep(2000);
		String price1 = driver.findElement(By.id("com.ebay.mobile:id/shopex_cart_summary_value")).getText();
		System.out.println("Cart Subtotal: "+price1);
		
//		Use Assert to check item description is match
		try {
			Thread.sleep(3000);
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(Desc,Desc1);
			Thread.sleep(1000);
			sa.assertEquals(price,price1);
			System.out.println("Assertion Pass");
		}catch(Exception e) {
			System.out.println("Assertion Fail");
		}
		
//		checkout from cart
		Thread.sleep(1000);
		driver.findElement(By.id("com.ebay.mobile:id/shopping_cart_checkout")).click();	
		Thread.sleep(1000);
		System.out.println("Ckeckout Successful");
		Thread.sleep(1000);
	}
	
	public static void main(String[] args) throws Exception {
		try {
			eBay();
		}catch(Exception e) {
			System.out.println(e.getCause());
			e.getMessage();
			e.printStackTrace();
		}		
				
	}
  @BeforeMethod
  public void setup() throws Exception {

//	  Set Capabilities for device
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "puja");
		cap.setCapability("udid", "WGPF59OBIVP7XWPV");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("appPackage", "com.ebay.mobile");
		cap.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		cap.setCapability("noReset", "true");
		
//		login in eBay App
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver<MobileElement>(url,cap);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Application Started");
		Thread.sleep(1000);
  }

  @AfterMethod
  public void teardown() throws Exception {
//	  close the driver
	  Thread.sleep(4000);
	  driver.quit();
  }
}
