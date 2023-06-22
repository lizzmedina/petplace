import { DatePicker } from 'antd';
import { useContextGlobal } from './utils/global.constext';
import { useEffect } from 'react';

const { RangePicker } = DatePicker;

export const ReservationCalendar = () => {
    const {selectedDates, setSelectedDates} = useContextGlobal();

    useEffect(() => {
        // Recuperar las fechas almacenadas del almacenamiento local
        const storedStartDate = JSON.parse(localStorage.getItem('selectedStartDate'));
        const storedEndDate = JSON.parse(localStorage.getItem('selectedEndDate'));
        // Establecer las fechas seleccionadas en el estado
        console.log(selectedDates);
        setSelectedDates([storedStartDate, storedEndDate]);
        console.log(selectedDates);
    }, []);

    return (
        <div style={{ margin: 5, width: 240 }}>
            <RangePicker
                onChange={(values) => {
                    let startDate = null;
                    let endDate = null;
                    if (values !=  null) {
                        startDate = values[0] ? values[0].format('YYYY-MM-DD') : null;
                        endDate = values[1] ? values[1].format('YYYY-MM-DD') : null;
                    }
                    setSelectedDates([startDate, endDate]);
                    
                }}
            />
        </div>
    );
};
