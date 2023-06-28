import React, { useEffect } from "react";
import { useContextGlobal } from "../components/utils/global.constext";
import RatingComponent from "../components/rating/RatingComponent.jsx"
import { Grid, Rating } from '@mui/material';

export const BookingHistory = () => {
    const { bookingHistory, setBookingHistory, urlBookingHistory } = useContextGlobal();
    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;
    const userId = userConnected.id;

    const getBookingHistory = async () => {
        const res = await fetch(`${urlBookingHistory}${userId}`);
        const data = await res.json();
        setBookingHistory(data);
    };

    useEffect(() => {
        getBookingHistory();
    }, []);


console.log(bookingHistory);


    return (
        <div className="space-section">
            <h2>Historial de reservas</h2>
            {userConnected.validation === true &&  (
                <table className="booking-item">
                <thead>
                    <tr>
                    <th className="booking-cell">Alojamiento</th>
                    <th className="booking-cell" >Check-in</th>
                    <th className="booking-cell">Check-out</th>
                    <th className="booking-cell">Nombre de Mascota</th>                    
                    <th className="booking-cell">Precio pagado</th>
                    <th className="booking-cell">Calificación</th>
                    </tr>
                </thead>
                <tbody>
                    {bookingHistory.map((booking, index) => {
                    const { checkIn, checkOut, dataPet, petDayCare, totalPrice } = booking;
                    const checkInDate = checkIn && checkIn.join('-');
                    const checkOutDate = checkOut && checkOut.join('-');
                    const dataPetName = dataPet && dataPet[0];
                    const petDayCareName = petDayCare && petDayCare.name;
                    const key = `booking_${index}`;

                    return (
                        <tr key={key}>
                            <td className="booking-cell">{petDayCareName}</td>
                            <td className="booking-cell">{checkInDate}</td>
                            <td className="booking-cell">{checkOutDate}</td>
                            <td className="booking-cell">{dataPetName}</td>                            
                            <td className="booking-cell">{totalPrice}</td>
                            { booking.score ?
                             <td className="booking-cell">
                                 <Grid container direction="row" spacing={0} className="align-items-center">
                                     <Rating className="rating-value-star" defaultValue={booking.score} max={5} readOnly/>
                                 </Grid>
                             </td>
                             : <td className="booking-cell"><RatingComponent booking={booking} dataRef={getBookingHistory}/></td>
                            }
                        </tr>
                    );
                    })}
                </tbody>
                </table>
            )}
        </div>
    );
}