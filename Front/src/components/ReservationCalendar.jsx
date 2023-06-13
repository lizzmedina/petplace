import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {

    const { dates, handleDateChange } = useContextGlobal();

    return (
        <div style={{margin: 5, width: 270,  }}>
            <RangePicker
            onChange={handleDateChange}
            />
        </div>
    );
};
