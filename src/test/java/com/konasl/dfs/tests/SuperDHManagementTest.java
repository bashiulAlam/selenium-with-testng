package com.konasl.dfs.tests;

import com.konasl.dfs.model.DhInfo;
import com.konasl.dfs.pages.*;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SuperDHManagementTest extends BaseTest {
    private DhInfo dhInfo = new DhInfo();

    public SuperDHManagementTest() {
        super(Constants.Url.DMS);

        dhInfo.setAccountNo(Utils.generateUniqueMobileNumber());
        dhInfo.setEmail(Utils.generateEmail());
        dhInfo.setName("Hello JK Super DH");
        dhInfo.setFatherName("JK Pops");
        dhInfo.setMotherName("JK Moms");
        dhInfo.setOccupation("PM Mr. X");
        dhInfo.setNid(Utils.generateNidNumber(10));
        dhInfo.setTradeLicense(Utils.generateNidNumber(10));
        dhInfo.setDob("1971-12-16");
        dhInfo.setAlternateContact(Utils.generateUniqueMobileNumber());
        dhInfo.setEmergencyContact(Utils.generateUniqueMobileNumber());
        dhInfo.setPostCode("4020");
        dhInfo.setPermanentAddress("Chorakhali");
        dhInfo.setVillage("Chorabali");
        dhInfo.setOrganizationName("Kona SL");
        dhInfo.setBankAccountNo(Utils.generateNidNumber(12));
        dhInfo.setDhType(Constants.DHType.SUPER);
    }

    @Test(priority = 1)
    public void doLoginTest() {
        String username = Constants.UserName.SOE;
        String password = Constants.Password.CommonPass;
        HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);

        String homeheader = homepage.getHomePageTitle();
        System.out.println("Home Header: "+ homeheader);
        Assert.assertEquals(homeheader, Constants.HeaderTitles.HomeHeader);
    }

    @Test(priority = 2, dependsOnMethods = {"doLoginTest"})
    public void expandSuperDhManagementMenu() {
        page.getInstance(SuperDHManagementPage.class).expandSuperDhMenu();
    }

    @Test(priority = 3, dependsOnMethods = {"expandSuperDhManagementMenu"}, enabled = false)
    public void addNewSuperDh() throws InterruptedException {
        page.getInstance(SuperDHManagementPage.class).addNewSuperDh();
        page.getInstance(RegistrationPage.class).registerSuperDh(dhInfo);
        Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
    }

    @Test(priority = 3, dependsOnMethods = {"expandSuperDhManagementMenu"}, enabled = true)
    public void searchSuperDh() {
        page.getInstance(SuperDHManagementPage.class).searchSuperDh();
        page.getInstance(SearchPage.class).setAccountNo(Constants.AccountNumbers.SuperDHAccountNo);
        page.getInstance(SearchPage.class).clickSearchButton();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
        System.out.println("Result count : " + paginationCount);
        Assert.assertEquals(paginationCount, 1);
    }

    @Test(priority = 3, dependsOnMethods = {"searchSuperDh"}, enabled = true)
    public void getSuperDhDetails() throws InterruptedException {
        Thread.sleep(2000);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.getInstance(SearchPage.class).clickDetailsButton();
        String detailsPageHeader = page.getInstance(TitleCommonPage.class).getDetailsPageHeader();
        System.out.println("Super Dh Details Page Header : " + detailsPageHeader);
        Assert.assertNotNull(detailsPageHeader, Constants.HeaderTitles.SuperDHDetailsHeaderTitle);

        /*String partnerCodeDSODetails = page.getInstance(DSOManagementPage.class).getPartnerCode();
        System.out.println("Partner Code in Details Page : " + partnerCodeDSODetails);
        Assert.assertEquals(partnerCode, partnerCodeDSODetails);*/

        String mobileAccountNo = page.getInstance(CommonPage.class).getMobileAccountNo();
        System.out.println("Mobile Account No : " + mobileAccountNo);
        Assert.assertEquals(Constants.AccountNumbers.SuperDHAccountNo, mobileAccountNo);
    }
}
