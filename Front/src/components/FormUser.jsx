import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import * as yup from "yup";
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.css';
import { useContextGlobal } from "./utils/global.constext";

const FormUser = () => {

    const {urlGetUsers, urlPostUsers, sendEmailUrl} = useContextGlobal();

    const [user, setUser] = useState({
        id: '',
        name:'',
        lastName:'',
        cellPhone:'',
        address:'',
        email:'',
        password:'',
        type:''
    });

        const schema = yup.object().shape({
        name: yup
            .string()
            .required("El nombre es obligatorio")
            .matches(
                /^[a-zA-Z]+(\s[a-zA-Z]+)*$/,
                "El nombre no debe contener caracteres especiales, números ni espacios en blanco al inicio o final"
            ),
        lastName: yup
            .string()
            .required("El apellido es obligatorio")
            .matches(
                /^[a-zA-Z]+(\s[a-zA-Z]+)*$/,
                "El apellido no debe contener caracteres especiales ni espacios en blanco al inicio o final"
            ),
        id: yup
            .string()
            .required("El Documento de Identidad es obligatorio")
            .matches(/^[0-9]+$/, "El Documento de identidad solo debe contener números sin puntos ni comas")
            .min(5, "El Documento de identidad debe tener al menos 5 caracteres"),
        cellPhone: yup
            .string()
            .required("El Celular es obligatorio")
            .matches(/^[0-9]+$/, "El Celular solo debe contener números sin puntos ni comas")
            .min(5, "El número de celular debe tener al menos 5 caracteres"),
        address: yup.string().required("La dirección es obligatoria"),
        email: yup
            .string()
            .email("El email no es válido")
            .required("El email es obligatorio")
            .matches(
                /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                "El email no debe contener caracteres especiales, espacios en blanco y seguir un formato xxx@xxx.xxx"
            ),
        password: yup.string().required("La contraseña es obligatoria"),
        type: yup.string().required("El tipo de usuario es obligatorio"),
    });

    const [isLoading, setIsLoading] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const [validationErrors, setValidationErrors] = useState({});

    //const url = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`;
    const [allUsers, setAllUsers] = useState([]);
    useEffect(() => {
        fetch(urlGetUsers)
            .then((res) => res.json())
            .then((data) => {
                setAllUsers(data);
            });
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await schema.validate(user, { abortEarly: false });// Si la validación es exitosa con Yup, se continúa con el resto

            const result = allUsers.find((item) => item.email.toLowerCase() === user.email.toLowerCase());
            if (!result) {
                
                setIsLoading(true);

                fetch(urlPostUsers, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(user),
                })
                .then((response) => {
                    if (response.ok) {
                        setIsSuccess(true);
                        Swal.fire({
                            title: `${user.name}`,
                            text: "Por favor, revisa tu correo electrónico. Te hemos enviado un correo de verificación.",
                            icon: "success",
                        });
                    
                        // Envío del correo de validación
                        fetch(`${sendEmailUrl} ${user.email}`, {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: JSON.stringify({ email: user.email }), 
                        })
                        .then((res) => res.json())
                        .then((data) => {
                            console.log("Correo de validación enviado:", data);
                        })
                        .catch((error) => {
                            console.error("Error al enviar el correo de validación:", error);
                        });
                        
                        setUser({
                            id: "",
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
                Swal.fire({icon: 'error',title:"Parece que algo va mal, verifica la información."});
            })
            .finally(() => {
                setIsLoading(false);
                setUser((user) => ({ ...user, type: "" })); // Restablecer el campo "type" a una cadena vacía
            });
            } else {
                Swal.fire({icon: 'warning',title:"El email proporcionado ya está tomado, intenta con uno nuevo."});
            }
        } catch (error) {
            const validationErrors = {};
            error.inner.forEach((e) => {
                validationErrors[e.path] = e.message;
            });
            setValidationErrors(validationErrors);
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
                        <label>Apellidos: </label><br/>
                        <input type="text" value={user.lastName} onChange={(e) => setUser({...user, lastName: e.target.value})}/>
                        {validationErrors.lastName && (<span className="error-message">{validationErrors.lastName}</span>)}
                        <br/>
                    </div>
                </div>
                <br/>
                <div className="form-section-name">
                    <div className="box">
                        <label>Documento de Identidad: </label><br/>
                        <input type="number" value={user.id} onChange={(e) => setUser({...user, id: e.target.value})}/>
                        {validationErrors.id && (<span className="error-message">{validationErrors.id}</span>)}
                        <br/>
                    </div>
                    <div className="box">
                        <label>Celular: </label><br/>
                        <input type="number" value={user.cellPhone} onChange={(e) => setUser({...user, cellPhone: e.target.value})}/>
                        {validationErrors.cellPhone && (<span className="error-message">{validationErrors.cellPhone}</span>)}
                        <br/>
                    </div>
                </div>
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
                <select name="type" value={user.type} onChange={(e) => setUser({...user, type: e.target.value})}>
                    <option value="" hidden>--- Elige una Opción ---</option>
                    <option value="Customer">Cliente</option>
                    <option value="Manager">Administrador</option>
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
