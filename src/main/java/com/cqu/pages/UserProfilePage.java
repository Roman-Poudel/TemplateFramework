package com.cqu.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserProfilePage {
    public WebDriver driver;

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public UserProfilePage clickStudyMenuu() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElement(By.cssSelector("[data-testid='NavItem']")).isDisplayed());

        WebElement link = driver.findElement(By.cssSelector("a[href='/study']"));
        link.click();
        return new UserProfilePage(driver);
    }

    public String getGPA() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElement(By.cssSelector("[data-testid='CurrentPoints']")).isDisplayed());
        return driver.findElements(By.cssSelector("[data-testid='CurrentPoints']")).get(1).getText();
    }

}
