package com.example.demo.controller;

import com.example.demo.DTO.RatingDTO;
import com.example.demo.DTO.BookingScoreReviewDTO;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/booking/rating")
public class RatingController {

  @Autowired private RatingService ratingService;

  @PostMapping
  public RatingDTO evaluate(@RequestBody BookingScoreReviewDTO bookingScoreReview) {
    if (bookingScoreReview == null) {
      throw new IllegalArgumentException("El cuerpo de la peticion no puede ser vacio!");
    } else {
      if (bookingScoreReview.getUserId() == null) {
        throw new IllegalArgumentException("El id del usuario debe ser valido");
      }

      if (bookingScoreReview.getBookingId() == null || bookingScoreReview.getBookingId() < 0) {
        throw new IllegalArgumentException("El id de la reserva asociado debe ser valido");
      }

      if (bookingScoreReview.getScore() == null
          || bookingScoreReview.getScore() < 0 || bookingScoreReview.getScore() > 5) {
        throw new IllegalArgumentException("La calificacion debe ser entre 1 y 5");
      }

      if (bookingScoreReview.getReview() != null){
        bookingScoreReview.setReview(bookingScoreReview.getReview().trim());
      }

      return ratingService.evaluate(bookingScoreReview);
    }
  }

  @GetMapping("/{bookingId}")
  public RatingDTO getEvaluations(@PathVariable(name = "bookingId") Integer bookingId) {
    return ratingService.getRatingsByBooking(bookingId);
  }
}
