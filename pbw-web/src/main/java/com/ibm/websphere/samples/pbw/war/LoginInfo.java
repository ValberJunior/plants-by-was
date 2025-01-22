package com.ibm.websphere.samples.pbw.war;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A JSF backing bean used to store information for the login web page.
 */
public class LoginInfo {
    @NotBlank
    @Email
    private String email;

    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters.")
    private String password;

    private String checkPassword;
    private String message;

    // Getters and setters
}