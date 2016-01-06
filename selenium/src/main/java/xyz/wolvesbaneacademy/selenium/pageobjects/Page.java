/**
 * 
 */
package xyz.wolvesbaneacademy.selenium.pageobjects;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import xyz.wolvesbaneacademy.selenium.framework.Browser;


/**
 * @author SPConlin
 *
 */
public class Page {

	/**
	 * Additional Variables
	 */
	protected WebDriver driver;
	protected String pageTitle;
	protected String pageHandle;
	protected int timeOut = 10;
	
	/**
	 * This constructor accepts a WebDriver object and instantiates the
	 * page object.
	 * 
	 * @param driver
	 */
	public Page(WebDriver driver) {
		this.driver = driver;
		this.pageHandle = driver.getWindowHandle();
		this.pageTitle = driver.getTitle();
	}

	/**
	 * This constructor accepts a WebDriver object and the expected page
	 * title. After instantiating the page object, the page title is verified
	 * and an error is thrown if the page is incorrect.
	 * 
	 * @param driver
	 * @param pageTitle
	 * @throws IllegalStateException
	 */
	public Page(WebDriver driver, String pageTitle) {
		this.driver = driver;
		this.pageHandle = driver.getWindowHandle();
		
		// Check that we're on the right page.
		if (!isCurrent()) {
			throw new IllegalStateException("Incorrect page loaded: found " + driver.getTitle() + " expected " + pageTitle);
		} else {
			this.pageTitle = pageTitle; 
		}
	}

	/**
	 * Sets a new default timeOut value
	 * 
	 * @param timeOut
	 */
	public void setDefaulttimeOut(int timeout) {
		timeOut = timeout;
	}

	/**
	 * Returns the current default timeOut value
	 * 
	 * @return
	 */
	public int getDefaulttimeOut() {
		return timeOut;
	}

	/**
	 * Determines if the provided string is present in the title of the current page. Allows for specifying the amount
	 * of time to wait in seconds. If an error occurs and errCapture is true, a screenshot is saved to the Errors
	 * folder.
	 * 
	 * @param pageTitle
	 * @param errCapture
	 * @param timeOut
	 * @return boolean
	 */
	public boolean checkPageTitle(String pageTitle, boolean errCapture) {
		boolean matchFound = false;
		try {
			matchFound = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.titleContains(pageTitle));
		} catch (TimeoutException toe) {
			if (errCapture) {
				try {
					Browser.CaptureError(driver, "timeOut" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".jpg");
				} catch (IOException e) {
					e.printStackTrace();
				}
				throw toe;
			}
		}

		return matchFound;
	}

	/**
	 * Checks for the specified text within the web element found using the provided locator Allows for specifying the
	 * timeOut delay in seconds.
	 * 
	 * @param locator
	 * @param value
	 * @param timeOut
	 * @return boolean
	 */
	public boolean isTextPresent(By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
		} catch (TimeoutException toe) {
			result = false;
		}

		return result;
	}

	/**
	 * Checks for the specified text within the web element found using the provided locator Allows for specifying the
	 * timeOut delay in seconds.
	 * 
	 * @param locator
	 * @param value
	 * @param timeOut
	 * @return boolean
	 */
	public boolean isTextPresentInValue(By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		boolean result = false;

		try {
			result = wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
		} catch (TimeoutException toe) {
			result = false;
		}

		return result;
	}

	/**
	 * Checks if the web element found using the provided locator is visible Allows for specifying the timeOut delay in
	 * seconds.
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean isElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.invisibilityOfElementLocated(locator))) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Checks to see if the specified element missing from the page
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementAbsent(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		if (elements.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if the web element found using the provided locator is part of the DOM Allows for specifying the timeOut
	 * delay in seconds.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return boolean
	 */
	public boolean isElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			if (elements.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (TimeoutException te) {
			return false;
		}
	}

	/**
	 * Checks if the element found using the provided locator is enabled
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean isElementEnabled(By locator) {
		WebElement item = getWhenPresent(locator);
		if (null != item) {
			return item.isEnabled();
		} else {
			return false;
		}
	}

	/**
	 * Checks if the specified web element has been detached from the DOM. Allows for specifying the timeOut delay in
	 * seconds. If an error occurs, a screenshot is saved to the Errors folder.
	 * 
	 * @param webElement
	 * @param timeOut
	 * @return boolean
	 */
	public boolean isElementStale(WebElement webElement) {
		boolean isStale = false;
		try {
			isStale = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.stalenessOf(webElement));
		} catch (TimeoutException toe) {
			try {
				Browser.CaptureError(driver, "timeOut" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw toe;
		}
		return isStale;
	}

	/**
	 * Returns the web element specified by the provided locator once it can be found in the DOM Allows for specifying
	 * the timeOut delay in seconds. If an error occurs, a screenshot is saved to the Errors folder.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getWhenPresent(By locator) {
		WebElement foundElement = null;
		try {
			foundElement = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (TimeoutException toe) {
			try {
				Browser.CaptureError(driver, "timeOut" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw toe;
		}
		return foundElement;
	}

	/**
	 * Returns the web element specified by the provided locator once it is visible in the browser Allows for specifying
	 * the timeOut delay in seconds. If an error occurs, a screenshot is saved to the Errors folder.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getWhenVisible(By locator) {
		
		WebElement foundElement = null;
		try {
			foundElement = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			try {
				Browser.CaptureError(driver, "timeOut" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw toe;
		}
		return foundElement;
	}

	/**
	 * Returns a list of web elements found in the DOM matching the provided locator Allows for specifying the timeOut
	 * delay in seconds.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return List<WebElement>
	 */
	public List<WebElement> getAllWhenPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return elements;
	}

	/**
	 * Returns a list of web elements found in the DOM matching the provided locator Allows for specifying the timeOut
	 * delay in seconds.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return List<WebElement>
	 */
	public List<WebElement> getAllWhenVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return elements;
	}

	/**
	 * Returns the web element specified by the provided locator once it is clickable in the browser Allows for
	 * specifying the timeOut delay in seconds.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getWhenClickable(By locator) {
		WebElement foundElement = null;
		foundElement = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.elementToBeClickable(locator));
		return foundElement;
	}

	/**
	 * Locates the object specified by the locator and clicks it as soon as that option is available Allows for
	 * specifying the timeOut delay in seconds. The element will be located again and the retried if the web element is
	 * stale
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator) {
		WebElement foundElement = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.elementToBeClickable(locator));
		try {
			foundElement.click();
		} catch (StaleElementReferenceException stale) {
			WebElement retry = (new WebDriverWait(driver, timeOut)).until(ExpectedConditions.elementToBeClickable(locator));
			retry.click();
		}
	}

	/**
	 * Returns the page source as a string
	 */
	public String pageSource() {
		return driver.getPageSource();
	}
	
	/**
	 * Refreshes the current page
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

    /**
	 * Hovers the mouse over the element found by the locator
	 * 
	 * @param locator
	 */
    public void hoverOver(By locator)
    {
        Actions action = new Actions(driver);
        action.moveToElement(getWhenVisible(locator)).build().perform();
    }

    /**
	 * Emulates a context click on the provided web element
	 * 
	 * @param target
	 */
	public void contextClickOn(By locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWhenVisible(locator)).build().perform();
	}

	/**
	 * Emulates a double click on the provided web element
	 * 
	 * @param target
	 */
	public void doubleClickOn(By locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWhenVisible(locator)).build().perform();
	}

	/**
	 * Determines if this is the current page by comparing the page titles
	 * @return
	 */
	public boolean isCurrent() {
		return checkPageTitle(pageTitle, true);
	}

	/**
	 * Switches the browser window to the one this page was shown in when it was instantiated.
	 */
	public void switchToThisPage() {
		switchWindows(pageHandle);
	}
	
	/**
	 * Checks for an open Alert box
	 * 
	 * @return
	 */
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException Ex)
		{
			return false;
		} // catch
	}

	/**
	 * Returns an Alert object if one is present
	 * 
	 * @return
	 */
	public Alert getAlert() {
		if (this.isAlertPresent()) {
			return driver.switchTo().alert();
		} else {
			return null;
		}
	}
	
	/**
	 * Field Processing Methods
	 */

	/**
	 * Accepts a locator and a value object and determines the type of field to processed and casts the object to the
	 * correct type and handles the field accordingly This function currently supports only HTML 4 input types
	 * 
	 * @param locator
	 * @param value
	 * @return
	 */
	public boolean setElementValue(By locator, Object value) {
		boolean success = false;
		
		try {
			WebElement field = this.getWhenPresent(locator);
			if (null != field) {
				switch (field.getTagName().toLowerCase().trim()) {
					case "input":
						switch (field.getAttribute("type").toLowerCase().trim()) {
							case "text":
							case "hidden":
							case "password":
								if (!(field.getText().equalsIgnoreCase((String) value) || (field.getAttribute("value").equalsIgnoreCase((String) value)))) {
									field.clear();
									field.sendKeys((String) value);
									success = true;
								}
								break;
							case "checkbox":
								if (field.isSelected() != (boolean) value) {		
									field.click();
								}
								success = true;
								break;
							case "radio":
								field.click();
								success = true;
								break;
							case "button":
							case "submit":
							case "reset":
							case "image":
								field.click();
								success = true;
								break;
							case "file":
								// TODO: add processing code for the file input type
						}
						break;
					case "textarea":
						field.clear();
						field.sendKeys((String) value);
						success = true;
						break;
					case "select":
						try {
							Select DDL = new Select(field);
							if (value.getClass().toString().contains("ArrayList")) {
								// TODO - add multiple selection logic for list boxes
								if (DDL.isMultiple()) {
									
								} else {
									success = false;
								}
							} else {
									if (System.getProperty("browserName") == "internet explorer") {
										System.out.println("Using alternate method.");
										WebElement option = field.findElement(By.xpath("./option[text() == " + value));
										option.click();
									} else {
										DDL.selectByVisibleText((String) value);
										success = true;
									}
							}
						} catch (NoSuchElementException NSEE) {
							String holder = (String) value;
							if (holder.equals("") || holder.isEmpty()) {
								success = true;
							}
							success = false;
						}
						break;
					default:
						success = false;
				}

			}
		} catch (StaleElementReferenceException sere) {
			return setElementValue(locator, value);
		} catch (TimeoutException toe) {
			success = false;
		} catch (Exception ex) {
			throw ex;			
		}

		return success;
	}

}