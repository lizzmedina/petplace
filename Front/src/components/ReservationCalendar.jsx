import { DatePicker } from 'antd';
import  {SearchContext}  from "../components/utils/SearchContext";
import { useContext } from 'react';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {

    const { dates, handleDateChange } = useContext(SearchContext);

    return (
        <div style={{ margin: 20 }}>
            <RangePicker
            onChange={handleDateChange}
            />
        </div>
    );
};
