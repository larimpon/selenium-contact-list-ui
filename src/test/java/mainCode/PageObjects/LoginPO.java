package mainCode.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class LoginPO extends CommonPO {

    public enum Locators {
        SIGN_UP_BUTTON("#signup");

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

    public LoginPO(WebDriver driver) {
        super(driver);
    }
}