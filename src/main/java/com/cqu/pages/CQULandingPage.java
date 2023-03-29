package com.cqu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CQULandingPage {
    WebDriver driver;

    public CQULandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public CQULandingPage clickMyCQUStudentPortalButton() {
        driver.findElement(By.className("button")).click();
        // driver.findElement(By.linkText("MyCQU Student Portal")).click();
        return new CQULandingPage(driver);
    }
}
