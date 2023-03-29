package com.testSuites;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.cdimascio.dotenv.Dotenv;

public class TestSetup {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    private static String url;
    private static int implicitWait;
    protected static String cquUserID;
    protected static String userPassword;
    protected static WebDriver driver;
    protected String extentReportPath = "target/Reporter/TestReport.html";
    protected static ExtentReports extentReport;
    protected ExtentSparkReporter sparkReporter;

    public TestSetup() {
        // report tool setup
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(extentReportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Test Report for: XYZ ");
        extentReport.attachReporter(sparkReporter);
    }

    @BeforeAll
    public static void init() {
        // enviroment varibale set up
        var envVariables = dotenv.entries().stream().map(e -> e.getKey()).toList();
        Stream.of("BASE_URL", "USER_ID", "USER_PASSWORD", "IMPLICIT_WAIT")
                .filter(v -> !envVariables.contains(v))
                .reduce((a, b) -> a + ", " + b)
                .ifPresent(error -> {
                    throw new RuntimeException("Missing Environement variable" + error);
                });

        // intializing
        url = dotenv.get("BASE_URL");
        implicitWait = Integer.parseInt(dotenv.get("IMPLICIT_WAIT"));
        cquUserID = dotenv.get("USER_ID");
        userPassword = dotenv.get("USER_PASSWORD");
        System.out.println(cquUserID);
        System.out.println(cquUserID);
        System.out.println(url);
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
