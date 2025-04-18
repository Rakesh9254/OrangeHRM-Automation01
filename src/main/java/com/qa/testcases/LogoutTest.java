package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutTest() {
        test = extent.createTest("Logout Test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToOrangeHRM(prop.getProperty("username"), prop.getProperty("password"));

        test.info("Logged in successfully");

        HomePage homePage = new HomePage(driver);
        test.info("Clicking logout");
        homePage.logout();

        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualTitle, "OrangeHRM", "Logout failed or didn't return to login page");

        test.pass("Logout Test Passed");
    }
}
