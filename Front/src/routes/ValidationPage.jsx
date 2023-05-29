import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

export const ValidationPage = () => {
    const { token } = useParams();
    const [validationStatus, setValidationStatus] = useState('validating');
    useEffect(() => {
        fetch(`/api/validar-cuenta/${token}`)
        .then(response => {
            if (response.ok) {
                setValidationStatus('success');
            } else {
                setValidationStatus('error');
            }
        })
        .catch(error => {
            setValidationStatus('error');
        });
    }, [token]);

    const [message, setMessage] = useState('');
    if (validationStatus === 'validating') {
        setMessage('Validando cuenta...');
    } else if (validationStatus === 'success') {
        setMessage( '¡Cuenta validada correctamente!');
    } else if (validationStatus === 'error') {
        setMessage('Error al validar la cuenta.');
    }
    return (
        <div>
            <h2>Validando cuenta...</h2>
            <h2> {message} </h2>
        </div>
    );
}

