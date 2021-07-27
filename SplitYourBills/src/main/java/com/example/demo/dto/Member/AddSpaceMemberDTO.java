package com.example.demo.dto.Member;

import com.example.demo.dto.Users.UserDetails;
import com.example.demo.model.SpaceMembers;

public class AddSpaceMemberDTO {
    private Long id;
    private Long spaceId;
    private String phoneNo;


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

    public AddSpaceMemberDTO(Long id, Long spaceId, String phoneNo) {
        this.id = id;
        this.spaceId = spaceId;
        this.phoneNo = phoneNo;
    }
}
