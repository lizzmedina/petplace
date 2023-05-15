import React, { useState } from "react";

const FormLogin = () => {
    const [userLog, setUserLog] = useState({
        email:'',
        password:'',
    })

    const handleSubmit = (event) => {
        event.preventDefault()
        if (userLog.name.length > 3) {
            alert(`Bienvendio ${userLog.name}.`)
            setUserLog({
                email:'',
                password:'',
            })
        } else {
            alert('Por favor verifica la informacion suministrada')
        }
    }

    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <label>Email: </label>
                <input type="email" className="type-1" value={userLog.email} onChange={(e) => setUserLog({...userLog, email: e.target.value})}/>
                <br/>
                <label>Contraseña: </label>
                <input type="password" className="type-1" value={userLog.password} onChange={(e) => setUserLog({...userLog, password: e.target.value})}/>
                <br/>

                <br/>
                <div className="section-button">
                    <button className="button-1">Ingresar</button>
                </div>
            </form>
        </div>
    );
}

export default FormLogin