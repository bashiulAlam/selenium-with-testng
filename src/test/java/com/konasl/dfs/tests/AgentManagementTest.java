package com.konasl.dfs.tests;

import com.konasl.dfs.model.AgentInfo;
import com.konasl.dfs.pages.*;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AgentManagementTest extends BaseTest {
    private AgentInfo agentInfo = new AgentInfo();

    public AgentManagementTest() {
        super(Constants.Url.DMS);

        agentInfo.setAccountNo(Utils.generateUniqueMobileNumber());
        agentInfo.setEmail(Utils.generateEmail());
        agentInfo.setDistributorAccountNo(Constants.AccountNumbers.DHAccountNo);
        agentInfo.setName("Hello JK Agent");
        agentInfo.setFatherName("JK Pops");
        agentInfo.setMotherName("JK Moms");
        agentInfo.setOccupation("PM Mr. X");
        agentInfo.setNid(Utils.generateNidNumber(10));
        agentInfo.setTradeLicense(Utils.generateNidNumber(10));
        agentInfo.setDob("1971-12-16");
        agentInfo.setAlternateContact(Utils.generateUniqueMobileNumber());
        agentInfo.setEmergencyContact(Utils.generateUniqueMobileNumber());
        agentInfo.setPostCode("4020");
        agentInfo.setPermanentAddress("Chorakhali");
        agentInfo.setVillage("Chorabali");
        agentInfo.setOrganizationName("Kona SL");
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
    public void expandAgentManagementMenu() {
        page.getInstance(AgentManagementPage.class).expandAgentManagementMenu();
    }

    @Test(priority = 3, dependsOnMethods = {"expandAgentManagementMenu"}, enabled = false)
    public void addNewAgent() throws InterruptedException {
        page.getInstance(AgentManagementPage.class).openAddNewAgentMenu();
        page.getInstance(RegistrationPage.class).registerAgent(agentInfo);
        Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
    }

    @Test(priority = 3, dependsOnMethods = {"expandAgentManagementMenu"}, enabled = true)
    public void searchAgent() {
        page.getInstance(AgentManagementPage.class).openSearchAgentMenu();
        page.getInstance(SearchPage.class).setAccountNo(Constants.AccountNumbers.AgentAccountNo);
        page.getInstance(SearchPage.class).clickSearchButton();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
        System.out.println("Result count : " + paginationCount);
        Assert.assertEquals(paginationCount, 1);
    }

    @Test(priority = 3, dependsOnMethods = {"searchAgent"}, enabled = true)
    public void getAgentDetails() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page.getInstance(SearchPage.class).clickDetailsButton();
        String detailsPageHeader = page.getInstance(TitleCommonPage.class).getDetailsPageHeader();
        System.out.println("Agent Details Page Header : " + detailsPageHeader);
        Assert.assertNotNull(detailsPageHeader, Constants.HeaderTitles.AgentDetailsHeaderTitle);

        /*String partnerCodeDSODetails = page.getInstance(DSOManagementPage.class).getPartnerCode();
        System.out.println("Partner Code in Details Page : " + partnerCodeDSODetails);
        Assert.assertEquals(partnerCode, partnerCodeDSODetails);*/

        String mobileAccountNo = page.getInstance(CommonPage.class).getMobileAccountNo();
        System.out.println("Mobile Account No : " + mobileAccountNo);
        Assert.assertEquals(Constants.AccountNumbers.AgentAccountNo, mobileAccountNo);
    }
}
