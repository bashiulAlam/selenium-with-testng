package com.konasl.dfs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.konasl.dfs.pages.HomePage;
import com.konasl.dfs.pages.LoginPage;
import com.konasl.dfs.pages.ReadExcelFile;

public class LoginTest extends BaseTest {

	public LoginTest(String url) {
		super("http://qa-auth.dms.kpp.com:10900/authentication-service-provider-1.0/login");
	}
	
	@Test(priority=1, enabled=false)
	public void verifyLoginPageTitleTest() {
		String title = page.getInstance(LoginPage.class).getLoginPageTitle();
		System.out.println("title :"+title);
		Assert.assertEquals(title, "নগদ | Login Page");
	}
	
	
	
	
	@Test(priority=2, dataProvider="logindata")
	public void doLoginTest(String username, String password, boolean isValid) throws Exception{
		
//		String title = page.getInstance(LoginPage.class).getLoginPageTitle();
//		System.out.println("title :"+title);
//		Assert.assertEquals(title, "নগদ | Login Page");
		
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
		
		if(isValid) {
			String homeheader = homepage.getHomePageTitle();
			System.out.println("Home Header: "+homeheader);
			Assert.assertEquals(homeheader, "নগদ");
			page.getInstance(LoginPage.class).doLogout();
		}else {
			String errorMessage  = page.getInstance(LoginPage.class).getInvalidLoginError();
			System.out.println("Error Message: "+errorMessage);
			Assert.assertEquals(errorMessage, "Invalid Username or Password. Please try again.");
		}
		
	}
	
	
	
	@DataProvider(name="logindata")
	public Object[][] TestDataFeed(){
		ReadExcelFile readExcelObj = new ReadExcelFile("./Datafiles/users.xlsx");
		
		int sheet_index = 0;
		
		int rows = readExcelObj.getRowCount(sheet_index);
		
		Object[][] credentials = new Object[rows][3];
		
		for (int i = 0; i < rows; i++) {
			credentials[i][0] = readExcelObj.getData(sheet_index,i,0);
			credentials[i][1] = readExcelObj.getData(sheet_index,i,1);
			credentials[i][2] = readExcelObj.getBoolData(sheet_index,i,2);
		}
		
		return credentials;
	}

}
