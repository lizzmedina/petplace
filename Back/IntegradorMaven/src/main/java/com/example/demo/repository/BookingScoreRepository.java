package com.example.demo.repository;

import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingScoreRepository extends JpaRepository<BookingScore, Integer> {

    List<BookingScore> findAllByBooking(Booking booking);
}
