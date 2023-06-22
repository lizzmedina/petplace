import React from 'react'
import FormBooking from '../components/FormBooking'

function BookingRegister() {
    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    return (
        <div className='space-section'>
            <h2>{!userConnected ? "Página no encontrada" : "Reserva"}</h2>
            <p>{!userConnected ? "" : "asegurate de confirmar y llenar los siguientes campos"}</p>
            {!userConnected ? "" : <FormBooking/>}
        </div>
    )
}

export default BookingRegister