import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';
//import dayjs from 'dayjs';
//import { useEffect } from 'react';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const { selectedDates, setSelectedDates } = useContextGlobal();

    // useEffect(() => {
    //     const localStartDate = localStorage.getItem('localStartDate')
    //         ? dayjs(localStorage.getItem('localStartDate'))
    //         : null;
    //     const localEndDate = localStorage.getItem('localEndDate')
    //         ? dayjs(localStorage.getItem('localEndDate'))
    //         : null;
    //         setSelectedDates([localStartDate, localEndDate])
    // }, [selectedDates])

    return (
        <div className='searcher-calendar'>
            <RangePicker
                //defaultValue={[selectedDates[0], selectedDates[1]]}
                onChange={(values) => {
                    let startDate = null;
                    let endDate = null;
                    if (values != null) {
                        startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                        endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    }
                    localStorage.setItem('localStartDate', startDate);
                    localStorage.setItem('localEndDate', endDate);

                    setSelectedDates([startDate, endDate]);
                }}
                placeholder={['check In', 'check Out']}
            />
        </div>
    );
};

