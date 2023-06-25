package com.example.demo.repository;

import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingScoreRepository extends JpaRepository<BookingScore, BookingScore.BookingScoreId> {

    List<BookingScore> findAllByBookingScoreIdBooking(Booking booking);

    List<BookingScore> findByBookingScoreIdBookingIn(List<Booking> bookings);

    Optional<BookingScore> findByBookingScoreIdUserIdAndBookingScoreIdBooking(Integer userId, Booking booking);

    List<BookingScore> findByBookingScoreIdUserIdAndBookingScoreIdBookingIn(Integer userId, List<Booking> bookings);


}
