package com.aninfo.model;

import javax.persistence.Entity;

@Entity
public class UserStory {
    public String as;
    public String iWant;
    public String soThat;

    public UserStory(String as, String iWant, String soThat)
    {
        this.as = as;
        this.iWant = iWant;
        this.soThat = soThat;
    }

    public UserStory() {

    }
}

