package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.BookingScoreDTO;
import com.example.demo.DTO.BookingScoreReviewDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingScore;
import com.example.demo.exception.ExistingEvaluationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.BookingScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingScoreService {
  private BookingRepository bookingRepository;
  private BookingScoreRepository bookingScoreRepository;

  @Autowired
  public BookingScoreService(
      BookingRepository bookingRepository, BookingScoreRepository bookingScoreRepository) {
    this.bookingRepository = bookingRepository;
    this.bookingScoreRepository = bookingScoreRepository;
  }

  public BookingScoreDTO evaluate(BookingScoreReviewDTO scoreReview) {
    Booking booking = findBookingScoreByBookingId(scoreReview.getBookingId());

    if(!scoreReview.getUserId().equals(booking.getUser().getId())){
      throw new IllegalArgumentException(
              String.format("El usuario que va a calificar (%s), NO coincide con el usuario (%s) de la reserva (%s)",
                      scoreReview.getUserId(),
                      booking.getUser().getId(),
                      booking.getIdBooking()));
    }

    boolean alreadyEvaluated = bookingScoreRepository.findByBookingScoreIdUserIdAndBookingScoreIdBooking(scoreReview.getUserId(), booking).isPresent();

    if(alreadyEvaluated){
      throw new ExistingEvaluationException(scoreReview.getUserId(), booking.getPetDayCare().getName());
    }

    BookingScore bookingScore = new BookingScore();
    bookingScore.setUserId(scoreReview.getUserId());
    bookingScore.setBooking(booking);
    bookingScore.setScore(scoreReview.getScore());
    bookingScore.setReview(scoreReview.getReview());
    bookingScoreRepository.save(bookingScore);

    return mapFromBooking(booking);
  }

  public BookingScoreDTO getEvaluations(Integer bookingId) {
    Booking booking = findBookingScoreByBookingId(bookingId);
    return mapFromBooking(booking);
  }

  public Double getAverageScore(Integer petDayCareId) {
    List<Booking> bookings = bookingRepository.findByPetDayCareId(petDayCareId);

    return bookingScoreRepository.findByBookingScoreIdBookingIn(bookings).stream()
        .map(BookingScore::getScore)
        .collect(Collectors.summarizingDouble(Double::valueOf))
        .getAverage();
  }

  private Double getAverageScore(Booking booking) {
    return bookingScoreRepository.findAllByBookingScoreIdBooking(booking).stream()
        .map(BookingScore::getScore)
        .collect(Collectors.summarizingDouble(Double::valueOf))
        .getAverage();
  }

  private List<BookingScoreReviewDTO> getBookingScoreEvaluations(Integer userId, Booking booking) {
    return bookingScoreRepository.findAllByBookingScoreIdBooking(booking).stream()
        .map(bk -> new BookingScoreReviewDTO(userId, booking.getIdBooking(), bk.getScore(), bk.getReview()))
        .toList();
  }

  private Booking findBookingScoreByBookingId(Integer id) {
    Optional<Booking> bookingOpt = bookingRepository.findById(id);
    return bookingOpt.orElseThrow(
        () -> new ResourceNotFoundException("No existe la reserva con id: " + id));
  }

  private BookingScoreDTO mapFromBooking(Booking booking) {
    BookingScoreDTO bookingScoreDTO = new BookingScoreDTO();
    bookingScoreDTO.setAverage(getAverageScore(booking));
    bookingScoreDTO.setBookingScoreReviews(getBookingScoreEvaluations(booking.getUser().getId(), booking));
    bookingScoreDTO.setBooking(new BookingDTO(booking));

    return bookingScoreDTO;
  }
}
