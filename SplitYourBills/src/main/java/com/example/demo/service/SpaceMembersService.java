package com.example.demo.service;

import com.example.demo.dto.Member.AddSpaceMemberDTO;
import com.example.demo.dto.Member.SpaceMembersDTO;
import com.example.demo.model.Invites;
import com.example.demo.model.SpaceMembers;
import com.example.demo.model.User.User;
import com.example.demo.repository.InviteRepository;
import com.example.demo.repository.SpaceMembersRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpaceMembersService {

    @Autowired
    private SpaceMembersRepository spaceMembersRepository;

    @Autowired
    private InvitesService invitesService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InviteRepository inviteRepository;

//Search for the phone no in  the users table. If it is found , then invite_id is null and retrieve
    //the person id and place it here.
    public int addMemberOrInvite(SpaceMembersDTO spaceDTO){
        Long currUserId = Long.valueOf(0);
        String phoneNo = spaceDTO.getPhoneNo();
        if (userRepository.existsByPhoneno(phoneNo)){
            Optional<User> optionalUser = userRepository.findByPhoneno(phoneNo);
            if (optionalUser.isPresent()){
                currUserId = optionalUser.get().getId();
                //As the phone no is present in the users table, add it in the SpaceMembers table
                SpaceMembers spaceMembers = new SpaceMembers(spaceDTO.getSpaceId(),currUserId,phoneNo);
                spaceMembersRepository.save(spaceMembers);
                return 1;
            }else{
                //TODO need to throw some error
            }
        }else{
            //Create a new entry in the invitations table with phone no and space_id
            //replace the currUserId value
            Long spaceId = spaceDTO.getSpaceId();
            Invites newInvite = new Invites(spaceId,phoneNo);
            invitesService.addInvite(newInvite);

            long inviteId =  invitesService.getInviteByPhoneNoAndSpaceId(spaceId,phoneNo);

            SpaceMembers spaceMembers = new SpaceMembers();
            spaceMembers.setSpaceMembers(spaceId,inviteId,phoneNo);
            spaceMembersRepository.save(spaceMembers);

            return 2;

        }

        return -1;

    }

    private SpaceMembers getSpaceMembersFromDTO(SpaceMembersDTO spaceDTO, long userId) {
        SpaceMembers spaceMembers = new SpaceMembers(spaceDTO,userId);
        return spaceMembers;
    }

    public List<SpaceMembersDTO> getAllMembersBySpaceId(Long spaceId){
        List<SpaceMembers> spaceMembers = new ArrayList<>();
        List<SpaceMembersDTO> spaceMembersDTOS  = new ArrayList<>();
        if (spaceMembersRepository.existsBySpaceId(spaceId)){
            spaceMembers = spaceMembersRepository.findAllBySpaceId(spaceId);

            for (SpaceMembers s : spaceMembers){
                spaceMembersDTOS.add(getDTOFromSpaceMember(s));
            }

        }
        return spaceMembersDTOS;
    }

    private SpaceMembersDTO getDTOFromSpaceMember(SpaceMembers s) {

        SpaceMembersDTO spaceMembersDTO = new SpaceMembersDTO(s);
        return spaceMembersDTO;
    }



    public int addMemberOrInvite(AddSpaceMemberDTO spaceDTO){
        Long currUserId = Long.valueOf(0);
        String phoneNo = spaceDTO.getPhoneNo();
        if (userRepository.existsByPhoneno(phoneNo)){
            Optional<User> optionalUser = userRepository.findByPhoneno(phoneNo);
            if (optionalUser.isPresent()){
                currUserId = optionalUser.get().getId();
                //As the phone no is present in the users table, add it in the SpaceMembers table
                SpaceMembers spaceMembers = new SpaceMembers(spaceDTO.getSpaceId(),currUserId,phoneNo);
                spaceMembersRepository.save(spaceMembers);
                return 1;
            }else{
                //TODO need to throw some error
            }
        }else{
            //Create a new entry in the invitations table with phone no and space_id
            //replace the currUserId value
            Long spaceId = spaceDTO.getSpaceId();
            Invites newInvite = new Invites(spaceId,phoneNo, spaceDTO.getName());
            invitesService.addInvite(newInvite);

            long inviteId =  invitesService.getInviteByPhoneNoAndSpaceId(spaceId,phoneNo);

            SpaceMembers spaceMembers = new SpaceMembers();
            spaceMembers.setSpaceMembers(spaceId,inviteId,phoneNo);
            spaceMembersRepository.save(spaceMembers);

            return 2;

        }

        return -1;

    }

    public void deletePersonInSpace(Long personId,Long spaceId){
        Optional<SpaceMembers> optionalSpaceMembers =
                spaceMembersRepository.findBySpaceIdAndPersonId(spaceId,personId);

        if (optionalSpaceMembers.isPresent()){
            long id = optionalSpaceMembers.get().getId();
            spaceMembersRepository.deleteById(id);
        }else{
            //TODO throw exception
        }

    }

    public void deleteByInviteID(Long inviteId){
        Optional<SpaceMembers> optionalSpaceMembers =
                spaceMembersRepository.findByInviteId(inviteId);

        if (optionalSpaceMembers.isPresent()){
            long id = optionalSpaceMembers.get().getId();
            long inv = optionalSpaceMembers.get().getInviteId();
            spaceMembersRepository.deleteById(id);
            inviteRepository.deleteById(inv);
        }else{
            //TODO throw exception
        }

    }

}


