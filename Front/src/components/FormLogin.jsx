import React, { useState, useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

const FormLogin = () => {
    const navigate = useNavigate();

    const [userLog, setUserLog] = useState({
        email: '',
        password: '',
    });

    const urlAllUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`;
    const [allUsers, setAllUsers] = useState([]);
    useEffect(() => {
        fetch(urlAllUsers)
            .then((res) => res.json())
            .then((data) => {
                setAllUsers(data);
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();

        const foundUser = allUsers.find((item) => item.email.toLowerCase() === userLog.email.toLowerCase());
        if (foundUser) {
            if (foundUser.password === userLog.password) {
                Swal.fire({icon: 'success',title:`Bienvenido a PetPlace ${foundUser.name}.`});
                localStorage.setItem('userConnected', JSON.stringify(foundUser));
                setUserLog({
                    email: '',
                    password: '',
                });
                navigate('/', { replace: true });
                window.location.reload();
            } else {
                Swal.fire({icon: 'error',title:"Contraseña incorrecta."});
            }
        } else {
            Swal.fire({icon: 'error',title:"El correo electrónico no está registrado."});
        }
        passwordInput.value = '';
    };


    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <label htmlFor="emailInput">Email: </label>
                <input 
                    type="email" 
                    className="type-1" 
                    id="emailInput"
                    value={userLog.email} 
                    onChange={(e) => setUserLog({ ...userLog, email: e.target.value })} />
                <br />
                <label htmlFor="passwordInput">Contraseña: </label>
                <input 
                    type="password" 
                    className="type-1" 
                    id="passwordInput"
                    value={userLog.password} 
                    onChange={(e) => setUserLog({ ...userLog, password: e.target.value })} />
                <br />

                <br />
                <div className="section-button">
                    <button className="button-1">Ingresar</button>
                </div>
                <div className="section-redirection">¿Aun no tienes cuenta? <Link to='/register'>Registrate</Link></div>
            </form>
        </div>
    );
}

export default FormLogin;
