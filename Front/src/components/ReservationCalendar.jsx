import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';
import { useEffect } from 'react';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const {selectedDates, setSelectedDates} = useContextGlobal();

    

    return (
        <div className='searcher-calendar'>
            <RangePicker
                onChange={(values) => {
                    let startDate = null;
                    let endDate = null;
                    if (values !=  null) {
                        startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                        endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    }
                    
                    localStorage.setItem('localStartDate', startDate);
                    localStorage.setItem('localEndDate', endDate);
                    
                    console.log("Localstorage dates: ");
                    console.log(startDate, endDate);
                    console.log("--------------------")
                    setSelectedDates([startDate, endDate]);
                    
                   
                }}
            />
        </div>
    );
};
