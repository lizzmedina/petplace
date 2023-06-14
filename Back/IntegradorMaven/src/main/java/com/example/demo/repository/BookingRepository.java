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

    @Query(value= "SELECT COUNT(DISTINCT check_in) FROM booking WHERE check_in < :check_out AND check_out > :check_in", nativeQuery = true)
    public Integer disponibilidadQuery(@Param("check_out") LocalDate check_out, @Param("check_in") LocalDate check_in);

    @Query(value= "select * FROM  pet_day_care h left join booking  r on h.id = r.id_booking where h.city_id = :city  and ((not (check_in <= :check_out AND check_out >= :check_in)) or  r.check_in is null);", nativeQuery = true)
    List<Integer> searchAvailablePetDayCares(@Param("city") Integer city, @Param("check_in") LocalDate checkIn, @Param("check_out") LocalDate checkOut);

//    Optional<Booking> findByPetDayCareId(Integer id);

    List<Booking> findByPetDayCareId(Integer id);
}
