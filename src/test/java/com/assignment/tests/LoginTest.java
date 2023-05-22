package com.assignment.tests;

import com.assignment.pages.HomePage;
import com.assignment.pages.LoginPage;
import com.assignment.utils.ReadCSVFile;
import com.assignment.utils.Constants;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

	public LoginTest(String url) {
		super(Constants.Url.BASE_URL);
	}
	
	@Test(priority = 1, dataProvider = "loginData")
	public void doLoginAndLogoutTest(String username, String password, String isValid) {
		System.out.println("Logging with username : " + username);
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
		
		if(isValid.equals("TRUE")) {
			String homeheader = homepage.getHomePageTitle();
			System.out.println("Home Header: "+ homeheader);
			Assert.assertEquals(homeheader, Constants.HeaderTitles.HOMEPAGE_HEADER);

			page.getInstance(HomePage.class).doLogout();
			Assert.assertTrue(page.getInstance(LoginPage.class).verifyLoginPage());
		} else {
			String errorMessage  = page.getInstance(LoginPage.class).getInvalidLoginError();
			System.out.println("Error Message: "+ errorMessage);
			Assert.assertEquals(errorMessage, Constants.ErrorMessages.INVALID_LOGIN_ERROR_MESSAGE);
		}
	}

	@DataProvider(name="loginData")
	public Object[][] TestDataFeed() throws IOException, CsvException {
		ReadCSVFile readCsv = new ReadCSVFile();
		return readCsv.getCSVContent();
	}

}
