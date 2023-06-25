import React, { useState } from "react";
import ModalContainer from "./ModalContainer.jsx"
import { useContextGlobal } from '../utils/global.constext';
import Swal from 'sweetalert2';


export const RatingComponent = ({booking, dataRef}) => {
  const getUserId = () => {
      const userStr = localStorage.getItem('userConnected');
      if(userStr === undefined || userStr === null){
        return null;
      } else {
        return JSON.parse(userStr).id;
      }
  }
  const { urlBookingScore } = useContextGlobal();
  const calificarAction = (event) => {
    event.preventDefault(event);
    enviarCalificacion(parseInt(event.target.rating.value));
  };

  const enviarCalificacion = (ratingValue) => {
    const ratingBody = {
                          userId: getUserId(),
                          bookingId: booking.idBooking,
                          score: ratingValue,
                          review: null
                         };
    fetch(urlBookingScore,
            {
                method: 'POST',
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(ratingBody)
            })
    .then((response) => response.json())
    .then((data) => {
        document.getElementById("close-modal").click();
        if(data.status !== undefined && data.status !== 200){
            Swal.fire({icon: 'error',title:`Ups! ${data.message}`});
        }
        dataRef();
    });
  }

  return (
    <div>
      <ModalContainer triggerText="Calificar" onSubmit={calificarAction}/>
    </div>
  );
};

export default RatingComponent;