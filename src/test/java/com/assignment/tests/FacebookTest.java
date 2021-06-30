package com.assignment.tests;

import com.assignment.pages.FacebookPage;
import com.assignment.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FacebookTest extends BaseTest {

    public FacebookTest() {
        super(Constants.FACEBOOK_URL);
    }

    @Test(priority = 0)
    public void Test_001_landingPageTitleCheck() {
        String title = page.getInstance(FacebookPage.class).getPageTitle();
        System.out.println("Facebook landing page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.FacebookLandingPageTitle);
    }

    @Test(priority = 0)
    public void Test_002_login() {
        page.getInstance(FacebookPage.class).login(Constants.FACEBOOK_USER_EMAIL, Constants.FACEBOOK_PASSWORD);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_002_login"})
    public void Test_003_postStatusMessage() throws InterruptedException {
        page.getInstance(FacebookPage.class).postStatusMessage(Constants.STATUS_MESSAGE);
        Thread.sleep(3000);
        /*page.getInstance(FacebookPage.class).openProfile();
        Thread.sleep(5000);
        String post = page.getInstance(FacebookPage.class).getPostText();
        System.out.println("Facebook last post : " + post);*/
        String postedStatus = page.getInstance(FacebookPage.class).checkPostInHomePage();
        System.out.println("Posted Status : " + postedStatus);
        Assert.assertEquals(postedStatus, Constants.STATUS_MESSAGE);
    }
}
