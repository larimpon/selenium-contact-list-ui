package mainCode.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class AddContactPO extends CommonPO {
    public enum Locators {
        FIRST_NAME("#firstName"),
        LAST_NAME("#lastName"),
        BIRTH_DATE("#birthdate"),
        EMAIL("#email"),
        PHONE("#phone"),
        ADDRESS_1("#street1"),
        ADDRESS_2("#street2"),
        CITY("#city"),
        STATE("#stateProvince"),
        POSTAL_CODE("#postalCode"),
        COUNTRY("#country");

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

    public enum ErrorMessages {
        CONTACT_VALIDATION_FAILED("Contact validation failed:"),
        INVALID_BIRTHDATE("birthdate: Birthdate is invalid");

        private final String errorMessage;

        ErrorMessages(String locator) {
            errorMessage = locator;
        }

        public String get() {
            return errorMessage;
        }

        public String getWithParams(Object... params) {
            return MessageFormat.format(String.valueOf(errorMessage), params);
        }
    }

    public AddContactPO(WebDriver driver) {
        super(driver);
    }
}
