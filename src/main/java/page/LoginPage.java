package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Random;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[placeholder='Выбрать']")
    private WebElement chooseTypeOrganisation;

    @FindBy(css = "[class='el-select-dropdown__item']")
    public WebElement getChooseTypeOrganisation;

    @FindBy(css = "[placeholder='Название компании']")
    private WebElement nameCompany;

    @FindBy(css = "[placeholder='Укажите адрес']")
    private WebElement addressCompany;

    @FindBy(css = "[placeholder='Введите ваш ИНН']")
    private WebElement identificationNumberCompany;

    @FindBy(css = "[placeholder='Введите ваш номер телефона']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//span[text()='Далее']/..")
    private WebElement next;

    @FindBy(xpath = "//span[text()='Регистрация нового дилера']")
    private WebElement title;

    @FindBy(xpath = "//div[text()=' Введите название компании ']")
    private WebElement errorMessage1;

    @FindBy(xpath = "//div[text()=' Введите ИНН ']")
    private WebElement errorMessage2;

    @FindBy(xpath = "//div[text()=' Введите телефон ']")
    private WebElement errorMessage3;

    @FindBy(css = "[class='ml-1 link']")
    private WebElement enter;

    public LoginPage clickTypeOrganizationFolder() {
        waitElementVisible(chooseTypeOrganisation).click();
        return new LoginPage(getDriver());
    }

    public LoginPage clickTypeOrganization() {
        waitElementClickable(getChooseTypeOrganisation).click();
        return new LoginPage(getDriver());
    }

    public LoginPage inputNameCompany(String name) {
        nameCompany.sendKeys(name);
        return new LoginPage(getDriver());
    }

    public LoginPage inputAddressCompany(String address) {
        addressCompany.sendKeys(address);
        return new LoginPage(getDriver());
    }

    public LoginPage inputINN(String inn) {
        identificationNumberCompany.sendKeys(inn);
        return new LoginPage(getDriver());
    }

    public LoginPage inputPhoneNumber(String randomPhoneNumber) {
        phoneNumber.sendKeys(randomPhoneNumber);
        return new LoginPage(getDriver());
    }

    public LoginPage submitNextButton() {
        next.click();
        return new LoginPage(getDriver());
    }

    public String getTitle() {
        return title.getText();
    }

    public String getErrorMessage1() {
        return waitElementVisible(errorMessage1).getText();
    }

    public String getErrorMessage2() {
        return waitElementVisible(errorMessage2).getText();
    }

    public String getErrorMessage3() {
        return waitElementVisible(errorMessage3).getText();
    }

    public SingInPage clickEnterButton() {
        enter.click();
        return new SingInPage(getDriver());
    }

    public static String randomInn() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(1 + rand.nextInt(9));
        for (int i = 0; i < 9; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    public static String randomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(7);
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
}