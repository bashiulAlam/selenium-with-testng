package com.konasl.dfs.tests;

import com.konasl.dfs.pages.GooglePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.konasl.dfs.pages.HomePage;
import com.konasl.dfs.pages.LoginPage;
import com.konasl.dfs.pages.ReadExcelFile;

public class BalanceTest extends BaseTest {

	public BalanceTest(String url) {
		super("https://www.google.com/?hl=en");
	}

	@Test(priority=1, enabled=true)
	public void search() throws InterruptedException {
		page.getInstance(GooglePage.class).search("Lamborghini");
		Thread.sleep(1000);
		long count = page.getInstance(GooglePage.class).resultCount();
		Assert.assertTrue(count > 0);
	}

	@Test(priority=1, enabled=false, dataProvider="dmslogindata")
	public void checkBalance(String username, String password) throws InterruptedException {
		
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
		double balanceAmount = homepage.checkBalance();
		
		System.out.println("Balance :"+balanceAmount);
		
		Assert.assertTrue(balanceAmount>0);
	}
	
	
	

}
