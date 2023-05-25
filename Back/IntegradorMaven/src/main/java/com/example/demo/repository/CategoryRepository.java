package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.PetDayCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value= "SELECT * FROM petplace.category where name=:category", nativeQuery = true)
    public Optional<Category> findCategory(@Param("category") String category);

}
