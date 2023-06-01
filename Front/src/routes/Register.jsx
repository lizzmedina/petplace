import React from 'react';
import FormUser from '../components/FormUser';

const Register = () => {
    return (
        <div className='space-section'>
            <h2>Crear Cuenta</h2>
            <p>Favor llenar los siguientes campos</p>
            <FormUser/>
        </div>
    )
}

export default Register