import React, { useState } from "react";
import { Rating, Typography, TextField, Grid } from "@mui/material";

export const RatingModalForm = ({ onSubmit }) => {
  const [review, setReview] = useState("");
  const [ratingValue, setRatingValue] = useState(0);
  const [reviewLength, setReviewLength] = useState(0);

  const limit = 100;
  const handleChange = (e) => {
    if (e.target.value.toString().length <= limit) {
      setReview(e.target.value);
      setReviewLength(e.target.value.toString().length);
    }
  };

  const hideReviewBox = () => {
    return true;
  };

  return (
    <form onSubmit={onSubmit}>
      <div className="form-group">
        <Grid
          container
          spacing={4}
          direction="column"
          className="modal-rating-content"
        >
          <Grid item xs={4}>
            <Typography component="legend">
              <h1>Valora nuestro servicio</h1>
            </Typography>
          </Grid>
          <Grid item xs={4}>
            <Rating
              value={ratingValue}
              precision={1}
              onChange={(event, newValue) => {
                setRatingValue(newValue);
              }}
              className="large-star"
            />
            <input
              className="hidden"
              id="rating"
              value={ratingValue}
              onChange={(e) => setRatingValue(e)}
            />
          </Grid>
          {hideReviewBox() ? null : (
            <Grid item xs={4}>
              <TextField
                type="text"
                id="outlined-basic"
                label="Dejanos tus comentarios!"
                variant="outlined"
                onChange={(e) => handleChange(e)}
                defaultValue={""}
                value={review}
                multiline
              />
              <p>
                {reviewLength}/{limit}
              </p>
              <input
                className="hidden"
                id="review"
                defaultValue={review}
                onChange={(e) => setReview(e)}
              />
            </Grid>
          )}
          <Grid item xs={4}>
            <button className="btn button-2 btn-calificar" type="submit">
              Calificar
            </button>
          </Grid>
        </Grid>
      </div>
    </form>
  );
};
export default RatingModalForm;
