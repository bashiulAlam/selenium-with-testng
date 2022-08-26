package com.assignment.tests;

import com.assignment.pages.PoiPage;
import com.assignment.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class PoiTest extends BaseTest {
    public PoiTest() {
        super(Constants.Url.PORTAL_URL);
    }

    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 0)
    public void Test_001_openPoiUi() {
        String title = page.getInstance(PoiPage.class).getPoiPageTitle();
        System.out.println("Page title : " + title);
        softAssert.assertTrue(title.contains("Visualize points of interest"));
    }

    @Test(priority = 1, dependsOnMethods = {"Test_001_openPoiUi"})
    public void Test_002_checkEmbedContentWithNoLocation() {
        String content = page.getInstance(PoiPage.class).getEmbedPopUpContent();
        System.out.println("Embed pop up content : " + content);
        page.getInstance(PoiPage.class).closeEmbedPopUp();
        softAssert.assertTrue(!content.contains("lng"));
        softAssert.assertTrue(!content.contains("lat"));
    }

    @Test(priority = 1, dependsOnMethods = {"Test_001_openPoiUi"})
    public void Test_003_checkEmbedContentWithLocationAndNoCategory() throws InterruptedException {
        page.getInstance(PoiPage.class).sendLocationText("Alexanderplatz");
        Assert.assertTrue(page.getInstance(PoiPage.class).locationOptionsDisplayed() > 0);

        if (page.getInstance(PoiPage.class).locationOptionsDisplayed() > 0) {
            page.getInstance(PoiPage.class).clickSearchOption();
            Thread.sleep(3000);
            String content = page.getInstance(PoiPage.class).getEmbedPopUpContent();
            System.out.println("Embed pop up content : " + content);
            page.getInstance(PoiPage.class).closeEmbedPopUp();
            Assert.assertTrue(content.contains("lng"));
            Assert.assertTrue(content.contains("lat"));
        }
    }

    @Test(priority = 1, dependsOnMethods = {"Test_001_openPoiUi"})
    public void Test_004_checkEmbedContentWithLocationAndCategory() {
        page.getInstance(PoiPage.class).sendLocationText("Friedrichshain");
        Assert.assertTrue(page.getInstance(PoiPage.class).locationOptionsDisplayed() > 0);

        if (page.getInstance(PoiPage.class).locationOptionsDisplayed() > 0) {
            page.getInstance(PoiPage.class).clickSearchOption();
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
            page.getInstance(PoiPage.class).clickOnVehicleCategory();
            String content = page.getInstance(PoiPage.class).getEmbedPopUpContent();
            System.out.println("Embed pop up content : " + content);
            Assert.assertTrue(content.contains("lng"));
            Assert.assertTrue(content.contains("lat"));
            Assert.assertTrue(content.contains("g_vehicle"));
        }
    }
}
