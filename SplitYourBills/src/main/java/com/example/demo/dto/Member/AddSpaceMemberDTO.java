package com.example.demo.dto.Member;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.SpaceMembers;

public class AddSpaceMemberDTO {
    private Long id;
    private Long spaceId;
    private String phoneNo;
    private String name;


    public AddSpaceMemberDTO(SpaceMembers    spaceMembers){
        this.setId(spaceMembers.getId());
        this.setSpaceId(spaceMembers.getSpaceId());
        this.setPhoneNo(spaceMembers.getPhoneNo());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddSpaceMemberDTO(Long spaceId, String phoneNo, String name) {
        this.spaceId = spaceId;
        this.phoneNo = phoneNo;
        this.name = name;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public AddSpaceMemberDTO() {
    }

    public AddSpaceMemberDTO( Long spaceId, String phoneNo) {
        this.spaceId = spaceId;
        this.phoneNo = phoneNo;
    }
}
