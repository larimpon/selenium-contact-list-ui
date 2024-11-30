package model;

import lombok.Builder;
import lombok.Data;

import java.util.Random;

/**
 * This class is a Data Transfer Object for User data.
 */
@Data
@Builder
public class UserDTO {

    @Builder.Default
    private String firstname = "User Firstname";
    @Builder.Default
    private String lastname = "User Lastname";
    @Builder.Default
    private String email = "cla_" + new Random(System.currentTimeMillis()).nextInt(99999) + "@mailinator.com";
    @Builder.Default
    private String password = "password";
}