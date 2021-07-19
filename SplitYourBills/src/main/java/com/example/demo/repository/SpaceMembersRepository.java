package com.example.demo.repository;

import com.example.demo.model.Role.Role;
import com.example.demo.model.Role.RoleName;
import com.example.demo.model.SpaceMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceMembersRepository extends JpaRepository<SpaceMembers, Long> {

}