package com.example.demo.repository;

import com.example.demo.model.SpaceMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceMembersRepository extends JpaRepository<SpaceMembers, Long> {
    List<SpaceMembers> findAllBySpaceId(Long spaceId);
    Boolean existsBySpaceId(Long spaceId);
}