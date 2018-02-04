package com.mostafavi.home.food.Data;

/**
 * Created by Reza on 2/2/2018.
 */

public class Food {

    private String name;
    private long dateTime;
    private String image;
    private String description;
    private int like;
    private User user;
    private boolean liked;

    public Food() {
    }

    public Food(String name, long dateTime, String image, String description, int like, User user) {
        this.name = name;
        this.dateTime = dateTime;
        this.image = image;
        this.description = description;
        this.like = like;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
