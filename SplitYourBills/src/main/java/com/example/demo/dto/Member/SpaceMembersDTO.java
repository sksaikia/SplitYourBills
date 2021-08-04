package com.example.demo.dto.Member;

import com.example.demo.dto.Users.InviteUserDetails;
import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.Invites;
import com.example.demo.model.Space;
import com.example.demo.model.SpaceMembers;

public class SpaceMembersDTO {
    private Long id;
    private Long userId;
    private Long spaceId;
    private Long inviteId;
    private String phoneNo;
    private Boolean isJoined;
    private UserDetails userDetails;
    private InviteUserDetails invites;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails getUserDTO() {
        return userDetails;
    }

    public void setUserDTO(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public SpaceMembersDTO(SpaceMembers spaceMembers) {
        this.setId(spaceMembers.getId());
        this.setSpaceId(spaceMembers.getSpaceId());
        this.setInviteId(spaceMembers.getInviteId());
        this.setPhoneNo(spaceMembers.getPhoneNo());
        this.setJoined(spaceMembers.getJoined());
        this.setUserId(spaceMembers.getPersonId());
        this.userDetails = new UserDetails(spaceMembers.getUser());
        this.invites = new InviteUserDetails(spaceMembers.getInvites());
    }


    public InviteUserDetails getInvites() {
        return invites;
    }

    public void setInvites(InviteUserDetails invites) {
        this.invites = invites;
    }

    public SpaceMembersDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getJoined() {
        return isJoined;
    }

    public void setJoined(Boolean joined) {
        isJoined = joined;
    }
}
