package com.example.demo.service;

import com.example.demo.DTO.RatingDTO;
import com.example.demo.DTO.BookingScoreReviewDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.BookingScore;
import com.example.demo.exception.ExistingEvaluationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.BookingScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
  private BookingRepository bookingRepository;
  private BookingScoreRepository bookingScoreRepository;

  @Autowired
  public RatingService(
      BookingRepository bookingRepository, BookingScoreRepository bookingScoreRepository) {
    this.bookingRepository = bookingRepository;
    this.bookingScoreRepository = bookingScoreRepository;
  }

  public RatingDTO evaluate(BookingScoreReviewDTO scoreReview) {
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

  public RatingDTO getRatingsByPetDayCare(Integer petDayCareId) {
    List<Booking> bookings = bookingRepository.findByPetDayCareId(petDayCareId);
    List<BookingScore> bookingRatings = bookingScoreRepository.findByBookingScoreIdBookingIn(bookings);

    var stats = bookingRatings.isEmpty() ? null : bookingRatings.stream()
            .map(BookingScore::getScore)
            .collect(Collectors.summarizingDouble(Double::valueOf));

    if(stats == null){
      return null;
    }

    RatingDTO rating = new RatingDTO();
    rating.setAverage(roundOneDecimal(stats.getAverage()));
    rating.setPetDayCareId(petDayCareId);
    rating.setRatings(getReviewsByBookingList(bookings));
    rating.setAmountOfReviews(bookingRatings.size());

    return rating;
  }

  public RatingDTO getRatingsByBooking(Integer bookingId) {
    Booking booking = findBookingScoreByBookingId(bookingId);
    return mapFromBooking(booking);
  }

  public Double getAverageScore(Integer petDayCareId) {
    List<Booking> bookings = bookingRepository.findByPetDayCareId(petDayCareId);

    List<BookingScore> bookingRatings = bookingScoreRepository.findByBookingScoreIdBookingIn(bookings);

    var avg = bookingRatings.isEmpty() ? null : bookingRatings.stream()
        .map(BookingScore::getScore)
        .collect(Collectors.summarizingDouble(Double::valueOf))
        .getAverage();

    return avg == null ? null : roundOneDecimal(avg);
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

  private List<BookingScoreReviewDTO> getReviewsByBookingList(List<Booking> bookings) {
    return bookingScoreRepository.findByBookingScoreIdBookingIn(bookings).stream()
            .map(bk -> new BookingScoreReviewDTO(bk.getUserId(), bk.getBookingId(), bk.getScore(), bk.getReview()))
            .toList();
  }

  private Booking findBookingScoreByBookingId(Integer id) {
    Optional<Booking> bookingOpt = bookingRepository.findById(id);
    return bookingOpt.orElseThrow(
        () -> new ResourceNotFoundException("No existe la reserva con id: " + id));
  }

  private RatingDTO mapFromBooking(Booking booking) {
    RatingDTO ratingDTO = new RatingDTO();
    ratingDTO.setPetDayCareId(booking.getPetDayCare().getId());
    ratingDTO.setAverage(getAverageScore(booking));
    ratingDTO.setRatings(getBookingScoreEvaluations(booking.getUser().getId(), booking));

    return ratingDTO;
  }

  private Double roundOneDecimal(Double value) {
    return BigDecimal.valueOf(value).setScale(1, RoundingMode.HALF_UP).doubleValue();
  }
}
