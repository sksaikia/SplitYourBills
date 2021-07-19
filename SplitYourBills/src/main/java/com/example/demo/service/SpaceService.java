package com.example.demo.service;

import com.example.demo.dto.AddNewSpaceDTO;
import com.example.demo.model.Space;
import com.example.demo.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    public SpaceService() {
    }

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }


    public void addSpace(AddNewSpaceDTO spaceDTO,long userId){
        Space space = getSpaceFromDTO(spaceDTO,userId);
        spaceRepository.save(space);
    }

    private Space getSpaceFromDTO(AddNewSpaceDTO spaceDTO, long userId) {
        Space space = new Space(spaceDTO,userId);
        return space;
    }


}


