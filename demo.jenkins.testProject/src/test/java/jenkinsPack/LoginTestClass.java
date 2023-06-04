package jenkinsPack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestClass {

	WebDriver driver = null;

	@BeforeSuite
	public void config() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void navigateToUrl() {
		driver.get("https://www.saucedemo.com/");
	}

	@Test(priority = 2)
	public void loginApp() {
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	public WebElement eleDisplay() {
		WebElement element = driver.findElement(By.xpath("//div[@id='header_container']//span[text()='Products']"));
		return element;
	}

	@Test(priority = 3)
	public void validateHomePageText() {
		if (eleDisplay().isDisplayed()) {
			System.out.println("Login Success!");
		} else {
			System.out.println("Login Failed!");
		}
	}

	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}

}