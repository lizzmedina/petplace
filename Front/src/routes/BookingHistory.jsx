import React, { useEffect } from "react";
import { useContextGlobal } from "../components/utils/global.constext";
import RatingComponent from "../components/rating/RatingComponent.jsx";
import { Grid, Rating } from '@mui/material';

export const BookingHistory = () => {
    const { bookingHistory, setBookingHistory, urlBookingHistory } = useContextGlobal();
    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;
    const userId = userConnected.id;

    const getBookingHistory = async () => {
        try {
            const res = await fetch(`${urlBookingHistory}${userId}`);
            const data = await res.json();
            if (Array.isArray(data)) {
                    data.sort((a, b) => new Date(b.creationDate) - new Date(a.creationDate));
                    setBookingHistory(data);
                } else {
                    setBookingHistory([]);
                }
            } catch (error) {
                console.error('Error al obtener el historial de reservas:', error);
                setBookingHistory([]);
            }
    };

    useEffect(() => {
        getBookingHistory();
    }, []);

    const formatCreationDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}-${month}-${day}`;
    };

    return (
        <div className="space-section booking-history-container">
            <h2>Historial de reservas</h2>
            <div className="container-booking-history">
            {userConnected.validation === true && (
                <>
                {bookingHistory.length === 0 ? (
                    <h3>Aún no has agregado ninguno de nuestros hospedajes como favorito.</h3>
                ) : (
                    <table className="table-booking-history">
                    <thead>
                        <tr>
                        <th className="booking-cell">Fecha de Reserva</th>
                        <th className="booking-cell">Alojamiento</th>
                        <th className="booking-cell">Check-in</th>
                        <th className="booking-cell">Check-out</th>
                        <th className="booking-cell">Mascota</th>
                        <th className="booking-cell">Precio pagado</th>
                        <th className="booking-cell">Calificación</th>
                        </tr>
                    </thead>
                    <tbody>
                        {bookingHistory.map((booking, index) => {
                            const { checkIn, checkOut, dataPet, petDayCare, totalPrice, creationDate } = booking;
                            const checkInDate = checkIn && checkIn.join('-');
                            const checkOutDate = checkOut && checkOut.join('-');
                            const dataPetName = dataPet && dataPet[0];
                            const petDayCareName = petDayCare && petDayCare.name;
                            const formattedCreationDate = formatCreationDate(creationDate);
                            const key = `booking_${index}`;
                        return (
                            <tr key={key}>
                            <td className="booking-cell">{formattedCreationDate}</td>
                            <td className="booking-cell">{petDayCareName}</td>
                            <td className="booking-cell">{checkInDate}</td>
                            <td className="booking-cell">{checkOutDate}</td>
                            <td className="booking-cell">{dataPetName}</td>
                            <td className="booking-cell">{totalPrice}</td>
                            {booking.score ? (
                                <td className="booking-cell">
                                <Grid container direction="row" spacing={0} className="align-items-center">
                                    <Rating className="rating-value-star" defaultValue={booking.score} max={5} readOnly />
                                </Grid>
                                </td>
                            ) : (
                                <td className="booking-cell">
                                <RatingComponent booking={booking} dataRef={getBookingHistory} />
                                </td>
                            )}
                            </tr>
                        );
                        })}
                    </tbody>
                    </table>
                )}
                </>
            )}
            </div>
        </div>
    );
};
