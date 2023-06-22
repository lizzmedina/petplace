package com.example.demo.DTO;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        this.average = Double.parseDouble(df.format(average).replace(",", "."));
    }

    public List<BookingScoreReviewDTO> getBookingScoreReviews() {
        return bookingScoreReviews;
    }

    public void setBookingScoreReviews(List<BookingScoreReviewDTO> bookingScoreReviews) {
        this.amountOfReviews = bookingScoreReviews.size();
        this.bookingScoreReviews = bookingScoreReviews;
    }
}
