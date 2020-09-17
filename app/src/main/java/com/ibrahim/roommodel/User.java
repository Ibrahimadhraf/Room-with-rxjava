package com.ibrahim.roommodel;

public class User {
    private int userInt;
    private String userName;

    public User(int userInt, String userName) {
        this.userInt = userInt;
        this.userName = userName;
    }

    public int getUserInt() {
        return userInt;
    }

    public String getUserName() {
        return userName;
    }
}
