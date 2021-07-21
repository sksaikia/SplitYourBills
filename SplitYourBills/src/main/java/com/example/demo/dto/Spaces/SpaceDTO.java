package com.example.demo.dto.Spaces;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.Space;

public class SpaceDTO {
    private Long spaceId;
    private String spaceName;
    private String spaceDescription;
    private UserDetails userDetails;
    private boolean isActive;

    public UserDetails getUserDTO() {
        return userDetails;
    }

    public void setUserDTO(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public SpaceDTO(Space space) {
        this.setSpaceId(space.getSpaceId());
        this.setSpaceName(space.getSpaceName());
        this.setSpaceDescription(space.getSpaceDescription());
        this.setActive(space.getActive());
        this.userDetails = new UserDetails(space.getUser());
    }

//    public void setUserDTO(User user) {
//        if (user==null)
//            System.out.println("Your are screwed");
//        this.userDTO.setUserId(user.getId());
//        this.userDTO.setEmail(user.getEmail());
//
//        System.out.println(user.getEmail());
//        this.userDTO.setName(user.getName());
//    }

    public SpaceDTO(){

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

//    public UserDTO getUserDTO() {
//        return userDTO;
//    }
//
//    public void setUserDTO(UserDTO userDTO) {
//        this.userDTO = userDTO;
//    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
