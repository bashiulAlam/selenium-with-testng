package com.konasl.dfs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.konasl.dfs.pages.HomePage;
import com.konasl.dfs.pages.LoginPage;
import com.konasl.dfs.pages.ReadExcelFile;

public class BalanceTest extends BaseTest {

	public BalanceTest(String url) {
		super("http://qa-auth.dms.kpp.com:10900/authentication-service-provider-1.0/login");
	}
	
	@Test(priority=1, enabled=true, dataProvider="dmslogindata")
	public void checkBalance(String username, String password) throws InterruptedException {
		
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
		double balanceAmount = homepage.checkBalance();
		
		System.out.println("Balance :"+balanceAmount);
		
		Assert.assertTrue(balanceAmount>0);
	}
	
	
	

}
