import React from 'react'
import FormProduct from '../components/FormProduct'

const ProductRegister = () => {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    return (
        <div className='space-section'>
            <h2>{userConnected.type !== "Manager" ? "Página no encontrada" : "Resitra un alojamiento para mascotas"}</h2>
            <p>{userConnected.type !== "Manager" ? "" : "Completa los siguientes campos"}</p>
            <FormProduct/>
        </div>
    )
}

export default ProductRegister