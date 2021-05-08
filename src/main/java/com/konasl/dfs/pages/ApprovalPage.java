package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ApprovalPage extends BasePage {
    public ApprovalPage(WebDriver driver) {
        super(driver);
    }

    By approvalMenu = By.xpath("//span[normalize-space()='Approval Management']");
    By waitingForApproval = By.xpath("//span[normalize-space()='Waiting For Approval']");
    By dsoRegistration = By.xpath("//div[@id='ngb-panel-0-header']//i[@class='ft-chevron-down pull-right hide']");
    By approveDialog = By.xpath("//div[@role='dialog']");
    By approvalYesButton = By.xpath("//button[normalize-space()='Yes']");

    String approvalButtonXPath = "//tbody/tr[rownNum]/td[8]/div[1]/span[2]/button[1]";
    String tableColXPath = "//table[@class='table table-sm']//tbody//tr//td[colNum]";

    public void expandApprovalMgmt() {
        driver.findElement(approvalMenu).click();
    }

    public void openWaitingForApproval() {
        ExpectedConditions.presenceOfElementLocated(waitingForApproval);
        driver.findElement(waitingForApproval).click();
    }

    public void openDsoRegistration() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(dsoRegistration).click();
    }

    public List<String> getAccountNoColumnValues() {
        CommonPage commonPage = new CommonPage(driver);

        return commonPage.getColumnValues(tableColXPath, "5");
    }

    public void clickApproveButton(String rowNum) throws InterruptedException {
        approvalButtonXPath = approvalButtonXPath.replace("rownNum", rowNum);
        ExpectedConditions.presenceOfElementLocated(By.xpath(approvalButtonXPath));
        driver.findElement(By.xpath(approvalButtonXPath)).click();
        ExpectedConditions.presenceOfElementLocated(approveDialog);
        //Thread.sleep(3000);
    }

    public void clickApprovalYesButton() {
        driver.findElement(approvalYesButton).click();
    }
}
