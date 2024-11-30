package testCases;

import api.ContactListAppRestController;
import mainCode.BusinessObjects.*;
import mainCode.Common.BaseTest;
import model.ContactDTO;
import model.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_002_AddContactInvalidDateOfBirth extends BaseTest {
    ContactListAppRestController contactListAppRestController;
    LoginBO loginBO;
    ContactListBO contactListBO;
    AddContactBO addContactBO;
    UserDTO user;
    ContactDTO contact;

    @BeforeClass
    public void create_data() {
        contactListAppRestController = new ContactListAppRestController();

        user = UserDTO.builder().build();
        contact = ContactDTO.builder().birthDate("01-01-2000").build();

        contactListAppRestController.addNewUser(user);
        loginBO = new LoginBO(driver);
        contactListBO = loginBO.typeUserCredentials(user);
        addContactBO = contactListBO.clickAddNewContact();
    }

    @Test(description = "Add contact with invalid date of birth.")
    public void test_step_1() {
        addContactBO.addNewContact(contact);
    }

    @Test(description = "Validate error message.", dependsOnMethods = "test_step_1")
    public void test_step_2() {
        addContactBO.validateInvalidDateErrorMessage();
    }

    @AfterClass(alwaysRun = true)
    public void clear_data() {
        contactListAppRestController.deleteUser(user);
    }
}
