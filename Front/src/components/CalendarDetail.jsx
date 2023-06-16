import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export const CalendarDetail = ({productId }) => {
    const [dates, setDates] = useState([]);
    const [currentMonth, setCurrentMonth] = useState(new Date().getMonth());
    const [bookingData, setBookingData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/petDayCare/${productId}`);
            const data = await response.json();
            setBookingData(data);
        } catch (error) {
            console.error("Error fetching booking data:", error);
        }
        };

        fetchData();
    }, [productId]);

    useEffect(() => {
        const markedDates = bookingData.map((booking) => ({
        startDate: new Date(booking.checkInCheckOut[0]),
        endDate: new Date(booking.checkInCheckOut[1]),
        }));
        setDates(markedDates);
    }, [bookingData]);

    useEffect(() => {
        const markedMonths = bookingData.map(
        (booking) => new Date(booking.checkInCheckOut[0]).getMonth()
        );
        if (markedMonths.length > 0) {
        const minMonth = Math.min(...markedMonths);
        setCurrentMonth(minMonth);
        }
    }, [bookingData]);

    const tileDisabled = ({ date }) => {
        for (const booking of bookingData) {
            const startDate = new Date(booking.checkInCheckOut[0]);
            const endDate = new Date(booking.checkInCheckOut[1]);
            endDate.setDate(endDate.getDate() + 1);
            if (date >= startDate && date < endDate) {
                return true; 
            }
        }
        return false; 
    };

    const handleDateChange = (date) => {
        setDates(date);
    };

    const handlePrevMonth = () => {
        setCurrentMonth((prevMonth) => prevMonth - 1);
    };

    const handleNextMonth = () => {
        setCurrentMonth((prevMonth) => prevMonth + 1);
    };

    return (
        <div className="calendar-section-container">
        <h3>Fechas disponibles</h3>
        <div className="calendars-render">
            <div className="calendar">
            <button
                className="calendar-navigation-button"
                onClick={handlePrevMonth}
            >
                &lt;
            </button>
            <Calendar
                value={new Date()}
                onChange={handleDateChange}
                tileDisabled={tileDisabled}
                calendarType="ISO 8601"
                minDetail="month"
                activeStartDate={new Date(
                new Date().getFullYear(),
                currentMonth,
                1
                )}
            />
            </div>

            <div className="calendar">
            <Calendar
                value={new Date()}
                onChange={handleDateChange}
                tileDisabled={tileDisabled}
                calendarType="ISO 8601"
                minDetail="month"
                activeStartDate={new Date(
                new Date().getFullYear(),
                currentMonth + 1,
                1
                )}
            />
            <button
                className="calendar-navigation-button"
                onClick={handleNextMonth}
            >
                &gt;
            </button>
            </div>
        </div>
        </div>
    );
};
