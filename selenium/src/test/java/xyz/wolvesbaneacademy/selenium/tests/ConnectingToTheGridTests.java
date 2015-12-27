package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import xyz.wolvesbaneacademy.selenium.framework.Browser;

public class ConnectingToTheGridTests {
	WebDriver driver;
  @Test
  public void NavigationTest() {
	  Assert.assertTrue(driver.getTitle().contains("Wolvesbane Academy"));
  }
  @BeforeMethod
  public void beforeMethod() {
	  try {
		  DesiredCapabilities capabilities = new DesiredCapabilities();
		  FirefoxProfile ffProfile = new FirefoxProfile();
		  capabilities = DesiredCapabilities.firefox();
		  capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
		  capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
		  capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		  capabilities.setCapability(CapabilityType.VERSION, "");
		  
		  URL driverHub = new URL("http://127.0.0.1:4444/wd/hub/");
		  driver = new RemoteWebDriver(driverHub, capabilities);
		  driver.navigate().to("http://www.wolvesbaneacademy.xyz/");
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  @AfterMethod
  public void afterMethod() {
	  Browser.Close(driver);
  }

}
