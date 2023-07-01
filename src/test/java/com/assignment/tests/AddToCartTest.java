package com.assignment.tests;

import com.assignment.pages.CartPage;
import com.assignment.pages.CommonPage;
import com.assignment.pages.HomePage;
import com.assignment.pages.LoginPage;
import com.assignment.utils.ReadCSVFile;
import com.assignment.utils.Constants;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

	public AddToCartTest(String url) {
		super(Constants.Url.BASE_URL);
	}
	
	@Test(priority = 0, dataProvider = "loginData")
	public void loginTest(String username, String password, String status) {
		System.out.println("Logging in with username : " + username + ", password : " + password);
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);

		switch (status) {
			case "VALID":
				String homeheader = homepage.getHomePageTitle();
				System.out.println("Home Header: " + homeheader);
				Assert.assertEquals(homeheader, Constants.HeaderTitles.HOMEPAGE_HEADER);
				break;
			case "INVALID": {
				String errorMessage = page.getInstance(LoginPage.class).getInvalidLoginError();
				System.out.println("Error Message: " + errorMessage);
				Assert.assertEquals(errorMessage, Constants.ErrorMessages.INVALID_LOGIN_ERROR_MESSAGE);
				break;
			}
			case "BLANK_USERNAME": {
				String errorMessage = page.getInstance(LoginPage.class).getInvalidLoginError();
				System.out.println("Error Message: " + errorMessage);
				Assert.assertEquals(errorMessage, Constants.ErrorMessages.BLANK_USERNAME_ERROR_MESSAGE);
				break;
			}
			case "BLANK_PASSWORD": {
				String errorMessage = page.getInstance(LoginPage.class).getInvalidLoginError();
				System.out.println("Error Message: " + errorMessage);
				Assert.assertEquals(errorMessage, Constants.ErrorMessages.BLANK_PASSWORD_ERROR_MESSAGE);
				break;
			}
		}
	}

	@Test(priority = 1, dependsOnMethods = {"loginTest"})
	public void addToCartTest() {
		page.getInstance(HomePage.class).addBackpackToCart();
		Assert.assertTrue(page.getInstance(HomePage.class).isCartBadgeDisplayed());
		Assert.assertTrue(page.getInstance(CommonPage.class).isRemoveButtonVisible());

		int cartItemCount = Integer.parseInt(page.getInstance(HomePage.class).getCartItemNumber());
		System.out.println("No of items in cart : " + cartItemCount);
		Assert.assertEquals(cartItemCount, 1);
	}

	@Test(priority = 1, dependsOnMethods = {"addToCartTest"})
	public void checkoutTest() {
		page.getInstance(HomePage.class).openCart();
		String cartItemTitle = page.getInstance(CartPage.class).getItemName();
		System.out.println("Item found in cart : " + cartItemTitle);
		Assert.assertEquals(cartItemTitle, Constants.InventoryNames.BACKPACK_TITLE);
		Assert.assertTrue(page.getInstance(CommonPage.class).isRemoveButtonVisible());

		page.getInstance(CartPage.class).checkout();
		page.getInstance(CartPage.class).submitCheckoutInformation(Constants.CheckoutInfo.FIRST_NAME, Constants.CheckoutInfo.LAST_NAME, Constants.CheckoutInfo.POST_CODE);
		Assert.assertTrue(page.getInstance(CartPage.class).isTotalAmountVisible());

		page.getInstance(CartPage.class).finishCheckout();
		String checkoutSuccessMessage = page.getInstance(CartPage.class).getSuccessCheckoutMessage();
		System.out.println("Checkout success message : " + checkoutSuccessMessage);
		Assert.assertEquals(checkoutSuccessMessage, Constants.Messages.SUCCESSFUL_CHECKOUT_MESSAGE);
	}

	@Test(priority = 1, dependsOnMethods = {"loginTest"})
	public void logoutTest() {
		page.getInstance(HomePage.class).doLogout();
		Assert.assertTrue(page.getInstance(LoginPage.class).verifyLoginPage());
	}

	@DataProvider(name="loginData")
	public Object[][] TestDataFeed() throws IOException, CsvException {
		ReadCSVFile readCsv = new ReadCSVFile();
		return readCsv.getCSVContent();
	}

}
