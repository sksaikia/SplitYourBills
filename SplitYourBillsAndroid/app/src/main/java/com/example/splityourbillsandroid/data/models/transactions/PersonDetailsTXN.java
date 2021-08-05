package com.example.splityourbillsandroid.data.models.transactions;

import com.example.splityourbillsandroid.data.models.authentication.response.UserResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.InviteResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDetailsTXN {


    @SerializedName("totalPaid")
    private Long totalPaid;

    @SerializedName("inviteId")
    private Long inviteId;

    @SerializedName("personId")
    private Long personId;

    @SerializedName("userDetails")
    private UserResponse userResponse;

    @SerializedName("invites")
    private InviteResponse inviteResponse;


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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public InviteResponse getInviteResponse() {
        return inviteResponse;
    }

    public void setInviteResponse(InviteResponse inviteResponse) {
        this.inviteResponse = inviteResponse;
    }

    public PersonDetailsTXN(Long totalPaid, Long inviteId, Long personId, UserResponse userResponse, InviteResponse inviteResponse) {
        this.totalPaid = totalPaid;
        this.inviteId = inviteId;
        this.personId = personId;
        this.userResponse = userResponse;
        this.inviteResponse = inviteResponse;
    }
}
