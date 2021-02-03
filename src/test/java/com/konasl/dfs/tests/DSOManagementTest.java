package com.konasl.dfs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.konasl.dfs.pages.DSOManementPage;
import com.konasl.dfs.pages.HomePage;
import com.konasl.dfs.pages.LoginPage;
import com.konasl.dfs.pages.ReadExcelFile;

public class DSOManagementTest extends BaseTest {

	public DSOManagementTest(String url) {
		super("http://qa-auth.dms.kpp.com:10900/authentication-service-provider-1.0/login");
	}
	
	@Test(priority=1, enabled=true, dataProvider="dmslogindata")
	public void searchDso(String username, String password) throws InterruptedException {
		
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
		DSOManementPage dsoManagementPage =  homepage.openDsoManagementPage();
		dsoManagementPage.searchDso();
		Thread.sleep(1000);
		
		String title = dsoManagementPage.getDsoPageTitle();
		
		System.out.println("title :"+title);
		Assert.assertEquals(title, "DSO Information");
		
	}
	
	
	

}
