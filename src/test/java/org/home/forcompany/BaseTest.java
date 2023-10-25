package org.home.forcompany;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;

abstract public class BaseTest {

    private WebDriver driver = null;
    private static final Boolean CLEAR_COOKIES_AND_STORAGE = Boolean.parseBoolean(
            System.getProperty("clearCookies", "true")
    );
    private static final String browserName = System.getProperty("browser", "chrome");
    private static final String env = System.getProperty("env", "qa");

    static {
        readConfig();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        setUpBrowser();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        driver.get(System.getProperty("url"));
    }

    @AfterSuite(alwaysRun = true)
    public void testDown() {
        clearCookiesAndLocalStorage();
        driver.close();
        if (isSafari(browserName))
            System.out.println("Safari is failing on `driver.quit()`, but browser gets closed on `driver.close`, " +
                    "so considering normal.");
        else
            driver.quit();
    }

    private static void readConfig() {
        String fileName = env + ".properties";
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        try {
            System.getProperties()
                    .load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpBrowser() {
        System.out.println("Browser Name is: " + browserName);
        if (isChrome(browserName)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (isSafari(browserName)) {
            driver = new SafariDriver();
        } else {
            throw new IllegalStateException("No browser name supplied");
        }
    }

    private static boolean isChrome(String browserName) {
        return "chrome".equalsIgnoreCase(browserName);
    }

    private static boolean isSafari(String browserName) {
        return "safari".equalsIgnoreCase(browserName);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getEnvironment() {
        return env;
    }

    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }
}