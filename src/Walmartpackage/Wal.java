package Walmartpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Wal {

		WebDriver driver;
		JavascriptExecutor executor =(JavascriptExecutor)driver;
		Actions actions= new Actions(driver);
		@BeforeSuite
		public void intialisation() {
			System.setProperty("webdriver.chrome.driver","chromedriver.exe" );
		}
		@BeforeTest
		public void setup() throws InterruptedException{
			this.driver=new ChromeDriver();
		}
		@Test
		public void CreateAccount() throws InterruptedException {
			this.driver.get("https://www.walmart.ca/create-account");
			Thread.sleep(3000);
			 driver.findElement(By.id("firstName")).sendKeys("Nav");
		     driver.findElement(By.id("lastName")).sendKeys("sandhu");
		     driver.findElement(By.id("email")).sendKeys("san@gmail.com");
		     driver.findElement(By.id("password")).sendKeys("love@123");
		     WebElement gg=  driver.findElement(By.id("TAndC"));
		     WebElement hh= driver.findElement(By.id("marketingPreference"));
		      executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		      executor.executeScript("arguments[0].click();", gg);
		      executor.executeScript("arguments[0].click();", hh);
		      Thread.sleep(3000);
		      driver.findElement(By.xpath("//*[@id=\"create-account-form\"]/div/div[9]/button")).click();
		}
		
		@Test
		public void MyAccount(){
			String strUrl = driver.getCurrentUrl();
		      System.out.println("Verify the Current Url is:"+ strUrl);
		      driver.findElement(By.xpath("//a[@href='/cart']")).click();//to view shopping cart
		      driver.navigate().to("https://www.walmart.ca/my-account");
		      driver.findElement(By.linkText("My orders")).click();//to view history of orders
		      driver.navigate().to("https://www.walmart.ca/my-account");
		      //if redeem coupon is applicable
		      driver.switchTo().frame(0);
		      driver.findElement(By.id("redeemEvoucherInput")).sendKeys("123");
		      driver.findElement(By.xpath("//button[contains(text(),'Redeem code')]")).click();
		      }
		
		@Test
		public void ProfileSetting() throws InterruptedException{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"accounts-home-page\"]/div[3]/div[1]/div[4]/a[1]")).click();//click profile setting
			driver.findElement(By.xpath(" //*[@id=\"skip-to-main\"]/div[1]/div[1]/div/div[1]/div[1]/button")).click();//click edit to edit full name
			driver.findElement(By.xpath("//*[@id=\"skip-to-main\"]/div[1]/div[2]/div/div/div[1]/div[1]/button")).click();//click edit to edit email address
			//to add Associate Discount
			driver.findElement(By.xpath("//*[@id=\"skip-to-main\"]/div[1]/div[4]/div/div/div[1]/button")).click();//click add to add Associate Discount
            driver.findElement(By.id("winNumber")).sendKeys("wwswr");
            driver.findElement(By.id("discountNumber")).sendKeys("12332");
            WebElement aa=  driver.findElement(By.id("agreeDiscountPolicy"));//to select checkbox of  the policy
            executor.executeScript("arguments[0].click();",aa);
            driver.findElement(By.xpath("//*[@id=\"skip-to-main\"]/div[1]/div[4]/div/div[4]/button[1]")).click();//to click save Button
		}
		
		@Test
		public void MyRecommendationPage() {
			actions.moveToElement(driver.findElement(By.xpath("//span[text()='My account']"))).build().perform();
	        driver.findElement(By.linkText("My recommendations")).click();
		}
		
		@Test
		public void MyOrder() throws InterruptedException {
			Thread.sleep(2000);
			actions.moveToElement(driver.findElement(By.xpath("//span[text()='My account']"))).build().perform();
			driver.findElement(By.linkText("My orders")).click();
			driver.switchTo().frame(0);
			//View Order from drop down box should let you to pick any year from last 10 years from currentyear
			Select sec= new Select(driver.findElement(By.id("view-orders-from-label")));
	        sec.selectByVisibleText("2021");
			//to display the Message
	        driver.findElement(By.xpath("//*[@id=\"order-history-page\"]/div[1]/div/div[2]/div[2]/div[2]/div")).getText();
		}
		
		@Test
		public void MySubscription() {
			actions.moveToElement(driver.findElement(By.xpath("//span[text()='My account']"))).build().perform();
	        driver.findElement(By.linkText("My subscriptions")).click();
			}
		@Test
		public void MyRegistries() {
			actions.moveToElement(driver.findElement(By.xpath("//span[text()='My account']"))).build().perform();
	        driver.findElement(By.linkText("My registries")).click();
		}
		@Test
		public void MyList() throws InterruptedException {
			//Here no AddToList option given
			actions.moveToElement(driver.findElement(By.xpath("//span[text()='My account']"))).build().perform();
	        driver.findElement(By.linkText("My lists")).click();
	        driver.findElement(By.xpath("//*[@id=\"jalapeno-template\"]/div[3]/div/form/button")).click();
	        Thread.sleep(2000);
	    }
		@Test
		public void Address() {
			driver.findElement(By.xpath("//*[contains(text(),'Addresses')]")).click();
			driver.findElement(By.xpath("//button[contains(text(),'Add address')]")).click();//to add address
		}
		
		@Test
		public void Searching_ShoppingCart() throws InterruptedException {
			driver.findElement(By.id("search-form-input")).sendKeys("apple");
	        driver.findElement(By.xpath("//*[@class='css-1v9c0kj e1xoeh2i2']")).click();
	        executor.executeScript("window.scrollBy(0,1000)");
	        driver.findElement(By.xpath("//*[contains(text(),'Apple, Gala')]")).click();
	        for (int i = 0; i < 3; i++)
	        {
	        driver.findElement(By.xpath("//button[contains(text(),'Add one more')]")).click();//add three more items
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();//add to cart
	        }
	        driver.findElement(By.xpath("//a[@href='/cart']")).click();//to view item in cart
	        executor.executeScript("window.scrollBy(0,1000)");
	        driver.findElement(By.xpath("//button[contains(text(),'Remove Apple, Gala from your cart.')]")).click();//Remove item from cart
	        
		}
		
		@AfterTest
		public void Signout() throws InterruptedException {
			Thread.sleep(2000);
			driver.navigate().to("https://www.walmart.ca/my-account");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).click();
		    driver.close();
			
		} }
		

		
		
	


