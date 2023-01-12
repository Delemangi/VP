package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    @FindBy(css = "input#username")
    private WebElement username;
    @FindBy(css = "input#password")
    private WebElement password;
    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage open(WebDriver driver) {
        get(driver, "/login");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static CoursesPage login(WebDriver driver, LoginPage loginPage, String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }
}
