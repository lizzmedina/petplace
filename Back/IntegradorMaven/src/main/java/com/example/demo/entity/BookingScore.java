package com.example.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class BookingScore {

    @EmbeddedId
    private BookingScoreId bookingScoreId;

    @Column
    private Integer score;

    @Column
    private String review;

    public BookingScore(){
        this.bookingScoreId = new BookingScoreId();
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

    public Integer getUserId() {
        return bookingScoreId.userId;
    }

    public void setUserId(Integer userId) {
        this.bookingScoreId.userId = userId;
    }

    public Booking getBooking() {
        return bookingScoreId.booking;
    }

    public void setBooking(Booking booking) {
        this.bookingScoreId.booking = booking;
    }

    @Embeddable
    public static class BookingScoreId implements Serializable {
        private Integer userId;
        @ManyToOne
        private Booking booking;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookingScoreId that = (BookingScoreId) o;
            return Objects.equals(userId, that.userId) && Objects.equals(booking, that.booking);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, booking);
        }
    }
}
