
import dayjs from 'dayjs';
import AdapterDayjs from '@mui/lab/AdapterDayjs'; 
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';


export const CalendarDetail = () => {
    const currentDate = dayjs();

    const datesData = [
        ['2023-06-08', '2023-06-10'],
        ['2023-07-23', '2023-07-26'],
        ['2023-07-02', '2024-06-08'],
        ['2023-06-12', '2024-06-13'],
    ];

    const startDate = currentDate.startOf('month').format('YYYY-MM-DD');
    const endDate = currentDate.endOf('month').format('YYYY-MM-DD');

    const renderDay = (day, _props) => {
        return (
        <div>
            {dayjs(day).format('D')}
        </div>
        );
    };

    const shouldDisableDate = (date) => {
        for (const [start, end] of datesData) {
        if (date >= start && date <= end) {
            return true;
        }
        }
        return false;
    };

    return (
        <div>
        <h1>Calendario</h1>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <CalendarPicker
                renderDay={renderDay}
                shouldDisableDate={(date) => date < startDate || date > endDate || shouldDisableDate(date)}
            />
        </LocalizationProvider>
        </div>
    );
};
