import React from 'react'
import FormCity from '../components/FormCity'

function CityRegister() {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    return (
        <div className='space-section'>
            <h2>{userConnected.type !== "Manager" ? "Página no encontrada" : "Registra una nueva Ciudad"}</h2>
            <p>{userConnected.type !== "Manager" ? "" : "Completa los siguientes campos"}</p>
            {userConnected.type !== "Manager" ? "" : <FormCity/>}
        </div>
    )
}

export default CityRegister