package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

public class SingInPage extends BasePage{
    public SingInPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[id='logininput']")
    private WebElement email;

    @FindBy(xpath = "//div[text()='Введите существующий email']")
    private WebElement emailError;
    @FindBy(css = "[id='passwordinput']")
    private WebElement password;

    @FindBy(xpath = "//div[text()='Введите пароль']")
    private WebElement errorPassword;

    @FindBy(xpath = "//button[text()=' Войти ']")
    private WebElement enter;

    @FindBy(css = "[class='w-full demo-login']")
    private WebElement demo;

    @FindBy(css = "[class='icon-close']")
    private WebElement iconClose;
    public SingInPage inputEmail(List<String> validEmails) {
        for (String validEmail : validEmails) {
            email.clear();
            email.sendKeys(validEmail);
        }
        return new SingInPage(getDriver());
    }

    public SingInPage inputPassword(String pass) {
        password.sendKeys(pass);
        return new SingInPage(getDriver());
    }
    public String incorrectEmail(List<String> invalidEmails) {
        for (String invalidEmail : invalidEmails) {
            email.clear();
            email.sendKeys(invalidEmail);
            clickEnterButtonSingInPage();
        }
        return emailError.getText();
    }
    public String errorMessagePassword() {
        clickEnterButtonSingInPage();
        return errorPassword.getText();
    }

    public SingInPage clickEnterButtonSingInPage() {
        enter.click();
        return new SingInPage(getDriver());
    }
    public SingInPage clickDemoButtonSingInPage() {
        demo.click();
        return new SingInPage(getDriver());
    }
    public DemoPage clickIconCloseDemoPage() {
        waitElementClickable(iconClose).click();
        return new DemoPage(getDriver());
    }
    public static  List<String> invalidEmails =  List.of(
            "Abc.example.com",
            "A@b@c@example.com",
            "just'n'right@example.com",
            "1234567890123456789012345678901234567890123456789012345678901234+x@example.com",
            "i_like_underscore@but_its_not_allowed_in_this_part.example.com",
            "QA[icon]CHOCOLATE[icon]@test.com"
    );
    public static List<String> validEmails  = List.of(
            "simple @example.com",
            "very.common@example.com",
            "disposable.style.email.with + symbol @example.com",
            "other.email - with - hyphen @example.com",
            "fully - qualified - domain @example.com",
            "user.name + tag + sorting @example.com",
            "example - indeed @strange -example.com",
            "test / test @test.com",
            "admin@mailserver1",
            "example @s.example",
            "avav @example.org",
            "john..doe @example.org",
            "mailhost !username @example.org",
            "@strange.example.com",
            "user % example.com @example.org",
            "user - @example.org");
    public static String randomPassword() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
}
