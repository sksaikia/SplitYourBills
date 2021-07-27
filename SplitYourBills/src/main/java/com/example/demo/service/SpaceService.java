package com.example.demo.service;

import com.example.demo.dto.Spaces.AddNewSpaceDTO;
import com.example.demo.dto.Spaces.SpaceDTO;
import com.example.demo.model.Space;
import com.example.demo.model.SpaceMembers;
import com.example.demo.model.User.User;
import com.example.demo.payload.SpaceResponse;
import com.example.demo.repository.SpaceMembersRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private SpaceMembersRepository spaceMembersRepository;

    @Autowired
    private UserRepository userRepository;

    public SpaceService() {
    }

    public SpaceResponse addSpace(AddNewSpaceDTO spaceDTO, long userId){
        Space space = getSpaceFromDTO(spaceDTO,userId);
        spaceRepository.save(space);
        long id =  space.getSpaceId();
        String name = space.getSpaceName();
        SpaceResponse spaceResponse = new SpaceResponse(id,name);
        addPersonToMembers(spaceDTO,userId,id);
        return spaceResponse;

    }
    public void addPersonToMembers(AddNewSpaceDTO spaceDTO, long userId,long spaceId){
        String phoneNo = "";
        if (userRepository.existsById(userId)){
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()){
                User currUser = optionalUser.get();
                phoneNo = currUser.getPhoneNo();
            }

        }

        SpaceMembers spaceMembers = new SpaceMembers(spaceId,userId,phoneNo);
        spaceMembersRepository.save(spaceMembers);
    }


    private Space getSpaceFromDTO(AddNewSpaceDTO spaceDTO, long userId) {
        Space space = new Space(spaceDTO,userId);
        return space;
    }
    public List<SpaceDTO> getSpacesByPersonId(long userId){
        List<Space> spaces =  spaceRepository.findAllByPersonId(userId);
        List<SpaceDTO> spaceDTOS = new ArrayList<>();
        for (Space s:spaces){
            spaceDTOS.add(convertSpaceToDTO(s));
        }
        return spaceDTOS;


    }

    private SpaceDTO convertSpaceToDTO(Space s){
        SpaceDTO spaceDTO = new SpaceDTO(s);
        return spaceDTO;
    }


}


