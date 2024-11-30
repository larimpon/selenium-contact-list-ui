package mainCode.BusinessObjects;

import mainCode.PageObjects.AddContactPO;
import mainCode.PageObjects.CommonPO;
import model.ContactDTO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddContactBO {
    public WebDriver driver;
    private AddContactPO addContactPO;

    public AddContactBO(WebDriver driver) {
        this.driver = driver;
        this.addContactPO = new AddContactPO(driver);
    }

    /**
     * Type all contact information.
     *
     * @param contact   -   Contact information.
     * @return
     */
    public ContactListBO addNewContact(ContactDTO contact) {
        addContactPO.typeKeys(AddContactPO.Locators.FIRST_NAME.get(), contact.getFirstname());
        addContactPO.typeKeys(AddContactPO.Locators.LAST_NAME.get(), contact.getLastname());
        addContactPO.typeKeys(AddContactPO.Locators.BIRTH_DATE.get(), contact.getBirthDate());
        addContactPO.typeKeys(AddContactPO.Locators.EMAIL.get(), contact.getEmail());
        addContactPO.typeKeys(AddContactPO.Locators.PHONE.get(), contact.getPhone());
        addContactPO.typeKeys(AddContactPO.Locators.ADDRESS_1.get(), contact.getAddress1());
        addContactPO.typeKeys(AddContactPO.Locators.ADDRESS_2.get(), contact.getAddress2());
        addContactPO.typeKeys(AddContactPO.Locators.CITY.get(), contact.getCity());
        addContactPO.typeKeys(AddContactPO.Locators.STATE.get(), contact.getState());
        addContactPO.typeKeys(AddContactPO.Locators.POSTAL_CODE.get(), contact.getPostalCode().toString());
        addContactPO.typeKeys(AddContactPO.Locators.COUNTRY.get(), contact.getCountry());
        addContactPO.click(CommonPO.CommonLocators.SUBMIT_BUTTON.get());
        return new ContactListBO(this.driver);
    }

    /**
     * Validate error message for invalid birthdate.
     */
    public void validateInvalidDateErrorMessage() {
        Assert.assertEquals(addContactPO.getText(CommonPO.CommonLocators.ERROR_MESSAGE.get()),
                AddContactPO.ErrorMessages.CONTACT_VALIDATION_FAILED.get() + " "
                        + AddContactPO.ErrorMessages.INVALID_BIRTHDATE.get());
    }
}