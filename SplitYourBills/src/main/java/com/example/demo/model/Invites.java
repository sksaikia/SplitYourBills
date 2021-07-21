package com.example.demo.model;

import com.example.demo.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name="invites")
public class Invites extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inviteId;

    @Column(name="space_id")
    private Long spaceId;

    @Column(name="phone_no")
    private String phoneNo;

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

    public Long getInviteId() {
        return inviteId;
    }

    public Invites() {
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Invites(Long inviteId, Long spaceId, String phoneNo) {
        this.inviteId = inviteId;
        this.spaceId = spaceId;
        this.phoneNo = phoneNo;
    }
    public Invites(Long spaceId, String phoneNo) {
        this.spaceId = spaceId;
        this.phoneNo = phoneNo;
    }
}
