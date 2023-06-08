import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';

export const CalendarDetail = () => {
    const datesData = [
        ["2023-06-12", "2023-06-15"],
        ["2023-07-23", "2023-07-26"],
        ["2023-07-10", "2023-07-15"],
        ["2023-06-19", "2023-06-21"],
    ];

    const [dates, setDates] = useState([]);
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
        const currentDate = new Date(date);
        if (currentDate >= new Date(startDate) && currentDate <= new Date(endDate)) {
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
        <h2>Calendario</h2>
        <div className="calendars-render">
            <div className="calendar">
            <button className="calendar-navigation-button" onClick={handlePrevMonth}>
                <ChevronLeftIcon />
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
            <button className="calendar-navigation-button" onClick={handleNextMonth}>
                <ChevronRightIcon />
            </button>
            <Calendar
                value={dates}
                onChange={handleDateChange}
                tileDisabled={tileDisabled}
                calendarType="ISO 8601"
                minDetail="month"
                activeStartDate={new Date(new Date().getFullYear(), currentMonth + 1, 1)}
            />
            </div>
        </div>
        </div>
    );
};
