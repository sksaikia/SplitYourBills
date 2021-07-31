package com.example.demo.repository;

import com.example.demo.model.SpaceMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceMembersRepository extends JpaRepository<SpaceMembers, Long> {
    List<SpaceMembers> findAllBySpaceId(Long spaceId);
    Boolean existsBySpaceId(Long spaceId);
    Optional<SpaceMembers> findBySpaceIdAndPersonId(Long spaceId,Long personId);
    Optional<SpaceMembers> findByInviteId(Long inviteId);
}