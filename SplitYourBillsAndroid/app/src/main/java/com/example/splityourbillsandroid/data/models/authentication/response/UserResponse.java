package com.example.splityourbillsandroid.data.models.authentication;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("userId")
    private String userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("phoneNo")
    private String userPhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
