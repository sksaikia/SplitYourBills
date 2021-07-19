package com.example.demo.controller;

import com.example.demo.dto.AddNewSpaceDTO;
import com.example.demo.model.Invites;
import com.example.demo.payload.ApiResponse;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.InvitesService;
import com.example.demo.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/invites")
public class InvitesController {
    @Autowired
    private InvitesService invitesService;


    @GetMapping("/{inviteId}")
    public ResponseEntity<Invites> getInviteById(@PathVariable("inviteId") long inviteId, @CurrentUser UserPrincipal currentUser){
        Optional<Invites> optionalInvites = invitesService.getInviteById(inviteId);
        Invites newInvite = null;
        if (optionalInvites.isPresent()){
            newInvite = optionalInvites.get();
        }
        return new ResponseEntity<Invites>(newInvite,HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Invites> addInvites(@RequestBody Invites invites){
        invitesService.addInvite(invites);
        return new ResponseEntity<Invites>(invites,HttpStatus.CREATED);
    }

}
