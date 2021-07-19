package com.example.demo.model;

import com.example.demo.dto.AddNewSpaceDTO;
import com.example.demo.model.User.User;
import com.example.demo.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name="space")
public class Space extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @Column(name="person_id")
    private Long personId;

    @Column(name = "space_name")
    private String spaceName;

    @Column(name="space_description")
    private String spaceDescription;

    @Column(name="space_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "user_id",insertable = false,updatable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Space(){

    }
    public Space(Long spaceId, Long personId, String spaceName, String spaceDescription, Boolean isActive) {
        this.spaceId = spaceId;
        this.personId = personId;
        this.spaceName = spaceName;
        this.spaceDescription = spaceDescription;
        this.isActive = isActive;
    }

    public Space(AddNewSpaceDTO spaceDTO,long userId){
        this.setSpaceName(spaceDTO.getSpaceName());
        this.setSpaceDescription(spaceDTO.getSpaceDescription());
        this.setPersonId(userId);
        this.setActive(spaceDTO.getActive());
    }
}
