package com.example.demo.DTO;

import java.util.List;

public class BookingScoreDTO {
    private BookingDTO booking;
    private Integer amountOfReviews;
    private Double average;
    private List<BookingScoreReviewDTO> bookingScoreReviews;

    public BookingScoreDTO() {
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public Integer getAmountOfReviews() {
        return amountOfReviews;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public List<BookingScoreReviewDTO> getBookingScoreReviews() {
        return bookingScoreReviews;
    }

    public void setBookingScoreReviews(List<BookingScoreReviewDTO> bookingScoreReviews) {
        this.amountOfReviews = bookingScoreReviews.size();
        this.bookingScoreReviews = bookingScoreReviews;
    }
}
