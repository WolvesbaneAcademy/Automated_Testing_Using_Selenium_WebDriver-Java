package xyz.wolvesbaneacademy.selenium.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import xyz.wolvesbaneacademy.selenium.components.Menu;
import xyz.wolvesbaneacademy.selenium.framework.Browser;
import xyz.wolvesbaneacademy.selenium.pageobjects.HomePage;
import xyz.wolvesbaneacademy.selenium.pageobjects.Page;

public class MenuTests {
  private WebDriver driver;
  
  @Test
  public void testArchiveMenuOption()
  {
      HomePage home = new HomePage(driver);
      Menu main = home.getMenu();
      Page newPage = main.clickArticles();
      Assert.assertTrue(newPage.isPageTitle("Articles | Wolvesbane Academy | A Fount of Knowledge for the Perpetual Student"), "Incorrect page loaded.");
  }

  @Test
  public void testVideosMenuOption()
  {
      HomePage home = new HomePage(driver);
      Menu main = home.getMenu();
      Page newPage = main.clickVideos();
      driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
      Assert.assertTrue(newPage.isPageTitle("Wolvesbane Academy - YouTube"), "Incorrect page loaded.");
  }

  @Test
  public void testGitLabMenuOption()
  {
      HomePage home = new HomePage(driver);
      Menu main = home.getMenu();
      Page newPage = main.clickGitLab();
      driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
      Assert.assertTrue(newPage.isPageTitle("Wolvesbane_Academy · GitLab"), "Incorrect page loaded.");
  }

  @BeforeMethod
  public void beforeMethod() {
      try {
		driver = Browser.Remote(new URL("http://localhost:4444/wd/hub/"), "firefox", "");
        driver.navigate().to("http://www.wolvesbaneacademy.xyz/");
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
