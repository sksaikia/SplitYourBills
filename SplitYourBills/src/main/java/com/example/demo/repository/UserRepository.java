package com.example.demo.repository;

import com.example.demo.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByIdIn(List<Long> userIds);

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneno(String phoneNo);

    Optional<User> findByPhoneno(String phoneNo);

}