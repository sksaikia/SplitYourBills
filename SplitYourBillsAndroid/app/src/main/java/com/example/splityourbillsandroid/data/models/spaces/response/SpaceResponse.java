package com.example.splityourbillsandroid.data.models.spaces.response;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.google.gson.annotations.SerializedName;

public class SpaceResponse {

    @SerializedName("spaceId")
    private String spaceId;

    @SerializedName("spaceName")
    private String spaceName;

    @SerializedName("spaceDescription")
    private String spaceDescription;

    @SerializedName("active")
    private Boolean isActive;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceDescription() {
        return spaceDescription;
    }

    public void setSpaceDescription(String spaceDescription) {
        this.spaceDescription = spaceDescription;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    @SerializedName("userDTO")
    private UserResponse user;


}
