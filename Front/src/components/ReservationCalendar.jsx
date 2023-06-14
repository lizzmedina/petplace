import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const {selectedDates, setSelectedDates} = useContextGlobal();
    console.log(selectedDates);
    return (
        <div style={{ margin: 5, width: 240 }}>
            <RangePicker
                onChange={(values) => {
                    const startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                    const endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    setSelectedDates([startDate, endDate]);
                }}
            />
        </div>
    );
};
