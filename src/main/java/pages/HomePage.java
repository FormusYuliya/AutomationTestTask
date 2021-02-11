package pages;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePageObject{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private String pageUrl = "http://demo.hospitalrun.io/#/patients";

    @FindBy(xpath = "//h1[@class='view-current-title']")
    private WebElement pageHeader;
    @FindBy(xpath = "//a[contains(.,'Medication')]")
    private WebElement medicationManuItem;
    @FindBy(css = ".category-sub-items")
    private WebElement medicationList;

    private By patientsList = By.cssSelector("tr.clickable");
    private By medicationListOfOptions = By.xpath("//a[contains(@class,'category-sub-item nav-link')]");

    @FindBy(css = ".settings-trigger")
    private WebElement gearIcon;
    @FindBy(css = ".logout")
    private WebElement logout;

    public String getPageTitle() {
        waitForVisibilityOf(pageHeader);
        return getText(pageHeader);
    }

    public int getAmountOfPatients() {
        List<WebElement> listRows = findAll(patientsList);
        System.out.println(listRows.size());
        return listRows.size();
    }

    public void logout() {
        click(gearIcon);
        click(logout);
    }

    public boolean medicationSectionContains(List<String> expectedListOfMedicationOptions) {

        click(medicationManuItem);
        WebElement List = medicationList;
        List<WebElement> listOfItemsInMedicationSection = findAll(medicationListOfOptions);
        boolean result = false;

        ArrayList<String> actualListOfMedicationOptions = new ArrayList<>();
        for (WebElement item : listOfItemsInMedicationSection) {
            actualListOfMedicationOptions.add(item.getText());
        }

        if (expectedListOfMedicationOptions.size() == actualListOfMedicationOptions.size()) {
            result = CollectionUtils.isEqualCollection(actualListOfMedicationOptions, expectedListOfMedicationOptions);
        }
        return result;
    }

    public NewMedicationRequestPage selectNewMedicationRequest(String linkText){
        driver.findElement(By.linkText(linkText)).click();
        return new NewMedicationRequestPage(driver);
    }
}
