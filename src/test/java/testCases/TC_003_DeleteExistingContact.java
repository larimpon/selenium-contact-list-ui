package testCases;

import api.ContactListAppRestController;
import mainCode.BusinessObjects.ContactDetailsBO;
import mainCode.BusinessObjects.ContactListBO;
import mainCode.BusinessObjects.LoginBO;
import mainCode.Common.BaseTest;
import model.ContactDTO;
import model.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_003_DeleteExistingContact extends BaseTest {
    ContactListAppRestController contactListAppRestController;
    LoginBO loginBO;
    ContactListBO contactListBO;
    ContactDetailsBO contactDetails;
    UserDTO user;
    ContactDTO contact;

    @BeforeClass
    public void create_data() {
        contactListAppRestController = new ContactListAppRestController();

        user = UserDTO.builder().build();
        contact = ContactDTO.builder().build();

        contactListAppRestController.addNewUser(user);
        contactListAppRestController.addNewContact(contact);
        loginBO = new LoginBO(driver);
        contactListBO = loginBO.typeUserCredentials(user);
        contactDetails = contactListBO.openContact();
    }

    @Test(description = "Delete an existing contact.")
    public void test_step_1() {
        contactListBO = contactDetails.deleteContact();
        contactListBO.verifyContactDeletion();
    }

    @AfterClass(alwaysRun = true)
    public void clear_data() {
        contactListAppRestController.deleteUser(user);
    }
}
