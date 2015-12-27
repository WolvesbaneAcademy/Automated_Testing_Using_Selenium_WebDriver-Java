package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import xyz.wolvesbaneacademy.selenium.framework.Browser;

public class NavigationTest {
	WebDriver driver;
  @Test
  public void Navigation() {
	  Assert.assertTrue(driver.getTitle().contains("Wolvesbane Academy"));
  }
  @BeforeMethod
  public void beforeMethod() {
	  try {
		driver = Browser.Driver(new URL("http://localhost:4444/wd/hub/"), "chrome", "");
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
