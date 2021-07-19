package com.example.demo.repository;

import com.example.demo.model.Invites;
import com.example.demo.model.Space;
import com.example.demo.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invites, Long> {
    Optional<Invites> findByPhoneNo(String phoneNo);
    Optional<Invites> findByInviteId(Long id);
    Optional<Invites> findBySpaceIdAndPhoneNo(Long spaceId,String phoneNo);
}