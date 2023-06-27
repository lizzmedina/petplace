import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';
import { useEffect } from 'react';
import dayjs from 'dayjs';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const { selectedDates, setSelectedDates } = useContextGlobal();

    let fechaPorDefecto = []

    let localStartDate = null
    const storedStartDate = localStorage.getItem('localStartDate');
    let localEndDate = null
    const storedEndDate = localStorage.getItem('localEndDate');

    if (storedStartDate === "null" || storedEndDate === "null") {
        localStorage.removeItem('localStartDate')
        localStorage.removeItem('localEndDate')
        fechaPorDefecto = undefined
    } if (storedStartDate === null || storedEndDate === null) {
        fechaPorDefecto = undefined
    } else {
        localStartDate = dayjs(localStorage.getItem('localStartDate'))
        localEndDate = dayjs(localStorage.getItem('localStartDate'))
        fechaPorDefecto = [localStartDate, localEndDate]
    }

    
    return (
        <div className='searcher-calendar'>
            <RangePicker
                defaultValue={fechaPorDefecto}
                onChange={(values) => {
                    let startDate = null;
                    let endDate = null;
                    if (values != null) {
                        startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                        endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    }

                    localStorage.setItem('localStartDate', startDate);
                    localStorage.setItem('localEndDate', endDate);

                    console.log('Localstorage dates: ');
                    console.log(startDate, endDate);
                    console.log('--------------------');
                    setSelectedDates([startDate, endDate]);
                }}
            />
        </div>
    );
};

