package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DemoPage extends BasePage {

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[text()='Пользователи']//parent::div//child::button")
    private WebElement users;

    @FindBy(xpath = "//h1[text()=' Справочник пользователя ']")
    private WebElement title;

    public DemoPage clickUsersButtonOnMenuDemoPage() {
        waitElementClickable(users).click();
        return new DemoPage(getDriver());
    }
    public String usersTitle() {
        return waitElementVisible(title).getText();
    }

}