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
	private static final By mnuArticles = By.linkText("ARTICLES");
	private static final By mnuVideos = By.id("menu-item-197");
	private static final By mnuCodeRepository = By.id("menu-item-179");
	private static final By mnuGitLab = By.linkText("GITLAB (ACTIVE)");
	
	/**
	 * 
	 */
	public Menu(WebDriver driver) {
		super(driver);
	}
	
	public Page clickArticles() {
		WebElement articles = this.getWhenClickable(mnuArticles);
		articles.click();
		return new Page(driver);
	}
	
	public Page clickVideos() {
		this.clickWhenReady(mnuVideos);
		return new Page(driver);
	}
	
	public Page clickGitLab() {
		this.hoverOver(mnuCodeRepository);
		this.clickWhenReady(mnuGitLab);
		return new Page(driver);
	}
}
