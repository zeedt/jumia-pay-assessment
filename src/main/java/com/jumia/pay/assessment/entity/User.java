package com.jumia.pay.assessment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "user")
public class User implements Serializable {

    @JsonIgnore
    private String id;

    private String username;

    private String firstname;

    private String lastname;

    private String password;

    private Date dateCreated;

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"username\":\"" + username + '\"' +
                ", \"firstname\":\"" + firstname + '\"' +
                ", \"lastname\":\"" + lastname + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"dateCreated\":\"" + dateCreated + '\"' +
                '}';
    }
}
