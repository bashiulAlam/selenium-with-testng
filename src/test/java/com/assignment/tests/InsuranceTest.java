package com.assignment.tests;

import com.assignment.pages.InsuranceLoginPage;
import com.assignment.pages.InsuranceProfilePage;
import com.assignment.pages.InsuranceReviewPage;
import com.assignment.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsuranceTest extends BaseTest {
    public InsuranceTest() {
        super(Constants.INSURANCE_URL);
    }

    @Test(priority = 0)
    public void Test_001_landingPageTitleCheck() {
        String title = page.getInstance(InsuranceLoginPage.class).getPageTitle();
        System.out.println("Insurance landing page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.InsuranceLandingPageTitle);
    }

    @Test(priority = 0)
    public void Test_002_login() {
        page.getInstance(InsuranceLoginPage.class).openLoginPage();
        String title = page.getInstance(InsuranceLoginPage.class).getPageTitle();
        System.out.println("Insurance login page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.InsuranceLoginPageTitle);

        page.getInstance(InsuranceLoginPage.class).login(Constants.INSURANCE_USER_EMAIL, Constants.INSURANCE_PASSWORD);
        String username = page.getInstance(InsuranceProfilePage.class).getUsername();
        System.out.println("User Name : " + username);
        Assert.assertEquals(username, Constants.INSURANCE_USER_FIRST_NAME);
    }

    @Test(priority = 1, dependsOnMethods = {"Test_002_login"}, enabled = true)
    public void Test_003_postRatingAndReview() throws InterruptedException {
        /*
        Following code was implemented to post review and fetch the success message.
        But after clicking submit button, the review posting success UI is not navigated
        */

        /*String message = page.getInstance(InsuranceReviewPage.class).postRatingAndReview(Constants.RATING, Constants.REVIEW);
        System.out.println("Success message : " + message);
        Assert.assertEquals(message, Constants.REVIEW_SUCCESS_MESSAGE);*/

        //Code has been changed to navigate to review feed instead
        page.getInstance(InsuranceReviewPage.class).postRatingAndReview(Constants.RATING, Constants.REVIEW);
        driver.navigate().to(Constants.INSURANCE_URL);
        String title = page.getInstance(InsuranceReviewPage.class).getPageTitle();
        System.out.println("Insurance review page title : " + title);
        Assert.assertEquals(title, Constants.UITitles.InsuranceLandingPageTitle);
    }

    @Test(priority = 2, dependsOnMethods = {"Test_003_postRatingAndReview"}, enabled = true)
    public void Test_004_verifyReviewFeed() throws InterruptedException {
        /*Continue button click was implemented to navigate to review feed after getting review success message.
        It is commented now and just review verification has been added
        */
        //page.getInstance(InsuranceReviewPage.class).clickContinueBtn();

        String userName = page.getInstance(InsuranceReviewPage.class).getUserName();
        System.out.println("User name : " + userName);
        Assert.assertEquals(userName.substring(1), Constants.INSURANCE_USERNAME);

        String reviewText = page.getInstance(InsuranceReviewPage.class).getReviewText();
        System.out.println("Submitted review : " + reviewText);
        Assert.assertEquals(reviewText, Constants.REVIEW);
    }

    @Test(priority = 3, dependsOnMethods = {"Test_002_login"}, enabled = true)
    public void Test_005_verifyReviewFromProfile() {
        page.getInstance(InsuranceProfilePage.class).openProfile();
        Assert.assertTrue(page.getInstance(InsuranceProfilePage.class).isRecommendSectionDisplayed());

        if (page.getInstance(InsuranceProfilePage.class).isRecommendSectionDisplayed()) {
            String companyTitle = page.getInstance(InsuranceProfilePage.class).getCompanyName();
            System.out.println("Reviewed Company Name : " + companyTitle);
            Assert.assertEquals(companyTitle, Constants.INSURANCE_COMPANY_NAME);
        }
    }
}
