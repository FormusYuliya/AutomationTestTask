package pages;

import base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NewMedicationRequestPage extends BasePageObject{
    public NewMedicationRequestPage(WebDriver driver) {
        super(driver);
    }

    private By patientSuggestionListOfOptions = By.xpath("(//div[@class='tt-suggestion tt-selectable'])");

    @FindBy(xpath = "//h1[@class='view-current-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "(//input[contains(@class,'form-control ember-text-field ember-view tt-input')])[1]")
    private WebElement patientField;
    @FindBy(xpath = "//div[@class='tt-menu tt-open']")
    private WebElement patientSuggestionList;

    @FindBy(xpath = "//div[@class='tt-suggestion tt-selectable'][contains(.,'Test Patient - P00201')]")
    private WebElement item;
    @FindBy(xpath = "//select[contains(@class,'form-control ')]")
    private WebElement visitField;

    @FindBy(xpath = "(//input[contains(@class,'form-control ember-text-field ember-view tt-input')])[2]")
    private WebElement medicationField;
    @FindBy(xpath = "//div[@class='tt-suggestion tt-selectable'][contains(.,'Pramoxine - m00005')]")
    private WebElement medicationOption;
    @FindBy(xpath = "//textarea[contains(@rows,'3')]")
    private WebElement prescriptionField;

    @FindBy(xpath = "(//input[contains(@type,'text')])[7]")
    private WebElement quantityRequestedField;
    @FindBy(xpath = "(//input[contains(@type,'text')])[8]")
    private WebElement refillsField;

    @FindBy(xpath = "(//input[contains(@type,'text')])[6]")
    private WebElement prescriptionDateField;
    @FindBy(css = ".on-white")
    private WebElement addButton;

    @FindBy(xpath = "//div[contains(@class,'modal-dialog')]")
    private WebElement modalWindow;
    @FindBy(xpath = "//button[contains(.,'Ok')]")
    private WebElement modalOkButton;
    @FindBy(xpath = "//span[contains(@class,'octicon octicon-x')]")
    private WebElement modalXButton;


    public String getPageHeading() {
        return getText(pageTitle);
    }

    public void selectPatient(String patientName) {
        click(patientField);
        TestUtilities.sleep(2000);
        type(patientName, patientField);
        selectOptionFromDropdown("Test Patient - P00201", patientSuggestionList, patientSuggestionListOfOptions);
    }

    public void selectVisit(int visitDateIndex) {
        Select visitDropdown = new Select(visitField);
        TestUtilities.sleep(2000);
        visitDropdown.selectByIndex(visitDateIndex);
    }

    public void setMedication(String medication) {
        click(medicationField);
        type(medication, medicationField);
        click(medicationOption);
    }

    public void setPrescription(String prescription) {
        type(prescription, prescriptionField);
    }

    public void setQuantityRequested(int num) {
        type(String.valueOf(num), quantityRequestedField);
    }

    public void setRefills(int num) {
        type(String.valueOf(num), refillsField);
    }

    public void setDate() {
        LocalDate today = LocalDate.now();
        String formattedDate = today.minusDays(1).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        click(prescriptionDateField);
        prescriptionDateField.clear();
        type(formattedDate, prescriptionDateField);
    }

    public void clickAdd() {
        click(addButton);
    }

    public boolean alertIsShown() {
        return alertElementsIsVisible(modalWindow);
    }

    public boolean buttonsShownInAlert() {
        return alertElementsIsVisible(modalOkButton) && alertElementsIsVisible(modalXButton);
    }

    public void clickOk() {
        click(modalOkButton);
    }
}
