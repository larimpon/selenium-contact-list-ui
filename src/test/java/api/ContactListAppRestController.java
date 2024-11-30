package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactDTO;
import model.UserDTO;
import org.testng.Assert;
import utils.ConfigManager;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * This class interacts with applications API in order to create, access or delete application entities.
 */
@Slf4j
public class ContactListAppRestController {

    private final String baseUrl = ConfigManager.getProperty("baseUrl");
    HttpClient client = HttpClient.newHttpClient();
    private String token;
    private UserDTO user;

    /**---------------------------- Users ----------------------------*/

    /**
     * POST - request to sign up a new user to the system.
     *
     * @param user -   User information.
     */
    public void addNewUser(UserDTO user) {
        this.user = user;
        log.info("STEP - Signup a user using API.");
        Map<String, String> postBody = new HashMap<>();

        postBody.put("firstName", user.getFirstname());
        postBody.put("lastName", user.getLastname());
        postBody.put("email", user.getEmail());
        postBody.put("password", user.getPassword());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonBody = objectMapper.writeValueAsString(postBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + "/users"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(jsonBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), 201);
            String responseBody = response.body();
            token = objectMapper.readTree(responseBody).get("token").asText();
            log.info("POST - New user is added up.");
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    /**
     * POST - request to delete a user from the system.
     */
    public void deleteUser(UserDTO user) {
        if (this.user != user) {
            this.user = user;
            userLogin();
        }
        log.info("STEP - Delete a user using API.");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + "/users/me"))
                    .header("Authorization", "Bearer " + token)
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), 200);
            log.info("POST - User is deleted.");
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    /**
     * POST - Login with user to get authorization token to leverage applications api.
     */
    public void userLogin() {
        log.info("STEP - Login with a user using API.");
        Map<String, String> postBody = new HashMap<>();
        postBody.put("email", user.getEmail());
        postBody.put("password", user.getPassword());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonBody = objectMapper.writeValueAsString(postBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + "/users/login"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(jsonBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), 200);
            String responseBody = response.body();
            token = objectMapper.readTree(responseBody).get("token").asText();
            log.info("POST - User is authorized.");
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    /**---------------------------- Contacts ----------------------------*/

    /**
     * POST - request to sign up a new user to the system.
     *
     * @param contact -   Contact information.
     */
    public void addNewContact(ContactDTO contact) {
        log.info("STEP - Create a new contact using API.");
        Map<String, String> postBody = new HashMap<>();

        postBody.put("firstName", contact.getFirstname());
        postBody.put("lastName", contact.getLastname());
        postBody.put("birthdate", contact.getBirthDate());
        postBody.put("email", contact.getEmail());
        postBody.put("phone", contact.getPhone());
        postBody.put("street1", contact.getAddress1());
        postBody.put("street2", contact.getAddress2());
        postBody.put("city", contact.getCity());
        postBody.put("stateProvince", contact.getState());
        postBody.put("portalCode", contact.getPostalCode().toString());
        postBody.put("country", contact.getCountry());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonBody = objectMapper.writeValueAsString(postBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + "/contacts"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .POST(BodyPublishers.ofString(jsonBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), 201);
            log.info("POST - New contact is added up.");
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}