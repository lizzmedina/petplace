import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import * as yup from "yup";

const FormUser = () => {
    const [user, setUser] = useState({
        id: 0,
        name:'',
        lastName:'',
        cellPhone:'',
        address:'',
        email:'',
        password:'',
        type:''
    });

    const schema = yup.object().shape({
        name: yup.string().required("El nombre es obligatorio").matches(/^[a-zA-Z]+$/, "El nombre no debe contener caracteres especiales, numeros ni espacios en blanco"),
        lastName: yup.string().required("El apellido es obligatorio").matches(/^[a-zA-Z]+$/, "El nombre no debe contener caracteres especiales ni espacios en blanco"),
        cellPhone: yup.string().required("El número de celular es obligatorio").min(5, "El número de celular debe tener al menos 5 caracteres"),
        address: yup.string().required("La dirección es obligatoria"),
        email: yup.string().email("El email no es válido").required("El email es obligatorio").matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, "El email no debe contener caracteres especiales, espacios en blanco y seguir un formato xxx@xxx.xxx"),
        password: yup.string().required("La contraseña es obligatoria"),
        type: yup.string().required("El tipo de usuario es obligatorio"),
    });

    const [isLoading, setIsLoading] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const [validationErrors, setValidationErrors] = useState({});

    const url = "http://localhost:8080/api/v1/user";
    const [allUsers, setAllUsers] = useState([]);
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setAllUsers(data);
            });
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await schema.validate(user, { abortEarly: false });
            // La validación es exitosa con Yup, se continúa con el resto

            const result = allUsers.find((item) => item.email === user.email);
            if (result === undefined) {
                // El email no existe, proceder con el envío del formulario
                setUser({
                    id: 0,
                    name: "",
                    lastName: "",
                    cellPhone: "",
                    address: "",
                    email: "",
                    password: "",
                    type: "",
                });

                setIsLoading(true);

                fetch("http://127.0.0.1:8080/api/v1/user", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(user),
                })
                    .then((response) => {
                        if (response.ok) {
                            setIsSuccess(true);
                            alert(`El Usuario ${user.name} ha sido creado exitosamente.`);
                            setUser({
                                id: 0,
                                name: "",
                                lastName: "",
                                cellPhone: "",
                                address: "",
                                email: "",
                                password: "",
                                type: "",
                            });
                        } else {
                            throw new Error("Error en la solicitud");
                        }
                    })
                    .catch((error) => {
                        console.error(error);
                        alert("Parece que algo va mal, verifica la información.");
                    })
                    .finally(() => {
                        setIsLoading(false);
                    });
            } else {
                alert(
                    "El email proporcionado ya está tomado, intenta con uno nuevo."
                );
            }
        } catch (error) {
            // La validación falló, manejar los errores
            const validationErrors = {};
            error.inner.forEach((e) => {
                validationErrors[e.path] = e.message;
            });

            setValidationErrors(validationErrors);
            // Puedes mostrar los errores en la interfaz de usuario o realizar otras acciones según tus necesidades
        }
    };

    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <div className="form-section-name">
                    <div className="box">
                        <label>Nombre: </label><br/>
                        <input type="text" value={user.name} onChange={(e) => setUser({...user, name: e.target.value})}/>
                        {validationErrors.name && (<span className="error-message">{validationErrors.name}</span>)}
                        <br/>
                    </div>
                    <div className="box">
                        <label>Apellido: </label><br/>
                        <input type="text" value={user.lastName} onChange={(e) => setUser({...user, lastName: e.target.value})}/>
                        {validationErrors.lastName && (<span className="error-message">{validationErrors.lastName}</span>)}
                        <br/>
                    </div>
                </div>
                <br/>
                <label>Celular: </label>
                <input type="text" value={user.cellPhone} onChange={(e) => setUser({...user, cellPhone: e.target.value})}/>
                {validationErrors.cellPhone && (<span className="error-message">{validationErrors.cellPhone}</span>)}
                <br/>
                <label>Dirección: </label>
                <input type="text" value={user.address} onChange={(e) => setUser({...user, address: e.target.value})}/>
                {validationErrors.address && (<span className="error-message">{validationErrors.address}</span>)}
                <br/>
                <label>Email: </label>
                <input type="email" value={user.email} onChange={(e) => setUser({...user, email: e.target.value})}/>
                {validationErrors.email && (<span className="error-message">{validationErrors.email}</span>)}
                <br/>
                <label>Contraseña: </label>
                <input type="password" value={user.password} onChange={(e) => setUser({...user, password: e.target.value})}/>
                {validationErrors.password && (<span className="error-message">{validationErrors.password}</span>)}
                <br/>
                <label>Tipo de Usuario: </label>
                <select name="type" onChange={(e) => setUser({...user, type: e.target.value})}>
                    <option selected hidden>--- Elige una Opcion ---</option>
                    <option value="customer">Cliente</option>
                    <option value="manager">Administrador</option>
                </select>
                {validationErrors.type && (<span className="error-message">{validationErrors.type}</span>)}
                <br/>
                <div className="section-button">
                    <button className="button-1">Enviar</button>
                </div>
                <div className="section-redirection">¿Ya tienes una cuenta? <Link to='/login'>Iniciar Sesión</Link></div>
            </form>
        </div>
    );
};

export default FormUser;