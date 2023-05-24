package com.example.demo.repository;

import com.example.demo.entity.PetDayCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetDayCareRepository extends JpaRepository<PetDayCare, Integer> {
    @Query(value= "SELECT * FROM petplace.pet_day_care where type=:category", nativeQuery = true)
    public List<PetDayCare> findByCategory(@Param("category") String category);


}
