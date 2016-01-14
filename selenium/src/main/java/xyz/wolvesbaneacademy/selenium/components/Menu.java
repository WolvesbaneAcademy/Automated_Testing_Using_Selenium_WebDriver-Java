/**
 * 
 */
package xyz.wolvesbaneacademy.selenium.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import xyz.wolvesbaneacademy.selenium.pageobjects.Page;

/**
 * @author SPConlin
 *
 */
public class Menu extends Page {
	private static final By mnuArticles = By.linkText("Articles");
	private static final By mnuVideos = By.id("menu-item-197");
	private static final By mnuCodeRepository = By.id("menu-item-179");
	private static final By mnuGitLab = By.linkText("GitLab (Active)");
	
	/**
	 * 
	 */
	public Menu(WebDriver driver) {
		super(driver);
	}
	
	public void clickArticles() {
		WebElement articles = this.getWhenClickable(mnuArticles);
		articles.click();
	}
	
	public void clickVideos() {
		this.clickWhenReady(mnuVideos);
	}
	
	public void clickGitLab() {
		this.hoverOver(mnuCodeRepository);
		this.clickWhenReady(mnuGitLab);
	}
}
