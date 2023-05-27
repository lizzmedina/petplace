package com.example.demo.repository;

import com.example.demo.entity.PetDayCare;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetDayCareRepository extends JpaRepository<PetDayCare, Integer> {
    public List<PetDayCare> findByTypeId(Integer id);

    public List<PetDayCare> findByName(String namePetDayCare);


}
