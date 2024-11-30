package mainCode.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class SignUpPO extends CommonPO {

    public enum Locators {
        FIRST_NAME_INPUT("#firstName"),
        LAST_NAME_INPUT("#lastName");

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

    public SignUpPO(WebDriver driver) {
        super(driver);
    }
}