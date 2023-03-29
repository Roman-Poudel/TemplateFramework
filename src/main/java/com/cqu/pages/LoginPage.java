package com.cqu.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage setUserNameOrStudentId(String email) {
        driver.findElement(By.id("userNameInput")).sendKeys(email);
        return new LoginPage(driver);
    }

    public LoginPage setPassword(String password) {
        driver.findElement(By.id("passwordInput")).sendKeys(password);
        return new LoginPage(driver);
    }

    public LoginPage clickContinueButton() {
        driver.findElement(By.cssSelector("[data-testid='ButtonContent']")).click();
        // driver.findElement(By.cssSelector("ButtonContent")).click();
        return new LoginPage(driver);
    }

    public LoginPage clickLoginButton() {
        driver.findElement(By.id("submitButton")).click();
        return new LoginPage(driver);
    }

    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.cssSelector("[data-testid='PageHeaderTitle']")).isDisplayed());

        String title = driver.findElement(By.cssSelector("[data-testid='PageHeaderTitle']")).getText();
        return title;
    }

    public String loginErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.cssSelector("[id='errorText']")).isDisplayed());

        String title = driver.findElement(By.id("errorText")).getText();
        return title;
    }
}
