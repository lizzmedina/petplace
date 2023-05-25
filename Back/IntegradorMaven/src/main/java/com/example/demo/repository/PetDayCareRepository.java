package com.example.demo.repository;

import com.example.demo.entity.PetDayCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetDayCareRepository extends JpaRepository<PetDayCare, Integer> {
    @Query(value= "select * from petplace.pet_day_care  where type_id=:id_category", nativeQuery = true)
    public List<PetDayCare> findByCategory(@Param("id_category") Integer id_category);

}
