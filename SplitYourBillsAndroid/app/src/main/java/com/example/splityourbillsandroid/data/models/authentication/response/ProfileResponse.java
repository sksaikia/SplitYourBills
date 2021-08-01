package com.example.splityourbillsandroid.data.models.authentication.response;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String phoneNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
