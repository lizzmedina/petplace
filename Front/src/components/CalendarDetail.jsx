import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import { eachDayOfInterval, addDays } from "date-fns";
import "react-datepicker/dist/react-datepicker.css";

export const CalendarDetail = ({ productId }) => {

    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [reservations, setReservations] = useState([]);

    const fetchData = async () => {
        try {
            const response = await fetch(
            `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/petDayCare/${productId}`
            );
            if (response.ok) {
                const data = await response.json();
                setReservations(data);
            } else {
                console.error("Error al obtener los datos:", response.status);
            }
        } catch (error) {
            console.error("Error al obtener los datos:", error);
        }
    };

    const getLocalStorageDate = () => {
        
        let localStartDate = localStorage.getItem('localStartDate');
        let startDate = localStartDate ??  null;
        
        let localEndDate = localStorage.getItem('localEndDate');
        let endDate = localEndDate ??  null;

        if (startDate) {
            let newStartDate = new Date(startDate);
            newStartDate.setDate(newStartDate.getDate() + 1);
            setStartDate(newStartDate);
        }
        
        if (endDate) {
            let newEndDate = new Date(endDate);
            newEndDate.setDate(newEndDate.getDate() + 1);
            setEndDate(newEndDate);
        }
    }

    useEffect(() => {
        fetchData();
        getLocalStorageDate();
    }, []);


    const disabledDates = reservations.flatMap((reservation) => {
        const [checkIn, checkOut] = reservation.checkInCheckOut;
        const startDate = new Date(checkIn);
        const endDate = new Date(checkOut);
        const range = eachDayOfInterval({
            start: addDays(startDate, 1),
            end: addDays(endDate, 1),
        });
        return range;
    });

    const onChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
        localStorage.setItem("selectedDate", start);
    };

    return (
        <div className="calendar-section-container">
            <h3>Fechas disponibles</h3>
            <div className="calendars-render">
                <div className="calendar">
                    <DatePicker
                    selected={startDate}
                    onChange={onChange}
                    startDate={startDate}
                    endDate={endDate}
                    monthsShown={2}
                    excludeDates={disabledDates}
                    selectsRange
                    inline
                    />
                </div>
            </div>
        </div>
        );
};
