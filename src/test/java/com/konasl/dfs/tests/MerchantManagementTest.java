package com.konasl.dfs.tests;

import com.konasl.dfs.model.MerchantInfo;
import com.konasl.dfs.pages.*;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MerchantManagementTest extends BaseTest {
    private MerchantInfo merchantInfo = new MerchantInfo();

    public MerchantManagementTest() {
        super(Constants.Url.DMS);

        merchantInfo.setAccountNo(Utils.generateUniqueMobileNumber());
        merchantInfo.setMerchantName("Hello JK Merchant");
        merchantInfo.setEmail(Utils.generateEmail());
        merchantInfo.setName("Hello JK Merchant");
        merchantInfo.setFatherName("JK Pops");
        merchantInfo.setMotherName("JK Moms");
        merchantInfo.setOccupation("PM Mr. X");
        merchantInfo.setNid(Utils.generateNidNumber(10));
        merchantInfo.setTradeLicense(Utils.generateNidNumber(10));
        merchantInfo.setDob("1971-12-16");
        merchantInfo.setAlternateContact(Utils.generateUniqueMobileNumber());
        merchantInfo.setEmergencyContact(Utils.generateUniqueMobileNumber());
        merchantInfo.setPostCode("4020");
        merchantInfo.setPermanentAddress("Chorakhali");
        merchantInfo.setRegisteredAddress("Chorakhali");
        merchantInfo.setVillage("Chorabali");
        merchantInfo.setOrganizationName("Kona SL");
        merchantInfo.setTypeOfBusiness("Back Stabbing");
        merchantInfo.setBankAccountNo(Utils.generateNidNumber(12));
        merchantInfo.setIntroducerName("MD");
        merchantInfo.setIntroducerAccount(Utils.generateUniqueMobileNumber());
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
    public void expandMerchantManagementMenu() {
        page.getInstance(MerchantManagementPage.class).expandMerchantManagementMenu();
    }

    @Test(priority = 3, dependsOnMethods = {"expandMerchantManagementMenu"}, enabled = false)
    public void addNewMerchant() throws InterruptedException {
        page.getInstance(MerchantManagementPage.class).openAddNewMerchant();
        page.getInstance(RegistrationPage.class).registerMerchant(merchantInfo);
        Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
    }

    @Test(priority = 3, dependsOnMethods = {"expandMerchantManagementMenu"}, enabled = true)
    public void searchMerchant() {
        page.getInstance(MerchantManagementPage.class).openSearchMerchant();
        page.getInstance(SearchPage.class).setAccountNo(Constants.AccountNumbers.MerchantAccountNo);
        page.getInstance(SearchPage.class).clickSearchButton();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
        System.out.println("Result count : " + paginationCount);
        Assert.assertEquals(paginationCount, 1);
    }

    @Test(priority = 3, dependsOnMethods = {"searchMerchant"}, enabled = true)
    public void getMerchantDetails() throws InterruptedException {
        Thread.sleep(2000);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.getInstance(SearchPage.class).clickDetailsButton();
        String detailsPageHeader = page.getInstance(TitleCommonPage.class).getMerchantDetailsPageHeader();
        System.out.println("Merchant Details Page Header : " + detailsPageHeader);
        Assert.assertNotNull(detailsPageHeader, Constants.HeaderTitles.MerchantDetailsHeaderTitle);

        /*String partnerCodeDSODetails = page.getInstance(DSOManagementPage.class).getPartnerCode();
        System.out.println("Partner Code in Details Page : " + partnerCodeDSODetails);
        Assert.assertEquals(partnerCode, partnerCodeDSODetails);*/

        String mobileAccountNo = page.getInstance(CommonPage.class).getMerchantMobileAccountNo();
        System.out.println("Mobile Account No : " + mobileAccountNo);
        Assert.assertEquals(Constants.AccountNumbers.MerchantAccountNo, mobileAccountNo);
    }
}
