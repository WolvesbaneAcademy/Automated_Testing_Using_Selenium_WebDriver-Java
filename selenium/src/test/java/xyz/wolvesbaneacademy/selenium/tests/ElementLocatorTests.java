package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.*;

public class ElementLocatorTests {
	private WebDriver driver;

	@Test
	public void testElementLocators() {
		driver.manage().window().maximize();
		driver.navigate().to("http://www.wolvesbaneacademy.xyz");
		
		// ID
		By menuItemLocator = By.id("menu-item-193");
		WebElement articles = driver.findElement(menuItemLocator);
		articles.click();
		Assert.assertEquals(driver.getTitle(), "Articles | Wolvesbane Academy | A Fount of Knowledge for the Perpetual Student");
		
		// Name
		By searchContainer = By.id("search-2");
		WebElement searchCont = driver.findElement(searchContainer);
		
		By searchBoxLocator = By.name("s");
		WebElement searchBox = searchCont.findElement(searchBoxLocator);
		searchBox.sendKeys("Automate");
		searchBox.submit();
		
		// XPath
		By searchBoxLoc = By.xpath("//*[@id='search-2']/form/label/input");
		
		// CSS Selector
		By searchBoxLocCSS = By.cssSelector("#search-2 > form > label > input");
		
		// Link Text
		By linktext = By.linkText("Using Page Objects");
		
		// Partial Link Text
		By partialLinkText = By.partialLinkText("Page Objects");
		
		// Class Name
		By className = By.className("main-content");
		
		// Tag Name
		By tagName = By.tagName("h2");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

}
