/**
 * 
 */
package xyz.wolvesbaneacademy.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Shawn P. Conlin
 *
 */
public class BasePage {
	protected WebDriver webDriver;
	
	/**
	 * Object constructor
	 */
	public BasePage(WebDriver driver) {
		webDriver = driver;
	}

	/**
	 * Checks for the specified text within the web element found using the provided locator Allows for specifying the
	 * timeout delay in seconds.
	 * 
	 * @param locator
	 * @param value
	 * @param timeout
	 * @return boolean
	 */
	public boolean isTextPresent(By locator, String value, int timeout) {
		WebDriverWait wait = new WebDriverWait(webDriver, timeout);
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
		} catch (TimeoutException toe) {
			result = false;
		}

		return result;
	}

}
