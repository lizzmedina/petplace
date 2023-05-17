import React, { useState } from "react";
import { Link } from 'react-router-dom';

const FormUser = () => {
    const [user, setUser] = useState({
        name:'',
        lastName:'',
        cellPhone:'',
        address:'',
        email:'',
        password:'',
        type:''
    })

    const handleSubmit = (event) => {
        event.preventDefault()
        if (user.name.length > 3) {
            alert(`Hola ${user.name}, su cuenta ha sido creada exitosamente.`)
            setUser({
                name:'',
                lastName:'',
                cellPhone:'',
                address:'',
                email:'',
                password:'',
                type:''
            })
        } else {
            alert('Por favor verifica la informacion suministrada')
        }
    }

    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <div className="form-section-name">
                    <div className="box">
                        <label>Nombre: </label><br/>
                        <input type="text" value={user.name} onChange={(e) => setUser({...user, name: e.target.value})}/>
                        <br/>
                    </div>
                    <div className="box">
                        <label>Apellido: </label><br/>
                        <input type="text" value={user.lastName} onChange={(e) => setUser({...user, lastName: e.target.value})}/>
                        <br/>
                    </div>
                </div>
                <br/>
                <label>Celular: </label>
                <input type="text" value={user.cellPhone} onChange={(e) => setUser({...user, cellPhone: e.target.value})}/>
                <br/>
                <label>Direccion: </label>
                <input type="text" value={user.address} onChange={(e) => setUser({...user, address: e.target.value})}/>
                <br/>
                <label>Email: </label>
                <input type="email" value={user.email} onChange={(e) => setUser({...user, email: e.target.value})}/>
                <br/>
                <label>Contraseña: </label>
                <input type="password" value={user.password} onChange={(e) => setUser({...user, password: e.target.value})}/>
                <br/>
                <label>Tipo de Usuario: </label>
                <select name="type" onChange={(e) => setUser({...user, type: e.target.value})}>
                    <option selected hidden>--- Elige una Opcion ---</option>
                    <option value="customer">Cliente</option>
                    <option value="manager">Administrador</option>
                </select>

                <br/>
                <div className="section-button">
                    <button className="button-1">Enviar</button>
                </div>
                <div className="section-redirection">¿Ya tienes una cuenta? <Link to='/login'>Iniciar Sesión</Link></div>
            </form>
        </div>
    );
};

export default FormUser