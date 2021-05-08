/**
 * 
 */
package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author sazzad.mohon
 *
 */
public class LoginPage extends BasePage {
	private By username = By.id("username");
	private By password = By.id("password");
	private By loginbtn = By.id("login_button");
	private By forgotpasswordbtn = By.xpath("//*[@id=\"login_form\"]/form/div[3]/a");
	
	private By inValidLogin = By.xpath("//*[@id=\"login_form\"]/form/div[3]/div[1]");
	
	private By logOutWrapperBtn = By.id("dropdownBasic3");
	private By logOutBtn = By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/div/a[3]");
	private By loginWrapperbtn = By.xpath("//*[@id=\"navbarSupportedContent\"]/a");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return the username
	 */
	public WebElement getUserName() {
		return getElementBy(username);
	}

	
	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return getElementBy(password);
	}

	
	
	
	/**
	 * @return the loginbtn
	 */
	public WebElement getLoginbtn() {
		return getElementBy(loginbtn);
	}

	public WebElement getLogoutWrapperBtn() {
		return getElementBy(logOutWrapperBtn);
	}

	public WebElement getLogoutBtn() {
		return getElementBy(logOutBtn);
	}
	
	public WebElement getLoginWrappertBtn() {
		return getElementBy(loginWrapperbtn);
	}

	/**
	 * @return the forgotpasswordbtn
	 */
	public By getForgotpasswordbtn() {
		return forgotpasswordbtn;
	}
	
	
	/**
	 * @return the forgotpasswordbtn
	 */
	public String getInvalidLoginError() {
		return getElementBy(inValidLogin).getText();
	}
	
	
	public String getLoginPageTitle() {
		return getPageTitle();
		
	}
	
	
	public HomePage doLogin(String username, String password) {
		getUserName().sendKeys(username);
		getPassword().sendKeys(password);
		getLoginbtn().click();

		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
		
		// Return next landing page class object
		return getInstance(HomePage.class);
	}
	
	/**
	 * Method Overloading
	 * @return
	 */
	public void doLogin() {
		getUserName().sendKeys("");
		getPassword().sendKeys("");
		getLoginbtn().click();
		
	}
	
	// username: admin@laototo.pp.com
	public void doLogin(String userCred) {
		if(userCred.contains("username")) {
			getUserName().sendKeys(userCred.split(":")[1].trim());
		}
		else if(userCred.contains("password")) {
			getPassword().sendKeys(userCred.split(":")[1].trim());
		}
		
	}
	
	public void doLogout() {
		getLogoutWrapperBtn().click();
		getLogoutBtn().click();
		//getLoginWrappertBtn().click();
	}
}
