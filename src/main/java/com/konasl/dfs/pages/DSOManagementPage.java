/**
 * 
 */
package com.konasl.dfs.pages;

import com.konasl.dfs.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DSOManagementPage extends BasePage {
	
	By searchDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(1) > a");
	By addDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(2) > a");
	By checkInDsoBtn = By.cssSelector(".sidebar-content > div.nav-container > ul > li.has-sub.open > ul > li:nth-child(3) > a");
	By dsoManagementMenu = By.xpath("//a[contains(.,'DSO Management')]");
	By searchDSOMenu = By.xpath("//span[normalize-space()='Search DSO']");
	By dsoCheckInMenu = By.xpath("//span[normalize-space()='DSO Check In']");
	By createdFromDatePicker = By.xpath("//input[@id='createdFrom']");
	By createdFromYearPicker = By.xpath("//body//app-root//select[2]");
	By createdToDatePicker = By.xpath("//input[@id='createdTo']");
	By createdFromFirstDay = By.xpath("//div[@class='btn-light'][normalize-space()='1']");
	By dsoCheckInAccountNo = By.xpath("//input[@id='visitorAccountNo']");
	By addDSOMenu = By.xpath("//span[normalize-space()='Add New DSO']");

	String tableXPath = "//table[@class='table table-sm']";
	String tableRowXPath = "//table[@class='table table-sm']//tbody//tr[rowNum]";
	String tableColXPath = "//table[@class='table table-sm']//tbody//tr//td[colNum]";
	String tableRowColXPath = "//table[@class='table table-sm']//tbody//tr[rowNum]//td[colNum]//span";
	//table/tbody/tr/td[3]/span

	/*@FindBy(how = How.XPATH, xpath = "//span[normalize-space()='DSO Management']")
	WebElement dsoManagementMenu;*/

	/*@FindBy(how = How.XPATH, xpath = "//span[normalize-space()='Search DSO']")
	WebElement searchDSOMenu;*/

	/*@FindBy(how = How.XPATH, xpath = "//input[@id='accountNo']")
	WebElement accountNoInputField;*/

	/*@FindBy(how = How.XPATH, xpath = "//button[normalize-space()='Search']")
	WebElement dsoSearchButton;*/

	public DSOManagementPage(WebDriver driver) {
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

	public void ExpandDsoManagement() throws InterruptedException {
//		Thread.sleep(5000);
//		driver.findElement(dsoManagementMenu).click();
//		searchDSOMenu.click();
		driver.findElement(dsoManagementMenu).click();
	}

	public void openSearchDSO() throws InterruptedException {
		//dsoManagementMenu.click();
		//searchDSOMenu.click();
		//Thread.sleep(5000);
		driver.findElement(searchDSOMenu).click();
	}

	public void openAddDSO() {
		driver.findElement(addDSOMenu).click();
	}

	public void openDSOCheckin() {
		driver.findElement(dsoCheckInMenu).click();
	}

	public String getPartnerCodeCellValue() {
		CommonPage commonPage = new CommonPage(driver);
		//ExpectedConditions.presenceOfElementLocated(By.xpath(tableRowColXPath));

		return commonPage.getCellValue(tableRowColXPath, "1", "3");
	}

	public List<String> getPartnerCodeColumnValues() {
		CommonPage commonPage = new CommonPage(driver);
		List<String> columnValues = commonPage.getColumnValues(tableColXPath, "3");

		return columnValues;
	}

	public void setCreatedFromDatePicker(int days) throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly', 'readonly')", driver.findElement(createdFromDatePicker));

		/*Utils utils = new Utils();
		String createdDate = utils.getCustomizeDate(days);
		System.out.println("From date set to : " + createdDate);*/
		//Thread.sleep(10000);
		/*ExpectedConditions.presenceOfElementLocated(createdFromDatePicker);
		((JavascriptExecutor) driver).executeScript("$('#createdFrom').val('"+createdDate+"')");*/

		driver.findElement(createdFromDatePicker).click();
		Select yearSelector = new Select(driver.findElement(createdFromYearPicker));
		yearSelector.selectByValue("2020");
		driver.findElement(createdFromFirstDay).click();
		//driver.findElement(createdFromDatePicker).sendKeys(createdDate);
	}

	public void setCreatedToDatePicker(int days) {
		Utils utils = new Utils();
		String createdDate = utils.getCustomizeDate(days);
		System.out.println("To date set to : " + createdDate);
		driver.findElement(createdToDatePicker).sendKeys(createdDate);
	}

	public void setDsoCheckInAccountNo(String mobileNo) {
		driver.findElement(dsoCheckInAccountNo).sendKeys(mobileNo);
	}
}
