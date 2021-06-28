package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FacebookPage extends BasePage {
    public FacebookPage(WebDriver driver) {
        super(driver);
    }

    //region Landing Page
    By emailField = By.xpath("//input[@id='email']");
    By passwordField = By.xpath("//input[@id='pass']");
    By loginBtn = By.xpath("//button[normalize-space()='Log In']");

    public void typeEmail(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
    }

    public void typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public void login(String userEmail, String password) {
        typeEmail(userEmail);
        typePassword(password);
        clickLoginBtn();
    }
    //endregion

    //region HomePage
    By postField = By.xpath("//div[@class='oajrlxb2 b3i9ofy5 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 j83agx80 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x cxgpxx05 d1544ag0 sj5x9vvc tw6a2znq i1ao9s8h esuyzwwr f1sip0of lzcic4wl l9j0dhe7 abiwlrkh p8dawk7l bp9cbjyn orhb3f3m czkt41v7 fmqxjp7s emzo65vh btwxx1t3 buofh1pr idiwt2bm jifvfom9 kbf60n1y']");
    By postTextArea = By.xpath("//div[@class='gcieejh5 bn081pho humdl8nn izx4hr6d rq0escxv oo9gr5id t5a262vz o0t2es00 b1v8xokw datstx6m f530mmz5 lzcic4wl ecm0bbzt rz4wbd8a sj5x9vvc a8nywdso k4urcfbm o8yuz56k']");
    By postBtn = By.xpath("//div[@aria-label='Post']");

    public void clickPostField() {
        driver.findElement(postField).click();
    }

    public void typePost(String postText) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(postTextArea)).sendKeys(postText);
    }

    public void clickPostBtn() {
        driver.findElement(postBtn).click();
    }

    public void postStatusMessage(String postText) throws InterruptedException {
        clickPostField();
        Thread.sleep(3000);
        typePost(postText);
        clickPostBtn();
    }
    //endregion

    //region Profile
    By profileLink = By.xpath("//li[@id='AmsNShi6LJYAMio4M']//img[contains(@class,'gallery-item')]");
    By postSection = By.xpath("//div[@class='rq0escxv l9j0dhe7 du4w35lb j83agx80 pfnyh3mw i1fnvgqd gs1a9yip owycx6da btwxx1t3 d1544ag0 tw6a2znq jb3vyjys b5q2rw42 lq239pai mysgfdmx hddg9phg']");
    By post = By.cssSelector("div[class='f530mmz5 b1v8xokw o0t2es00 oo9gr5id'] div[class='kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql']");

    public void openProfile() {
        driver.findElement(profileLink).click();
    }

    public String getPostText() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(postSection));

        return driver.findElement(post).getText();
    }

    //endregion
}
