package mainCode.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class ContactDetailsPO extends CommonPO {
    public enum Locators {
        DELETE_CONTACT_BUTTON("#delete");

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

    public ContactDetailsPO(WebDriver driver) {
        super(driver);
    }
}