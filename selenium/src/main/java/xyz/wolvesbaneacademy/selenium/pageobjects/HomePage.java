/**
 * 
 */
package xyz.wolvesbaneacademy.selenium.pageobjects;

import org.openqa.selenium.WebDriver;

import xyz.wolvesbaneacademy.selenium.components.Menu;

/**
 * @author SPConlin
 *
 */
public class HomePage extends Page {
	
	/**
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @param driver
	 * @param pageTitle
	 */
	public HomePage(WebDriver driver, String pageTitle) {
		super(driver, pageTitle);
	}
	
	public Menu getMenu() {
		return new Menu(driver);
	}

}
