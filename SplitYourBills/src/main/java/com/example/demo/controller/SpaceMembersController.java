package com.example.demo.controller;

import com.example.demo.dto.SpaceMembersDTO;
import com.example.demo.model.Invites;
import com.example.demo.model.Space;
import com.example.demo.model.SpaceMembers;
import com.example.demo.payload.ApiResponse;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.InvitesService;
import com.example.demo.service.SpaceMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spacemember")
public class SpaceMembersController {
    @Autowired
    private SpaceMembersService spaceMembersService;


//    @GetMapping("/{inviteId}")
//    public ResponseEntity<Invites> getInviteById(@PathVariable("inviteId") long inviteId, @CurrentUser UserPrincipal currentUser){
//        Optional<Invites> optionalInvites = invitesService.getInviteById(inviteId);
//        Invites newInvite = null;
//        if (optionalInvites.isPresent()){
//            newInvite = optionalInvites.get();
//        }
//        return new ResponseEntity<Invites>(newInvite,HttpStatus.OK);
//
//    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addInviteOrPerson(@RequestBody SpaceMembersDTO spaceMembersDTO){

        int state = spaceMembersService.addMemberOrInvite(spaceMembersDTO);
        if (state==1)
            return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Added"),HttpStatus.CREATED);
        else if (state==2)
            return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Invited"),HttpStatus.CREATED);
        else
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Error"),HttpStatus.CREATED);
    }


    @GetMapping("/{spaceId}")
    public ResponseEntity<List<SpaceMembersDTO>> getMembersBySpaceId(@PathVariable("spaceId") long spaceId){
        List<SpaceMembersDTO> spaceMembers =  spaceMembersService.getAllMembersBySpaceId(spaceId);
        return new ResponseEntity<>(spaceMembers,HttpStatus.OK);
    }

}
