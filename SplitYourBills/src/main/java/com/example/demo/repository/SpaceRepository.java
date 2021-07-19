package com.example.demo.repository;

import com.example.demo.dto.SpaceDTO;
import com.example.demo.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
  List<Space> findAllByPersonId(Long PersonId);

}