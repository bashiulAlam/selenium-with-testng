package com.assignment.tests;

import com.assignment.pages.InsuranceLoginPage;
import com.assignment.pages.InsuranceReviewPage;
import com.assignment.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsuranceTest extends BaseTest {
    public InsuranceTest() {
        super(Constants.INSURANCE_URL);
    }

    @Test(priority = 0)
    public void landingPageTitleCheck() {
        String title = page.getInstance(InsuranceLoginPage.class).getPageTitle();
        System.out.println("Insurance landing page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.InsuranceLandingPageTitle);
    }

    @Test(priority = 0)
    public void login() {
        page.getInstance(InsuranceLoginPage.class).openLoginPage();
        String title = page.getInstance(InsuranceLoginPage.class).getPageTitle();
        System.out.println("Insurance login page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.InsuranceLoginPageTitle);

        page.getInstance(InsuranceLoginPage.class).login(Constants.INSURANCE_USER_EMAIL, Constants.INSURANCE_PASSWORD);
    }

    @Test(priority = 1, dependsOnMethods = {"login"})
    public void setRating() throws InterruptedException {
       String message = page.getInstance(InsuranceReviewPage.class).postRatingAndReview(Constants.RATING, Constants.REVIEW);
       System.out.println("Success message : " + message);
       Assert.assertEquals(message, Constants.REVIEW_SUCCESS_MESSAGE);
    }

    @Test(priority = 2, dependsOnMethods = {"setRating"}, enabled = false)
    public void verifyReviewFeed() {
        page.getInstance(InsuranceReviewPage.class).clickContinueBtn();
        String userName = page.getInstance(InsuranceReviewPage.class).getUserName();
        System.out.println("User name : " + userName);
        Assert.assertEquals(userName, Constants.INSURANCE_USERNAME);
    }
}
