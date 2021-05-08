package com.konasl.dfs.tests;

import com.konasl.dfs.model.DsoInfo;
import com.konasl.dfs.pages.*;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DSOManagementTest extends BaseTest {
	private DsoInfo dsoInfo = new DsoInfo();

	public DSOManagementTest(String url) {
		super(Constants.Url.DMS);

		dsoInfo.setAccountNo(Utils.generateUniqueMobileNumber());
		dsoInfo.setEmail(Utils.generateEmail());
		dsoInfo.setDistributorAccountNo(Constants.AccountNumbers.DHAccountNo);
		dsoInfo.setName("Hello JK");
		dsoInfo.setFatherName("JK Pops");
		dsoInfo.setMotherName("JK Moms");
		dsoInfo.setOccupation("PM Mr. X");
		dsoInfo.setNid(Utils.generateNidNumber(10));
		dsoInfo.setTradeLicense(Utils.generateNidNumber(10));
		dsoInfo.setDob("1971-12-16");
		dsoInfo.setAlternateContact(Utils.generateUniqueMobileNumber());
		dsoInfo.setEmergencyContact(Utils.generateUniqueMobileNumber());
		dsoInfo.setPostCode("4020");
		dsoInfo.setPermanentAddress("Chorakhali");
		dsoInfo.setVillage("Chorabali");
		dsoInfo.setOrganizationName("Kona SL");
	}

	private String partnerCode;

	@Test(priority = 1/*, dataProvider = "logindata"*/)
	public void doLoginTest() throws Exception {

//		String title = page.getInstance(LoginPage.class).getLoginPageTitle();
//		System.out.println("title :"+title);
//		Assert.assertEquals(title, Constants.UITitles.HomePageTitle);
		String username = Constants.UserName.SOE;
		String password = Constants.Password.CommonPass;
		boolean isValid = true;
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);

		if(isValid) {
			String homeheader = homepage.getHomePageTitle();
			System.out.println("Home Header: " + homeheader);
			Assert.assertEquals(homeheader, Constants.HeaderTitles.HomeHeader);
			//page.getInstance(LoginPage.class).doLogout();
		} else {
			String errorMessage  = page.getInstance(LoginPage.class).getInvalidLoginError();
			System.out.println("Error Message: "+ errorMessage);
			Assert.assertEquals(errorMessage, Constants.ErrorMessages.LogInFailErrorMessage);
		}

	}

	@Test(priority = 2, dependsOnMethods = {"doLoginTest"})
	public void expandDSOManagementMenu() throws InterruptedException {
		page.getInstance(DSOManagementPage.class).ExpandDsoManagement();
	}

	@Test(priority = 2, dependsOnMethods = {"doLoginTest"}, enabled = false)
	public void logOut() {
		page.getInstance(LoginPage.class).doLogout();
	}

	@Test(priority = 3, dependsOnMethods = {"expandDSOManagementMenu"}, enabled = false)
	public void addNewDso() throws InterruptedException {
		page.getInstance(DSOManagementPage.class).openAddDSO();
		page.getInstance(RegistrationPage.class).registerDso(dsoInfo);
		Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = {"expandDSOManagementMenu", "addNewDso"}, enabled = true)
	public void searchDso() throws InterruptedException {
		page.getInstance(DSOManagementPage.class).openSearchDSO();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//page.getInstance(DSOManagementPage.class).setAccountNo(Constants.AccountNumbers.DSOAccountNo);
		page.getInstance(SearchPage.class).setAccountNo(dsoInfo.getAccountNo());
		page.getInstance(SearchPage.class).clickSearchButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
		System.out.println("Result count : " + paginationCount);
		//partnerCode = page.getInstance(DSOManagementPage.class).getPartnerCodeCellValue();
		//System.out.println("Partner Code : " + partnerCode);
		Assert.assertEquals(paginationCount, 1);

		/*List<String> partnerCodeColumnVals = page.getInstance(DSOManagementPage.class).getPartnerCodeColumnValues();
		System.out.println("List size : " + partnerCodeColumnVals.size());
		System.out.println("value : " + partnerCodeColumnVals.get(0));*/
	}

	@Test(priority = 3, dependsOnMethods = {"searchDSO"}, enabled = true)
	public void getDsoDetails() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		page.getInstance(SearchPage.class).clickDetailsButton();
		String dsoDetailsPageHeader = page.getInstance(TitleCommonPage.class).getDetailsPageHeader();
		System.out.println("DSO Details Page Header : " + dsoDetailsPageHeader);
		Assert.assertNotNull(dsoDetailsPageHeader, Constants.HeaderTitles.DSODetailsHeaderTitle);

		String partnerCodeDSODetails = page.getInstance(CommonPage.class).getPartnerCode();
		System.out.println("Partner Code in Details Page : " + partnerCodeDSODetails);
		Assert.assertEquals(partnerCode, partnerCodeDSODetails);

		String mobileAccountNo = page.getInstance(CommonPage.class).getMobileAccountNo();
		System.out.println("Mobile Account No : " + mobileAccountNo);
		Assert.assertEquals(Constants.AccountNumbers.DSOAccountNo, mobileAccountNo);
	}

	@Test(priority = 4, dependsOnMethods = {"expandDSOManagementMenu"}, enabled = false)
	public void dsoCheckIn() throws InterruptedException {
		page.getInstance(DSOManagementPage.class).openDSOCheckin();
		Assert.assertFalse(page.getInstance(SearchPage.class).checkSearchButtonEnabled());

		page.getInstance(DSOManagementPage.class).setDsoCheckInAccountNo(Constants.AccountNumbers.DSOAccountNo);
		page.getInstance(DSOManagementPage.class).setCreatedFromDatePicker(-365);

		//page.getInstance(DSOManagementPage.class).setCreatedToDatePicker(0);

		Assert.assertTrue(page.getInstance(SearchPage.class).checkSearchButtonEnabled());
		if (page.getInstance(SearchPage.class).checkSearchButtonEnabled()) {
			page.getInstance(SearchPage.class).clickSearchButton();
			int paginationCount = page.getInstance(CommonPage.class).getPaginationCount();
			System.out.println("Result count : " + paginationCount);
			Assert.assertTrue(paginationCount > 0);
		}
	}
}
