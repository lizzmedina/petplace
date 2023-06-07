import { DatePicker } from 'antd';
import { useState } from 'react';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const [dates, setDates] = useState([]);
    console.log(dates);

    return (
        <div style={{ margin: 20 }}>
            <RangePicker
                onChange={(values) => {
                    const startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                    const endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    setDates([startDate, endDate]);
                }}
            />
        </div>
    );
};
