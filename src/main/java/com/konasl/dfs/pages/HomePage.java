/**
 * 
 */
package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	By checkBalanceBtn = By.xpath("/html/body/app-root/app-full-layout/div/div[1]/app-sidebar/div[2]/div/span/div");
	By blanceContainerDiv = By.cssSelector("div.balance-check-box user-balance div");
	By dsoMainBtn = By.cssSelector("div.sidebar-content > div.nav-container > ul > li:nth-child(1) > a");
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	
	public String getHomePageTitle() {
		return getPageTitle();
		
	}
	
	/**
	 * @return the Balance Button
	 */
	public WebElement getBalanceBtn() {
		return getElementBy(checkBalanceBtn);
	}
	
	/**
	 * @return the DSO Management Main Button
	 */
	public WebElement getDsoManagementBtn() {
		return getElementBy(dsoMainBtn);
	}
	
	
	public DSOManagementPage openDsoManagementPage() {
		
		getDsoManagementBtn().click();
		
		// Return next landing page class object
		return getInstance(DSOManagementPage.class);
	}
	
    
	/**
	 * @return the Balance Amount
	 */
	public double getBalanceAmount() {
		String balance = getElementBy(blanceContainerDiv).getText();
		System.out.println(balance);
		balance = balance.replaceAll("[^0-9.]", "");  
		return Double.parseDouble(balance);
	}
	
	
	public double checkBalance() throws InterruptedException {
		Thread.sleep(1000);
		getBalanceBtn().click();
		Thread.sleep(1500);
		return getBalanceAmount();
	}

}
