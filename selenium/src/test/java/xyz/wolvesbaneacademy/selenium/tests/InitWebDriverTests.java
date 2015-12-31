package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.*;

public class InitWebDriverTests {
	private WebDriver driver;

	@Test
	public void f() {
		System.out.println(driver.getTitle());
	}

	@BeforeMethod
	public void beforeMethod() {
		// Firefox Driver
		//driver = new FirefoxDriver();

		// Chrome Driver
		//driver = new ChromeDriver();

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
	}

}
