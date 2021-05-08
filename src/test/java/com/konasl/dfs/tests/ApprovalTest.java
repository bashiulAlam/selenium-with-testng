package com.konasl.dfs.tests;

import com.konasl.dfs.pages.ApprovalPage;
import com.konasl.dfs.pages.CommonPage;
import com.konasl.dfs.pages.HomePage;
import com.konasl.dfs.pages.LoginPage;
import com.konasl.dfs.utils.Constants;
import com.konasl.dfs.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ApprovalTest extends BaseTest {
    public ApprovalTest() {
        super(Constants.Url.DMS);
    }

    private List<String> accountNumbers = new ArrayList<String>();
    String dsoAccountForApproval = "01543953700";

    @Test(priority = 1)
    public void doLoginTest() {
        String username = Constants.UserName.TM;
        String password = Constants.Password.CommonPass;
        HomePage homepage = page.getInstance(LoginPage.class).doLogin(username, password);
    }

    @Test(priority = 2, dependsOnMethods = {"doLoginTest"})
    public void expandApprovalMenu() {
        page.getInstance(ApprovalPage.class).expandApprovalMgmt();
    }

    @Test(priority = 2, dependsOnMethods = {"expandApprovalMenu"})
    public void openApprovalAwaitedDsoRegistration() throws InterruptedException {
        page.getInstance(ApprovalPage.class).openWaitingForApproval();
        page.getInstance(ApprovalPage.class).openDsoRegistration();

        accountNumbers = page.getInstance(ApprovalPage.class).getAccountNoColumnValues();
        System.out.println("List size : " + accountNumbers.size());
        /*for (int i = 0; i < accountNumbers.size(); i++)
            System.out.println(accountNumbers.get(i));*/
        Assert.assertTrue(accountNumbers.size() > 0);
    }

    @Test(priority = 2, dependsOnMethods = {"openApprovalAwaitedDsoRegistration"}, enabled = false)
    public void approveDso() throws InterruptedException {
        int accountNoIndex = Utils.getMatchedAccountNumberIndex(accountNumbers, dsoAccountForApproval);
        Assert.assertTrue(accountNoIndex > 0);

        if (accountNoIndex > 0) {
            page.getInstance(ApprovalPage.class).clickApproveButton(String.valueOf(accountNoIndex));
            page.getInstance(ApprovalPage.class).clickApprovalYesButton();

            Assert.assertTrue(page.getInstance(CommonPage.class).checkSuccessToastDisplayed());
        }
    }
}
