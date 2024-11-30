package mainCode.BusinessObjects;

import mainCode.PageObjects.ContactDetailsPO;
import org.openqa.selenium.WebDriver;

public class ContactDetailsBO {
    public WebDriver driver;
    private ContactDetailsPO contactDetailsPO;

    public ContactDetailsBO(WebDriver driver) {
        this.driver = driver;
        this.contactDetailsPO = new ContactDetailsPO(driver);
    }

    /**
     * Delete the contact.
     *
     * @return
     */
    public ContactListBO deleteContact() {
        contactDetailsPO.click(ContactDetailsPO.Locators.DELETE_CONTACT_BUTTON.get());
        contactDetailsPO.driver.switchTo().alert().accept();
        return new ContactListBO(driver);
    }
}