package pages;

import base.TestUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class BasePageObject {
    protected WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*Open page with given URL */
    protected void openURL(String url) {
        driver.get(url);
    }


    /*Find multiple elements with given locator */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements( locator);
    }

    /*Click on the element with given locator whet it s visible */
    protected void click(WebElement element) {
        waitForVisibilityOf(element, 15);
        element.click();
    }


    /*Click on the element with given locator whet it s visible */
    protected void type(String text, WebElement element) {
        click(element);
        waitForVisibilityOf(element, 5);
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        waitForVisibilityOf(element);
        return element.getText();
    }

    /*
    Wait for given number of seconds for element with given locator to be visible on the page
    */

    protected void waitForVisibilityOf(WebElement element, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                wait(ExpectedConditions.visibilityOf(element), timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
            }
        }
    }

    /*
        Wait for specific  ExpectedCondition for the given amount of time in seconds
         */
    private void wait(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 5;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    protected boolean alertElementsIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
                return true;
            }
        } catch (TimeoutException ex) {
            return false;
        }
        return false;
    }

    protected void selectOptionFromDropdown(String s, WebElement element, By patientSuggestionListOfOptions) {
        waitForVisibilityOf(element, 5);
        List<WebElement> options = driver.findElements(patientSuggestionListOfOptions);

        for (WebElement opt : options) {
            if (opt.getText().equals(s)) {
                opt.click();
                return;
            }
        }
    }

    public void typeOnLetterByLetter(WebElement webElement, String value) {
        webElement.clear();

        for (int i = 0; i < value.length(); i++){
            char c = value.charAt(i);
            String s = new StringBuilder().append(c).toString();
            webElement.sendKeys(s);
            TestUtilities.sleep(300);
        }
    }



}
