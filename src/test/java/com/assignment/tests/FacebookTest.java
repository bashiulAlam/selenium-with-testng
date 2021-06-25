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
    public void landingPageTitleCheck() {
        String title = page.getInstance(FacebookPage.class).getPageTitle();
        System.out.println("Facebook landing page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.FacebookLandingPageTitle);
    }

    @Test(priority = 0)
    public void login() {
        page.getInstance(FacebookPage.class).login(Constants.FACEBOOK_USER_EMAIL, Constants.FACEBOOK_PASSWORD);
    }

    @Test(priority = 1, dependsOnMethods = {"login"})
    public void postStatusMessage() throws InterruptedException {
        page.getInstance(FacebookPage.class).postStatusMessage(Constants.STATUS_MESSAGE);
    }
}
