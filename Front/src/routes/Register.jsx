import React from 'react'
import FormUser from '../components/FormUser'

const Register = () => {
    return (
        <div className='register-section'>
            <h2>Crea una Nueva Cuenta</h2>
            <p>Favor llenar los siguientes campos</p>
            <FormUser/>
        </div>
    )
}

export default Register