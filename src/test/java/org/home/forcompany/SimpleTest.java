package org.home.forcompany;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import java.util.Random;
import static page.LoginPage.randomInn;
import static page.LoginPage.randomPhoneNumber;
import static page.SingInPage.invalidEmails;
import static page.SingInPage.randomPassword;
import static page.SingInPage.validEmails;

public class SimpleTest extends BaseTest {

    private final String ADDRESS = "Street " + randomNumberHouse();
    private final String NAME_OF_COMPANY = "Company";
    private static String randomNumberHouse() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(1 + rand.nextInt(9));
        for (int i = 0; i < 2; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("success login")
    @Test
    public void correctFillingOfFields() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .inputPhoneNumber(randomPhoneNumber())
                .submitNextButton()
                .getTitle().toString();

        Assert.assertEquals(title, "Регистрация нового дилера");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("incorrect filling of fields")
    @Test
    public void getErrorMessage1() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .inputPhoneNumber(randomPhoneNumber())
                .submitNextButton()
                .getErrorMessage1();

        Assert.assertEquals(title, "Введите название компании");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("incorrect filling of fields")
    @Test
    public void getErrorMessage2() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputPhoneNumber(randomPhoneNumber())
                .submitNextButton()
                .getErrorMessage2();

        Assert.assertEquals(title, "Введите ИНН");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("incorrect filling of fields")
    @Test(groups = {"prod", "stg", "qa"})
    public void getErrorMessage3() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .submitNextButton()
                .getErrorMessage3();

        Assert.assertEquals(title, "Введите телефон");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("incorrect email in SingInPage")
    @Test
    public void incorrectFillingOfFieldsLogin() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .inputPhoneNumber(randomPhoneNumber())
                .clickEnterButton()
                .inputPassword(randomPassword())
                .incorrectEmail(invalidEmails);

        Assert.assertEquals(title, "Введите существующий email");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("incorrect password in SingInPage")
    @Test
    public void incorrectFillingOfFieldsPassword() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .inputPhoneNumber(randomPhoneNumber())
                .clickEnterButton()
                .inputEmail(validEmails)
                .errorMessagePassword();


        Assert.assertEquals(title, "Введите пароль");
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://app.skif.pro/dealer")
    @Description("correct work buttons of menu on DemoPage")
    @Test
    public void demoPageUsersTitle() {
        String title = new LoginPage(getDriver())
                .clickTypeOrganizationFolder()
                .clickTypeOrganization()
                .inputNameCompany(NAME_OF_COMPANY)
                .inputAddressCompany(ADDRESS)
                .inputINN(randomInn())
                .inputPhoneNumber(randomPhoneNumber())
                .clickEnterButton()
                .clickDemoButtonSingInPage()
                .clickIconCloseDemoPage()
                .clickUsersButtonOnMenuDemoPage()
                .usersTitle();

        Assert.assertEquals(title, "Справочник пользователя");
    }
}
