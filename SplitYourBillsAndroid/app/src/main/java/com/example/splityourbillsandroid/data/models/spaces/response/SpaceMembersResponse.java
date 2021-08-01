package com.example.splityourbillsandroid.data.models.spaces.response;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.google.gson.annotations.SerializedName;

public class SpaceMembersResponse {
    @SerializedName("id")
    private Long id;

    @SerializedName("userId")
    private Long userId;

    @SerializedName("spaceId")
    private Long spaceId;

    @SerializedName("inviteId")
    private Long inviteId;

    @SerializedName("phoneNo")
    private String phoneNo;

    @SerializedName("userDetails")
    private UserResponse userDetails;
    
    @SerializedName("joined")
    private Boolean joined;

    @SerializedName("userDTO")
    private UserResponse userDTO;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public UserResponse getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserResponse userDetails) {
        this.userDetails = userDetails;
    }

    public Boolean getJoined() {
        return joined;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
    }

    public UserResponse getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserResponse userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpaceMembersResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", spaceId=" + spaceId +
                ", inviteId=" + inviteId +
                ", phoneNo='" + phoneNo + '\'' +
                ", userDetails=" + userDetails +
                ", joined=" + joined +
                ", userDTO=" + userDTO +
                '}';
    }
}
