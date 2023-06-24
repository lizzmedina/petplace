import React, { useState } from "react";
import { Rating, Typography, TextField, Grid } from '@mui/material';
import { useContextGlobal } from '../utils/global.constext';


export const RatingModalForm = ({ onSubmit }) => {

    const [review, setReview] = useState("");
    const [ratingValue, setRatingValue] = useState(0);
    const [reviewLength, setReviewLength] = useState(0);

    const hideReview = () => {
        // placeholder para comentarios de la calificacion
        return true;
    }

    const limit = 100;
    const handleChange = (e) => {
        if (e.target.value.toString().length <= limit) {
            setReview(e.target.value);
            setReviewLength(e.target.value.toString().length)
        }
    };

  return (
    <form onSubmit={onSubmit}>
      <div className="form-group">
        <Grid container spacing={4} direction="column" className="modal-rating-content">
            <Grid item xs={4}>
                <Typography component="legend"><h1>Valora nuestro servicio</h1></Typography>
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
                <input className="hidden" id="rating" value={ratingValue} onChange={(e) => setRatingValue(e)}/>
            </Grid>
            { hideReview() ? null :
                (<Grid item xs={4}>
                    <TextField
                      type="text"
                      id="outlined-basic"
                      label="Comentarios"
                      variant="outlined"
                      onChange={(e) => handleChange(e)}
                      defaultValue={""}
                      value={review}
                    />
                    <p>{reviewLength}/{limit}</p>
                    <input className="hidden" id="review" defaultValue={review} />
                </Grid>)}
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
