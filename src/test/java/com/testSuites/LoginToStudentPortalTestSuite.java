package com.testSuites;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cqu.pages.LoginPage;

public class LoginToStudentPortalTestSuite extends TestSetup {
    @Test
    @BeforeEach
    public void loginToStudentPortalSuccess() {
        var loginPage = new LoginPage(driver);
        loginPage.clickContinueButton()
                .setUserNameOrStudentId(cquUserID)
                .setPassword(userPassword)
                .clickLoginButton();
        // assertEquals("GOOD EVENING ROMAN!", loginPage.getPageTitle().toString());

    }

    @Test
    public void loginToStudentPortalFailure() {
        var loginPage = new LoginPage(driver);
        loginPage.clickContinueButton()
                .setUserNameOrStudentId("any@mail.com")
                .setPassword("wrongPassword@13")
                .clickLoginButton();
        assertEquals("Incorrect user ID or password. Type the correct user ID and password, and try again.",
                loginPage.loginErrorMessage());
        assertEquals("", "");
    }
}
