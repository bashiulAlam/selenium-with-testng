package com.konasl.dfs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AgentManagementPage extends BasePage {
    public AgentManagementPage(WebDriver driver) {
        super(driver);
    }

    By agentManagementMenu = By.xpath("//span[normalize-space()='Agent Management']");
    By addNewAgent = By.xpath("//span[normalize-space()='Add New Agent']");
    By searchAgent = By.xpath("//span[normalize-space()='Search Agent']");

    public void expandAgentManagementMenu() {
        driver.findElement(agentManagementMenu).click();
    }

    public void openAddNewAgentMenu() {
        driver.findElement(addNewAgent).click();
    }

    public void openSearchAgentMenu() {
        driver.findElement(searchAgent).click();
    }
}
