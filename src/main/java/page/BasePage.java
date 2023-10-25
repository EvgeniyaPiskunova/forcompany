package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {

    private final WebDriver driver;
    private static final int EXPLICIT_WAIT = 10;

    public WebDriver getDriver() {
        return this.driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitElementVisible(WebElement element) {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitElementClickable(WebElement element) {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }
}
