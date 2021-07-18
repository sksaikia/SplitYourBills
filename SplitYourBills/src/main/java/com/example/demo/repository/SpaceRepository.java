package com.example.demo.repository;

import com.example.demo.model.Space;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
  List<Space> findAllByUserId(Long userId);
}