package com.example.demo.controller;

import com.example.demo.dto.Member.AddSpaceMemberDTO;
import com.example.demo.dto.Member.SpaceMembersDTO;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.SpaceMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse> addInviteOrPerson(@RequestBody AddSpaceMemberDTO addSpaceMemberDTO){

        int state = spaceMembersService.addMemberOrInvite(addSpaceMemberDTO);
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
