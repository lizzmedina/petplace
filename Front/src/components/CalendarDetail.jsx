import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export const CalendarDetail = () => {
    const datesData = [
        [new Date("2023-06-14"), new Date("2023-06-17")], 
        [new Date("2023-07-23"), new Date("2023-07-27")],
        [new Date("2023-07-10"), new Date("2023-07-16")], 
        [new Date("2023-06-19"), new Date("2023-06-22")],
    ];

    const [dates, setDates] = useState(null);
    const [currentMonth, setCurrentMonth] = useState(new Date().getMonth());

    useEffect(() => {
        const markedMonths = datesData.map(([startDate]) => new Date(startDate).getMonth());
        if (markedMonths.length > 0) {
            const minMonth = Math.min(...markedMonths);
            setCurrentMonth(minMonth);
        }
    }, []);

    const handleDateChange = (date) => {
        setDates(date);
    };

    const tileDisabled = ({ date }) => {
        for (const [startDate, endDate] of datesData) {
            if (date >= startDate && date <= endDate) {
                return true;
            }
        }
        return false;
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
                    <button className="calendar-navigation-button" onClick={handlePrevMonth}>
                        &lt;
                    </button>
                    <Calendar
                        value={dates}
                        onChange={handleDateChange}
                        tileDisabled={tileDisabled}
                        calendarType="ISO 8601"
                        minDetail="month"
                        activeStartDate={new Date(new Date().getFullYear(), currentMonth, 1)}
                    />
                </div>
        
                <div className="calendar">
                <Calendar
                    value={dates}
                    onChange={handleDateChange}
                    tileDisabled={tileDisabled}
                    calendarType="ISO 8601"
                    minDetail="month"
                    activeStartDate={new Date(new Date().getFullYear(), currentMonth + 1, 1)}
                />
                <button className="calendar-navigation-button" onClick={handleNextMonth}>
                    &gt;
                </button>
                </div>
            </div>
        </div>
    );
};
