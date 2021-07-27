package com.example.splityourbillsandroid.data.models.spaces;

import com.google.gson.annotations.SerializedName;

public class SpaceBody {
    @SerializedName("spaceName")
    private String spaceName;

    @SerializedName("spaceDescription")
    private String spaceDescription;

    @SerializedName("active")
    private Boolean active;

    public SpaceBody(String spaceName, String spaceDescription) {
        this.spaceName = spaceName;
        this.spaceDescription = spaceDescription;
        this.active = true;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public void setSpaceDescription(String spaceDescription) {
        this.spaceDescription = spaceDescription;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SpaceBody() {
    }
}
