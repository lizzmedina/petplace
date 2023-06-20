import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export const CalendarDetail = ({ productId }) => {
    const [selectedDates, setSelectedDates] = useState([]);
    const [currentMonth, setCurrentMonth] = useState(new Date().getMonth());
    const [bookingData, setBookingData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
        try {
            const response = await fetch(
            `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/petDayCare/${productId}`
            );
            const data = await response.json();
            setBookingData(data);
        } catch (error) {
            console.error("Error fetching booking data:", error);
        }
        };

        fetchData();
    }, [productId]);

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
        setSelectedDates(date);
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
                selectRange
                value={selectedDates}
                onChange={handleDateChange}
                tileDisabled={tileDisabled}
                calendarType="ISO 8601"
                minDetail="month"
                activeStartDate={new Date(
                new Date().getFullYear(),
                currentMonth,
                1
                )}
                prev2Label={null}
                next2Label={null}
                nextLabel={null}
                prevLabel={null}
                navigationLabel={({ date }) =>
                `${new Intl.DateTimeFormat("es", {
                    month: "long",
                    year: "numeric",
                }).format(date)}`
                }
            />

            </div>

            <div className="calendar">
            <Calendar
                selectRange
                value={selectedDates}
                onChange={handleDateChange}
                tileDisabled={tileDisabled}
                calendarType="ISO 8601"
                minDetail="month"
                activeStartDate={new Date(
                new Date().getFullYear(),
                currentMonth + 1,
                1
                )}
                prev2Label={null}
                next2Label={null}
                nextLabel={null}
                prevLabel={null}
                navigationLabel={({ date }) =>
                `${new Intl.DateTimeFormat("es", {
                    month: "long",
                    year: "numeric",
                }).format(date)}`
                }
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
