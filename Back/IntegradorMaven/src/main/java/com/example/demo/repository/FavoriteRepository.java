package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findAllByUserId(Integer userId);

    @Query(value= "select id_favorite from favorite where pet_day_care_id = :pet_day_care_id and user_id = :user_id", nativeQuery = true)
    Integer searchInFavorites(@Param("pet_day_care_id") Integer petDayCareId, @Param("user_id") Integer userId);


}
