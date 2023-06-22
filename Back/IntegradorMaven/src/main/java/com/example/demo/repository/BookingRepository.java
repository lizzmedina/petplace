package com.example.demo.repository;

import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.PetDayCare;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value= "SELECT COUNT(id_booking) FROM booking WHERE  pet_day_care_id = :pet_day_care_id AND check_in < :check_out AND check_out > :check_in", nativeQuery = true)
    public Integer disponibilidadQuery(@Param("pet_day_care_id")Integer pet_day_care_id, @Param("check_out") LocalDate check_out, @Param("check_in") LocalDate check_in);

    @Query(value= "select * from pet_day_care h where h.id not in (select h.id FROM booking r join  pet_day_care h  on h.id = r.pet_day_care_id where check_in <= :check_out AND check_out >= :check_in) and h.city_id= :city", nativeQuery = true)
    List<Integer> searchAvailablePetDayCares(@Param("city") Integer city, @Param("check_in") LocalDate checkIn, @Param("check_out") LocalDate checkOut);

    List<Booking> findByPetDayCareId(Integer id);

    List<Booking>findByUserId(Integer idUser);
}
