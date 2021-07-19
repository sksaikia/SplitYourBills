package com.example.demo.dto;

import com.example.demo.model.Space;

public class AddNewSpaceDTO {
    private Long spaceId;

    private String spaceName;
    private String spaceDescription;
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public AddNewSpaceDTO() {
    }


    public AddNewSpaceDTO(Space space){
        this.setSpaceId(space.getSpaceId());
        this.setActive(true);
        this.setSpaceName(space.getSpaceName());
        this.setSpaceDescription(space.getSpaceDescription());
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
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
}
