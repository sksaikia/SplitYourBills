package com.example.demo.dto.Users;

import com.example.demo.model.Invites;
import com.example.demo.model.User.User;

public class InviteUserDetails {
    private Long inviteId;
    private Long spaceId;
    private String phoneNo;

    public InviteUserDetails() {

    }

    public InviteUserDetails(Invites invites){
       this.inviteId = invites.getInviteId();
       this.spaceId = invites.getSpaceId();
       this.phoneNo = invites.getPhoneNo();
    }

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
