package com.example.splityourbillsandroid.data.models.spaces.response;

public class AddNewSpaceResponse {
    private Boolean success;
    private String message;
    private long SpaceId;
    private String spaceName;

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public AddNewSpaceResponse(Boolean success, String message, long spaceId) {
        this.success = success;
        this.message = message;
        SpaceId = spaceId;
    }

    public long getSpaceId() {
        return SpaceId;
    }

    public void setSpaceId(long spaceId) {
        SpaceId = spaceId;
    }

    public AddNewSpaceResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AddNewSpaceResponse(Long spaceId , String spaceName) {
        this.SpaceId = spaceId;
        this.spaceName = spaceName;
    }
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
