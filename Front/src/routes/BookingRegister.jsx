import React from 'react';
import { Navigate } from 'react-router-dom';
import FormBooking from '../components/FormBooking';

function BookingRegister() {
    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

    return (
        <div className='space-section'>
            {!userConnected ? <Navigate to='/login' /> : (
                <>
                    <h2>Reserva</h2>
                    <FormBooking />
                </>
            )}
        </div>
    );
}

export default BookingRegister;

