package com.example.demo.model;

import com.example.demo.dto.Member.SpaceMembersDTO;
import com.example.demo.model.User.User;
import com.example.demo.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name="space_members")
public class SpaceMembers extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="space_id")
    private Long spaceId;

    @Column(name="person_id")
    private Long personId;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="invite_id")
    private Long inviteId;

    @Column(name="is_joined")
    private Boolean isJoined;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    private User user;

    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    @ManyToOne
    @JoinColumn(name = "invite_id", referencedColumnName = "invite_id",insertable = false,updatable = false)
    private Invites invites;




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SpaceMembers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Boolean getJoined() {
        return isJoined;
    }

    public void setJoined(Boolean joined) {
        isJoined = joined;
    }

    public SpaceMembers(SpaceMembersDTO spaceMembersDTO,long userId){
        this.setSpaceId(spaceMembersDTO.getSpaceId());
        this.setJoined(true);
        this.setInviteId(null);
        this.setPhoneNo(spaceMembersDTO.getPhoneNo());
        this.setPersonId(userId);
    }
    //If user is found with particular phone no in the users table
    public SpaceMembers(Long spaceId,Long personId,String phoneNo){
        this.spaceId = spaceId;
        this.personId = personId;
        this.phoneNo = phoneNo;
        inviteId = Long.valueOf(-1);
        isJoined = true;
    }
    public void  setSpaceMembers(Long spaceId,Long inviteId,String phoneNo){

        this.spaceId = spaceId;
        this.personId = Long.valueOf(-1);
        this.phoneNo = phoneNo;
        this.inviteId = inviteId;
        isJoined = false;
    }

}
