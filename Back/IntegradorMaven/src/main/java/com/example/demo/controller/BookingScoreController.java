package com.example.demo.controller;

import com.example.demo.DTO.BookingScoreDTO;
import com.example.demo.DTO.BookingScoreReviewDTO;
import com.example.demo.service.BookingScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/booking/score")
public class BookingScoreController {

  @Autowired private BookingScoreService bookingScoreService;

  @PostMapping
  public BookingScoreDTO evaluate(@RequestBody BookingScoreReviewDTO bookingScoreReview) {
    if (bookingScoreReview == null) {
      throw new IllegalArgumentException("El cuerpo de la peticion no puede ser vacio!");
    } else {
      if (bookingScoreReview.getBookingId() == null || bookingScoreReview.getBookingId() < 0) {
        throw new IllegalArgumentException("El id de la reserva asociado debe ser valido");
      }

      if (bookingScoreReview.getScore() == null
          || bookingScoreReview.getScore() < 0 || bookingScoreReview.getScore() > 5) {
        throw new IllegalArgumentException("La calificacion debe ser entre 1 y 5");
      }

      bookingScoreReview.setReview(bookingScoreReview.getReview().trim());
      return bookingScoreService.evaluate(bookingScoreReview);
    }
  }

  @GetMapping("/{bookingId}")
  public BookingScoreDTO getEvaluations(@PathVariable(name = "bookingId") Integer bookingId) {
    return bookingScoreService.getEvaluations(bookingId);
  }
}
