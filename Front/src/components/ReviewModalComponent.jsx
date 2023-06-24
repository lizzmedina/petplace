import React, { useState } from "react";
import "../styles.css";
import { Rating, Typography, TextField, Box } from '@mui/material';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from "reactstrap";
import { useContextGlobal } from './utils/global.constext';

export const ReviewModalComponent = (props) => {
  const {
    userId, bookingId,
    buttonText,
    title,
    actionButtonText,
    cancelButtonText
   } = props;

  const [modal, setModal] = useState(false);
  const [ratingValue, setRatingValue] = useState(false);
  const { urlBookingScore } = useContextGlobal();

  const toggle = () => setModal(!modal);


  const enviarCalificacion = () => {
    console.log("userId: "+userId);
    const ratingBody = {
                          userId: userId,
                          bookingId: bookingId,
                          score: ratingValue,
                          review: review
                         };
    fetch(urlBookingScore, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(ratingBody)
                })
                    .then((response) => response.json())
                    .then((data) => {
                        //Swal.fire({ icon: 'success', title: `Ciudad ${dataCity} actualizada con éxito` });
                        //getAllCitiesDataBase();
                        //closeModal();
                    })
                    .catch((error) => {

                        console.error('Error:', error.message);
                    });
  };

  const closeBtn = (
    <button className="close" onClick={toggle}>
      &times;
    </button>
  );

  const [review, setReview] = useState();
  const limitChar = 100;
  const handleChange = (e) => {
    if (e.target.value.toString().length <= limitChar) {
      setReview(e.target.value);
    }
  };

  return (
    <div className="review-modal-btn">
      <div onClick={toggle}>{buttonText}</div>
      <Modal isOpen={modal} toggle={toggle} className='modal-all'>
        <form>
          <ModalHeader className=" border-0" toggle={toggle} close={closeBtn}>
            {title}
          </ModalHeader>
          <ModalBody className="text-left border-0">
            <Typography component="legend">Calificacion</Typography>
            <Rating
              value={0}
              precision={0.5}
              onChange={(event, newValue) => {
                setRatingValue(newValue);
              }}
            />
                <TextField
                        type="text"
                        id="outlined-basic"
                        label="Comentarios"
                        variant="outlined"
                        onChange={(e) => handleChange(e)}
                        defaultValue={""}
                        value={review}
                      />
          </ModalBody>
          <ModalFooter className="modal-footer border-0">
            <Button className="btn btn_primary modal-btn" onClick={toggle}>
              {cancelButtonText}
            </Button>{" "}
            &nbsp;&nbsp;
            <input
              className="btn btn_primary modal-btn"
              type="button"
              value={actionButtonText}
              onClick={enviarCalificacion}
            />
          </ModalFooter>
        </form>
      </Modal>
    </div>
  );
};

export default ReviewModalComponent;