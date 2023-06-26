package com.example.demo.DTO;

import java.util.List;

public class RatingDTO {
    private Integer petDayCareId;
    private Integer amountOfReviews;
    private Double average;
    private List<BookingScoreReviewDTO> ratings;

    public RatingDTO() {
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

    public List<BookingScoreReviewDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<BookingScoreReviewDTO> ratings) {
        this.amountOfReviews = ratings.size();
        this.ratings = ratings;
    }

    public Integer getPetDayCareId() {
        return petDayCareId;
    }

    public void setPetDayCareId(Integer petDayCareId) {
        this.petDayCareId = petDayCareId;
    }

    public void setAmountOfReviews(Integer amountOfReviews) {
        this.amountOfReviews = amountOfReviews;
    }
}
