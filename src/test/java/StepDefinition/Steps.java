package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Steps {

	WebDriver driver;

	@Given("^Open the Firefox and launch the application$")
	public void open_the_Firefox_and_launch_the_application() throws Throwable {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//resources//driver//geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.ebay.com.au/");
		driver.findElement(By.xpath("//*[@id=\"gh-ug\"]/a")).click();
		Thread.sleep(4000);
	}

	@When("^Enter the Username and Password$")
	public void enter_the_Username_and_Password() throws Throwable {
		driver.findElement(By.id("userid")).sendKeys("tharanyaece@gmail.com");
		driver.findElement(By.id("signin-continue-btn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Subburaj@514");
		driver.findElement(By.id("sgnBt")).click();
		Thread.sleep(5000);
	}

	@Then("^Reset the cart$")
	public void Reset_the_cart() throws Throwable {
		String textInPage;
		String expectedText = "You don't have any items in your cart. Let's get shopping!" ;
		
		//Search for books
		driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("books");
		
		//Click on the search icon
		driver.findElement(By.id("gh-btn")).click();
		
		//Open the first book
		driver.findElement(By.xpath(
				"/html/body/div[5]/div[2]/div[1]/div[1]/div/div[1]/div/div[3]/div/div[1]/div/w-root/div/div[2]/ul/li[1]/h3/a"))
				.click();
		
		//Add the item to cart
		driver.findElement(By.id("isCartBtn_btn")).click();
		
		//Remove the item from cart
		driver.findElement(By.xpath(
				"//*[@id=\"mainContent\"]/div/div[2]/div[1]/div/div/div[2]/div/div/div/div/div[2]/span[2]/button"))
				.click();
		
		//Verifying whether the item has been removed from the cart
		textInPage = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div/div/div[1]/span/span"))
				.getText();
		Assert.assertEquals(expectedText, textInPage);
		
		driver.quit();
	}
}