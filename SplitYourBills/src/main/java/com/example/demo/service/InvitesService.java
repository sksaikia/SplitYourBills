package com.example.demo.service;

import com.example.demo.model.Invites;
import com.example.demo.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InvitesService {

    @Autowired
    private InviteRepository inviteRepository;

    public InvitesService() {
    }

    public InvitesService(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }


    public void addInvite(Invites invite) {
        inviteRepository.save(invite);
    }
    public Optional<Invites> getInviteById(Long inviteId){
        return inviteRepository.findByInviteId(inviteId);
    }

    public Long getInviteByPhoneNoAndSpaceId(Long spaceId,String phoneNo){
        Optional<Invites> optionalInvites = inviteRepository.findBySpaceIdAndPhoneNo(spaceId, phoneNo);
        if (optionalInvites.isPresent()){
            Invites invites = optionalInvites.get();
            return invites.getInviteId();
        }
        return Long.valueOf(-1);
    }

}


