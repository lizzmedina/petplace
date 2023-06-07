package com.example.demo.repository;

import com.example.demo.entity.PetDayCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PetDayCareRepository extends JpaRepository<PetDayCare, Integer> {
    List<PetDayCare> findByTypeId(Integer id);

    List<PetDayCare> findByName(String namePetDayCare);
}
