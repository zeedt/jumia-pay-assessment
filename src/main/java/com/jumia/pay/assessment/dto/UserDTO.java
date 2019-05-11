package com.jumia.pay.assessment.dto;

import com.jumia.pay.assessment.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, message = "Username cannot be less than 3 characters")
    private String username;

    @NotBlank(message = "First namecannot be blank")
    @Size(min = 3, message = "First name cannot be less than 3 characters")
    private String firstname;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 3,  message = "Last name cannot be less than 3 characters")
    private String lastname;

    @NotBlank(message = "Buyer cannot be blank")
    @Size(min = 8, message = "password cannot be less than 8 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User retrieveEntity () {
        User user = new User();
        user.setPassword(this.password);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setUsername(this.username);
        return user;
    }
}
