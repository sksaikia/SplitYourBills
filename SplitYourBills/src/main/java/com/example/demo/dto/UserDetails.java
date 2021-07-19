package com.example.demo.dto;

import com.example.demo.model.User.User;

public class UserDetails {
    private Long userId;
    private String name;
    private String email;
    private String phoneNo;

    public UserDetails() {

    }

    public UserDetails(User user){
       this.userId = user.getId();
       this.email = user.getEmail();
       this.name = user.getName();
       this.phoneNo = user.getPhoneNo();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
