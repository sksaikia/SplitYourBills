package com.example.demo.dto.TXNDetails;

import com.example.demo.dto.Users.InviteUserDetails;
import com.example.demo.dto.Users.UserDetails;

public class PersonDetailsTXN {

    private Long personId;
    private Long totalPaid;
    private Long inviteId;
    private UserDetails userDetails;
    private InviteUserDetails invites;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Long totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public InviteUserDetails getInvites() {
        return invites;
    }

    public void setInvites(InviteUserDetails invites) {
        this.invites = invites;
    }


    public PersonDetailsTXN(Long personId, Long totalPaid, UserDetails userDetails, InviteUserDetails invites) {
        this.personId = personId;
        this.totalPaid = totalPaid;
        this.userDetails = userDetails;
        this.invites = invites;
    }

    public PersonDetailsTXN(Long personId,Long inviteId, Long totalPaid) {
        this.personId = personId;
        this.totalPaid = totalPaid;
        this.inviteId = inviteId;
    }
}
