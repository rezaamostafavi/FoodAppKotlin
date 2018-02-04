package com.mostafavi.home.food.Data;

/**
 * Created by Reza on 2/2/2018.
 */

public class User {

    private String name;
    private String profilePicture;

    public User() {
    }

    public User(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
