package mainCode.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ContactListPO extends CommonPO{
    public enum Locators {
        ADD_NEW_CONTACT_BUTTON("#add-contact"),
        LIST_ROW("tr.contactTableBodyRow");

        private final String myLocator;

        Locators(String locator) {
            myLocator = locator;
        }

        public By get() {
            return By.cssSelector(myLocator);
        }

        public By getWithParams(Object... params) {
            return By.cssSelector(MessageFormat.format(String.valueOf(myLocator), params));
        }
    }

    public static String pageUrl = "contactList";

    public ContactListPO(WebDriver driver) {
        super(driver);
    }


    /**
     * Captures the contact information displayed on the contact list.
     *
     * @return  -   List of all contact information.
     */
    public List<String> getActualContacts() {
        waitForElementToBeVisible(Locators.LIST_ROW.get());
        List<String> actualContacts = new ArrayList<>();
        Integer i = getElementMatchingLocators(Locators.LIST_ROW.get());
        IntStream.range(0, i).forEach(j -> actualContacts.add(getNthElementText(Locators.LIST_ROW.get(), j)));
        return actualContacts;
    }
}
