package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;


public class LoginTest extends BaseTest {

    // Valid Login Test
    @Test
    public void loginTest() {
        test = extent.createTest("Login Test");

        LoginPage loginPage = new LoginPage(driver);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        test.info("Entering username");
        loginPage.enterUsername(username);

        test.info("Entering password");
        loginPage.enterPassword(password);

        test.info("Clicking on login button");
        loginPage.clickLogin();

        test.info("Verifying page title after login");
        String actualTitle = loginPage.getPageTitle();
        String expectedTitle = "OrangeHRM";
        Assert.assertEquals(actualTitle, expectedTitle, "Login failed or unexpected page title");

        test.pass("Login successful and title matched");
    }

    // Invalid Login Test using DataProvider
    @Test(dataProvider = "invalidData")
    public void invalidLoginTest(String uname, String pwd) {
        test = extent.createTest("Invalid Login Test - " + uname + " / " + pwd);

        LoginPage loginPage = new LoginPage(driver);
       
        loginPage.loginToOrangeHRM(uname, pwd);

        test.info("Entered credentials");

        String actualError = loginPage.getErrorMessage();
        Assert.assertNotNull(actualError, "Expected error message but got null — login might have succeeded.");
        Assert.assertEquals(actualError, "Invalid credentials", "Error message did not match");

        test.pass("Proper error message displayed");
        
        
        
    }


    // Invalid login data
    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][] {
            { "Admin", "wrongpass" },
            { "InvalidUser", "admin123" },
            { "test", "test123" }

        };
    }
}
