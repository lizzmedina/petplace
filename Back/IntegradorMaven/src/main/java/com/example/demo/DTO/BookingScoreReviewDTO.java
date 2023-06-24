package com.example.demo.DTO;

public class BookingScoreReviewDTO {
    private Integer bookingId;
    private Integer score;
    private String review;

    public BookingScoreReviewDTO(Integer bookingId, Integer score, String review) {
        this.bookingId = bookingId;
        this.score = score;
        this.review = review;
    }

    public BookingScoreReviewDTO() {
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
