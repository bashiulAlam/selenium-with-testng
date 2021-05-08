package com.konasl.dfs.tests;

import com.konasl.dfs.model.DhInfo;
import com.konasl.dfs.pages.*;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DHManagementTest extends BaseTest {
    private DhInfo dhInfo = new DhInfo();
    
    public DHManagementTest() {
        super(Constants.Url.DMS);

        dhInfo.setAccountNo(Utils.generateUniqueMobileNumber());
        dhInfo.setEmail(Utils.generateEmail());
        dhInfo.setName("Hello JK DH");
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
        dhInfo.setDhType(Constants.DHType.REGULAR);
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
    public void expandDhManagementMenu() {
        page.getInstance(DHManagementPage.class).expandDhManagementMenu();
    }

    @Test(priority = 3, dependsOnMethods = {"expandDhManagementMenu"}, enabled = false)
    public void addNewDh() throws InterruptedException {
        page.getInstance(DHManagementPage.class).openAddNewDh();
        page.getInstance(RegistrationPage.class).registerDh(dhInfo);
        Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
    }

    @Test(priority = 3, dependsOnMethods = {"expandDhManagementMenu"}, enabled = true)
    public void searchDh() {
        page.getInstance(DHManagementPage.class).openSearchDh();
        page.getInstance(SearchPage.class).setAccountNo(Constants.AccountNumbers.DHAccountNo);
        page.getInstance(SearchPage.class).clickSearchButton();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
        System.out.println("Result count : " + paginationCount);
        Assert.assertEquals(paginationCount, 1);
    }

    @Test(priority = 3, dependsOnMethods = {"searchDh"}, enabled = true)
    public void getDhDetails() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.getInstance(SearchPage.class).clickDetailsButton();
        String detailsPageHeader = page.getInstance(TitleCommonPage.class).getDetailsPageHeader();
        System.out.println("Dh Details Page Header : " + detailsPageHeader);
        Assert.assertNotNull(detailsPageHeader, Constants.HeaderTitles.DHDetailsHeaderTitle);

        /*String partnerCodeDSODetails = page.getInstance(DSOManagementPage.class).getPartnerCode();
        System.out.println("Partner Code in Details Page : " + partnerCodeDSODetails);
        Assert.assertEquals(partnerCode, partnerCodeDSODetails);*/

        String mobileAccountNo = page.getInstance(CommonPage.class).getMobileAccountNo();
        System.out.println("Mobile Account No : " + mobileAccountNo);
        Assert.assertEquals(Constants.AccountNumbers.DHAccountNo, mobileAccountNo);
    }
}
