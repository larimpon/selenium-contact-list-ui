package mainCode.BusinessObjects;

import mainCode.PageObjects.ContactListPO;
import model.ContactDTO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactListBO {
    public WebDriver driver;
    private ContactListPO contactListPO;

    public ContactListBO(WebDriver driver) {
        this.driver = driver;
        this.contactListPO = new ContactListPO(driver);
    }

    /**
     * Click 'Add a New Contact' button.
     *
     * @return
     */
    public AddContactBO clickAddNewContact() {
        contactListPO.click(ContactListPO.Locators.ADD_NEW_CONTACT_BUTTON.get());
        contactListPO.waitForUrl("addContact");
        return new AddContactBO(this.driver);
    }

    /**
     * Verify a contact is visible on the contact list page.
     *
     * @param contact -   Contact information.
     */
    public void verifyAddedContact(ContactDTO contact) {
        contactListPO.waitForUrl(ContactListPO.pageUrl);
        Assert.assertListContainsObject(contactListPO.getActualContacts(), contactToString(contact),
                "Contact is missing from contact list");
    }

    /**
     * Open a contact from the Contact list.
     *
     * @return
     */
    public ContactDetailsBO openContact() {
        contactListPO.click(ContactListPO.Locators.LIST_ROW.get());
        return new ContactDetailsBO(driver);
    }

    public void verifyContactDeletion() {
        Assert.assertFalse(contactListPO.isElementVisible(ContactListPO.Locators.LIST_ROW.get()));
    }

    /**
     * Convert all contact attributes to a single string object.
     *
     * @param contact - Contact information.
     * @return
     */
    public String contactToString(ContactDTO contact) {
        String expectedContact = Stream.of(contact.getClass().getDeclaredFields())
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(contact).toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(" "));
        return expectedContact;
    }
}