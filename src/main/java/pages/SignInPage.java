package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePageObject{
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    private String pageUrl = "http://demo.hospitalrun.io/#/login";

    @FindBy(xpath = "//h2[@class='form-signin-heading']")
    private WebElement pageTitle;
    @FindBy(xpath = "//input[contains(@type,'text')]")
    private WebElement usernameField;
    @FindBy(xpath = "//input[contains(@type,'password')]")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement errorMessage;
    @FindBy(xpath = "//h2[@class='form-signin-heading']")
    private WebElement pageHeading;

    public void openPage() {
        openURL(pageUrl);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    private void login(String name, String password) {
        type(name, usernameField);
        type(password, passwordField);
        click(signInButton);
    }

    public String invalidLogin(String name, String password) {
        login(name, password);
        waitForVisibilityOf(errorMessage, 5);
        return getText(errorMessage);
    }

    public HomePage validLogin(String name, String password){
        login(name,password);
        return new HomePage(driver);
    }
}
