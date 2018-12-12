package com.example.nkeven.graduationproject;

public class Person {
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPhoneNumber;

    public Person(String userName, String userSurname, String userEmail, String userPhoneNumber) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }
}
