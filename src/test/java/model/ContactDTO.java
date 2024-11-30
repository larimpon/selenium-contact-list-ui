package model;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

/**
 * This class is a Data Transfer Object for Contact data.
 */
@Data
@Builder
public class ContactDTO {

    @Builder.Default
    private String firstname = "Contact Firstname";
    @Builder.Default
    private String lastname = "Contact Lastname";
    @Builder.Default
    private String birthDate = "2000-01-01";
    @Builder.Default
    private String email = "cla_contact" + new Random(System.currentTimeMillis()).nextInt(99999) + "@mailinator.com";
    @Builder.Default
    private String phone = "1234567890";
    @Builder.Default
    private String address1 = "Custom address 1";
    @Builder.Default
    private String address2 = "Custom address 2";
    @Builder.Default
    private String city = "Thessaloniki";
    @Builder.Default
    private String state = "Center Macedonia";
    @Builder.Default
    private Integer postalCode = 50000;
    @Builder.Default
    private String country = "Greece";
}