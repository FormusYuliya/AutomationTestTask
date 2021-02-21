import base.BaseTest;
import base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewMedicationRequestPage;
import pages.SignInPage;

import java.util.Arrays;
import java.util.List;

public class PageTests extends BaseTest {
    private SignInPage signInPage;

    @Test
    public void validSignInTest() {
        signInPage = new SignInPage(driver);
        signInPage.openPage();
        HomePage home = signInPage.validLogin("hr.doctor@hospitalrun.io", "HRt3st12");
        home.getPageTitle();
        String message = driver.getCurrentUrl();

        Assert.assertEquals(message, "http://demo.hospitalrun.io/#/patients");
        Assert.assertEquals(home.getPageTitle(), "Patient Listing");
        Assert.assertTrue(home.getAmountOfPatients() > 0);
    }

    @Test
    public void invalidLoginTest() {
        signInPage = new SignInPage(driver);
        signInPage.openPage();

        String error = signInPage.invalidLogin("ere", "ererf");
        Assert.assertEquals(error, "Username or password is incorrect.");
    }

    @Test
    public void logoutTest() {
        signInPage = new SignInPage(driver);
        signInPage.openPage();
        HomePage home = signInPage.validLogin("hr.doctor@hospitalrun.io", "HRt3st12");
        home.logout();
        Assert.assertTrue(signInPage.getPageHeading().toLowerCase().equals("please sign in"));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://demo.hospitalrun.io/#/login"));
    }

    @Test
    public void listCheck() {
        signInPage = new SignInPage(driver);
        signInPage.openPage();
        HomePage home = signInPage.validLogin("hr.doctor@hospitalrun.io", "HRt3st12");
        List<String> expectedListOfMedicationOptions = Arrays.asList("Return Medication", "Requests", "Completed", "New Request");
        Assert.assertTrue(home.medicationSectionContains(expectedListOfMedicationOptions));
        NewMedicationRequestPage medPage = home.selectNewMedicationRequest("New Request");
        medPage.typeletters("Test Patient ");
        medPage.selectPatient("Test Patient - P00201");
        medPage.setMedication("Pramoxine");
        medPage.selectVisit(1);
        medPage.setPrescription("Testing prescription");
        medPage.setQuantityRequested(TestUtilities.getRandomNumberInRange(1, 5));
        medPage.setRefills(TestUtilities.getRandomNumberInRange(5, 10));
        medPage.setDate();
        medPage.clickAdd();
        Assert.assertTrue(medPage.alertIsShown());
        Assert.assertTrue(medPage.buttonsShownInAlert());
        medPage.clickOk();
        Assert.assertFalse(medPage.alertIsShown());
        Assert.assertEquals(medPage.getPageHeading(), "New Medication Request");
    }
}
