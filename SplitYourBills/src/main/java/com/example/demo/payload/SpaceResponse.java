package com.example.demo.payload;

public class SpaceResponse {
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

    public SpaceResponse(Boolean success, String message, long spaceId, String spaceName) {
        this.success = success;
        this.message = message;
        SpaceId = spaceId;
        this.spaceName = spaceName;
    }

    public SpaceResponse(Boolean success, String message, long spaceId) {
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

    public SpaceResponse(long SpaceId, String spaceName) {
        this.SpaceId = SpaceId;
        this.spaceName = spaceName;
    }
    public SpaceResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
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
