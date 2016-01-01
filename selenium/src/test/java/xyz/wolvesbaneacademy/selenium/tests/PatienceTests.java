package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatienceTests {
	private WebDriver driver;

	@Test
	public void testPatience() {
		driver.manage().window().maximize();
		driver.navigate().to("http://www.wolvesbaneacademy.xyz");
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By searchBoxLocator = By.xpath("//*[@id='search-2']/form/label/input");
		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
		// WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.sendKeys("Automate");
		searchBox.submit();
		
	}

	@BeforeMethod
	public void beforeMethod() {
		// Remote Web Driver
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			FirefoxProfile ffProfile = new FirefoxProfile();
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
			capabilities.setCapability(CapabilityType.PLATFORM,
					Platform.WINDOWS);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			capabilities.setCapability(CapabilityType.VERSION, "");

			URL driverHub = new URL("http://127.0.0.1:4444/wd/hub/");
			driver = new RemoteWebDriver(driverHub, capabilities);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

}
