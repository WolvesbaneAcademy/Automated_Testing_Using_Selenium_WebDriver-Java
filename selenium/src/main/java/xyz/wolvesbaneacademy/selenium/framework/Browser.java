/**
 * 
 */
package xyz.wolvesbaneacademy.selenium.framework;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.*;

/**
 * @author SPConlin
 *
 */
public class Browser {
    private static String screenshotPath = "." + File.separator + "Screenshots" + File.separator;
    private static String errorScreenshotPath = "." + File.separator + "ErrorScreenshots" + File.separator;

    public static WebDriver Firefox() 
    {
        return new FirefoxDriver();
    }
    public static WebDriver Chrome()
    {
        return new ChromeDriver();
    }
    public static WebDriver IE()
    {
        return new InternetExplorerDriver();
    }
    public static WebDriver Remote(URL driverHub, String browserName, String browserVersion)
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browserName)
        {
            case "chrome":
                ChromeOptions ChrOpt = new ChromeOptions();
                ChrOpt.addArguments("test-type");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, ChrOpt);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
                break;
            case "internet explorer":
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
                break;
            default:
                FirefoxProfile ffProfile = new FirefoxProfile();
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
                capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
                break;
        }

        capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(CapabilityType.VERSION, browserVersion);
        return new RemoteWebDriver(driverHub, capabilities);
    }

    public static String getScreenshotPath() {
    	return screenshotPath;
    }
    
    public static void setScreenshotPath(String path){
    	if (path.endsWith(File.separator)) {
    		screenshotPath = path;
    	} else {
    		screenshotPath = path + File.separator;
    	}
    }

    public static String getErrorScreenshotPath() {
    	return errorScreenshotPath;
    }
    
    public static void setErrorScreenshotPath(String path){
    	if (path.endsWith(File.separator)) {
    		errorScreenshotPath = path;
    	} else {
    		errorScreenshotPath = path + File.separator;
    	}
    }

    public static void TakeScreenshot(WebDriver driver, String fileName) throws IOException
    {
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
        	FileUtils.copyFileToDirectory(scrFile, new File(screenshotPath + fileName));
        } catch (IOException io) {
        	throw io;
        }
    }

    public static void CaptureError(WebDriver driver, String fileName) throws IOException
    {
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
        	FileUtils.copyFileToDirectory(scrFile, new File(errorScreenshotPath + fileName));
        } catch (IOException io) {
        	throw io;
        }
    }

    public static Object executeJSCommand(WebDriver driver, String jsCMD)
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Object obj = jse.executeScript(jsCMD);
        return obj;
    }

}
