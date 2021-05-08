/**
 * 
 */
package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DSOManementPage extends BasePage{
	
	By searchDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(1) > a");
	By addDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(2) > a");
	By checkInDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(3) > a");
	

	public DSOManementPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	public String getDsoPageTitle() {
		return getPageTitle();
		
	}
	
	/**
	 * @return the Search DSO Button
	 */
	public WebElement getDsoSearchBtn() {
		return getElementBy(searchDsoBtn);
	}
	
	
	public void searchDso() {
		getDsoSearchBtn().click();
	}

	
    
	
	
	

}
