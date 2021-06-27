package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InsuranceReviewPage extends BasePage {
    public InsuranceReviewPage(WebDriver driver) {
        super(driver);
    }

    By ratingSection = By.xpath("//div[@class='rv review-action ng-enter-element']");
    By selectElement = By.xpath("//span[normalize-space()='Select...']");
    By healthInsurance = By.xpath("//li[normalize-space()='Health Insurance']");
    By reviewTextArea = By.xpath("//textarea[@placeholder='Write your review...']");
    By submitBtn = By.cssSelector(".sbn-action");
    By continueBtn = By.xpath("//div[@class='btn rvc-continue-btn']");

    String ratingStar = "body > web-app:nth-child(1) > div:nth-child(1) > div:nth-child(2) > main:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(7) > section:nth-child(1) > div:nth-child(2) > div:nth-child(3) > review-star:nth-child(2) > div:nth-child(1) > svg:nth-child(itemNo)";

    By reviewSuccessMessage = By.cssSelector("div[class='rvc-header'] h4");
    By reviewSection = By.xpath("//section[@class='rvtab-content']");
    By reviewText = By.cssSelector(".rvtab-ci-content.with-links.text-select[data-pos='0']");
    By userName = By.cssSelector("article[class='rvtab-citem rvtab-item-user ng-enter-element'] span[class='rvtab-ci-nickname regular-font']");

    public void setRating(int rating) throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(ratingSection));

        Thread.sleep(1000);
        Actions action = new Actions(driver);
        for (int i = 1; i <= rating; i++) {
            WebElement element = driver.findElement(By.cssSelector(ratingStar.replace("itemNo", String.valueOf(i))));
            action.moveToElement(element).perform();
        }

        driver.findElement(By.cssSelector(ratingStar.replace("itemNo", String.valueOf(rating)))).click();
    }

    public void selectItemFromDropdown() {
        driver.findElement(selectElement).click();
        driver.findElement(healthInsurance).click();
    }

    public void writeReview(String review) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(reviewTextArea)).sendKeys(review);
    }

    public void clickSubmitBtn() {
        driver.findElement(submitBtn).click();
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }

    //public String postRatingAndReview(int rating, String review) throws InterruptedException {
    public void postRatingAndReview(int rating, String review) throws InterruptedException {
        setRating(rating);
        writeReview(review);
        selectItemFromDropdown();
        //Thread.sleep(3000);
        clickSubmitBtn();

        //Thread.sleep(5000);
        //return new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(reviewSuccessMessage)).getText();
    }

    public String getUserName() throws InterruptedException {
        Thread.sleep(3000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(reviewSection));

        return driver.findElement(userName).getText();
    }

    public String getReviewText() {
        return driver.findElement(reviewText).getText();
    }
}
