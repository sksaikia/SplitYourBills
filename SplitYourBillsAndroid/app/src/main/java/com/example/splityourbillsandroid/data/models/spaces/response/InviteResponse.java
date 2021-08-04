package com.example.splityourbillsandroid.data.models.spaces.response;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.google.gson.annotations.SerializedName;

public class InviteResponse {

    @SerializedName("inviteId")
    private Long  inviteId;

    @SerializedName("spaceId")
    private Long spaceId;

    @SerializedName("phoneNo")
    private String phone;

    @SerializedName("name")
    private String name;

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
