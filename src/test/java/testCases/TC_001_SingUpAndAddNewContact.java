package testCases;

import api.ContactListAppRestController;
import mainCode.BusinessObjects.*;
import mainCode.Common.BaseTest;
import model.ContactDTO;
import model.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_001_SingUpAndAddNewContact extends BaseTest {
    ContactListAppRestController contactListAppRestController;
    LoginBO loginBO;
    SignUpBO signUpBO;
    ContactListBO contactListBO;
    AddContactBO addContactBO;
    UserDTO user;
    ContactDTO contact;
    @BeforeClass
    public void create_data() {
        contactListAppRestController = new ContactListAppRestController();

        loginBO = new LoginBO(driver);
        user = UserDTO.builder().build();
        contact = ContactDTO.builder().build();
    }

    @Test(description = "Sign up with new user.")
    public void test_step_1() {
        signUpBO = loginBO.clickSignUpButton();
        contactListBO = signUpBO.successSignUpUser(user);
    }

    @Test(description = "Add new contact.", dependsOnMethods = "test_step_1")
    public void test_step_2() {
        addContactBO = contactListBO.clickAddNewContact();
        contactListBO = addContactBO.addNewContact(contact);
    }

    @Test(description = "Verify added contact.", dependsOnMethods = "test_step_2")
    public void test_step_3() {
        contactListBO.verifyAddedContact(contact);
    }

    @AfterClass(alwaysRun = true)
    public void clear_data() {
        contactListAppRestController.deleteUser(user);
    }
}