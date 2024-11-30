package mainCode.BusinessObjects;

import mainCode.PageObjects.LoginPO;
import mainCode.PageObjects.SignUpPO;
import model.UserDTO;
import org.openqa.selenium.WebDriver;

public class LoginBO {
    public WebDriver driver;
    private LoginPO loginPO;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        this.loginPO = new LoginPO(driver);
    }

    /**
     * Click Sign up button.
     *
     * @return
     */
    public SignUpBO clickSignUpButton() {
        loginPO.click(LoginPO.Locators.SIGN_UP_BUTTON.get());
        return new SignUpBO(this.driver);
    }

    /**
     * Complete the login form and click the submit button.
     *
     * @param user -   User object to complete the form with.
     **/
    public ContactListBO typeUserCredentials(UserDTO user) {
        loginPO.typeKeys(SignUpPO.CommonLocators.EMAIL_INPUT.get(), user.getEmail());
        loginPO.typeKeys(SignUpPO.CommonLocators.PASSWORD_INPUT.get(), user.getPassword());
        loginPO.clickSubmitFormButton();
        return new ContactListBO(driver);
    }
}