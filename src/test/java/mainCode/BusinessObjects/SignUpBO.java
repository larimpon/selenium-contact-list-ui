package mainCode.BusinessObjects;

import mainCode.PageObjects.SignUpPO;
import model.UserDTO;
import org.openqa.selenium.WebDriver;

public class SignUpBO {
    public WebDriver driver;
    private SignUpPO signUpPO;

    public SignUpBO(WebDriver driver) {
        this.driver = driver;
        this.signUpPO = new SignUpPO(driver);
    }

    /**
     * Completes the signup form fields and clicks the submit form button,
     * leading to a successful signup.
     *
     * @param user -   User object to complete the form with.
     **/
    public ContactListBO successSignUpUser(UserDTO user) {
        typeUserCredentials(user);
        signUpPO.waitForUrl("contactList");
        return new ContactListBO(driver);
    }

    /**
     * Completes the signup form required fields and clicks the submit form button.
     *
     * @param user -   User object to complete the form with.
     **/
    public void typeUserCredentials(UserDTO user) {
        signUpPO.typeKeys(SignUpPO.Locators.FIRST_NAME_INPUT.get(), user.getFirstname());
        signUpPO.typeKeys(SignUpPO.Locators.LAST_NAME_INPUT.get(), user.getLastname());
        signUpPO.typeKeys(SignUpPO.CommonLocators.EMAIL_INPUT.get(), user.getEmail());
        signUpPO.typeKeys(SignUpPO.CommonLocators.PASSWORD_INPUT.get(), user.getPassword());
        signUpPO.clickSubmitFormButton();
    }
}
