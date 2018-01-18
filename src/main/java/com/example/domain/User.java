package com.example.domain;

import javax.persistence.Entity;

/**
 * Created by lyl57 on 2017/3/13.
 */

@Entity // This tells Hibernate to make a table out of this class
public class User extends BaseEntity {

    public User() {

    }

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}