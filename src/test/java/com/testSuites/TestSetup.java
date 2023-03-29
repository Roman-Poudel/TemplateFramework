package com.testSuites;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
//import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//@ExtendWith(AfterEachProcessor.class)
@Execution(ExecutionMode.CONCURRENT)
public class TestSetup {
    private String url = "https://my.cqu.edu.au/";
    private int implicitWait = 10;
    protected static WebDriver driver;
    protected String extentReportPath = "target/Reporter/TestReport.html";
    protected static ExtentReports extentReport;
    protected ExtentSparkReporter sparkReporter;

    public TestSetup() {
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(extentReportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Test Report for: XYZ ");
        extentReport.attachReporter(sparkReporter);
    }

    @BeforeEach
    public void setupDriver(TestInfo testInfo) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox",
                "--disable-extensions",
                "--disable-notifications",
                "--remote-debigging-port-922",
                "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        System.out.println(driver);
        System.setProperty("webdriver.chrome.driver", "/Applications");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.navigate().to(url);
        extentReport.createTest(testInfo.getDisplayName());
    }

    @AfterAll
    public static void driverTearDown() {
        driver.close();
        extentReport.flush();
    }

    protected <T> T open(Class<T> clazz) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return clazz.getConstructor(WebDriver.class).newInstance(driver);

    }
}
