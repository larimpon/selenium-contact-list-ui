package mainCode.PageObjects;

import utils.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.MessageFormat;

public class CommonPO extends Actions {

    public enum CommonLocators {

        LOGIN_MENU_BUTTON("#login"),
        EMAIL_INPUT("#email"),
        PASSWORD_INPUT("#password"),
        SUBMIT_BUTTON("#submit"),
        ERROR_MESSAGE("#error");

        private final String myLocator;

        CommonLocators(String locator) {
            myLocator = locator;
        }

        public By get() {
            return By.cssSelector(myLocator);
        }

        public By getWithParams(Object... params) {
            return By.cssSelector(MessageFormat.format(String.valueOf(myLocator), params));
        }
    }

    public CommonPO(WebDriver driver) {
        super(driver);
    }

    /**
     * Click the submit form button.
     */
    public void clickSubmitFormButton() {
        click(CommonLocators.SUBMIT_BUTTON.get());
    }
}