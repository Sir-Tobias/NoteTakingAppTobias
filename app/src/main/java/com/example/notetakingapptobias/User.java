package com.example.notetakingapptobias;

public class User {

    public String fullname, phoneNo, email;

    public User() {
    }

    public User(String fullname, String phoneNo, String email) {
        this.fullname = fullname;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public User(String fullname, String phoneNo) {
        this.fullname = fullname;
        this.phoneNo = phoneNo;
    }
}
