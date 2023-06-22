import React from 'react'

function FormBooking() {

    const user = JSON.parse(localStorage.getItem("userConnected"));

    return (
        <div>
            <div className="booking-upcontainer">
                <div>
                    bloque izquierda
                    <h3>Información de Usuario</h3>
                    <div> <p>id:&nbsp;{user.id}</p> </div>
                    <div> <p>Nombre(s):</p> <p>&nbsp;{user.name}</p></div>
                    <div> <p>Apellido(s):</p> <p>&nbsp;{user.lastName}</p> </div>
                    <div> <p>email:</p> <p>&nbsp;{user.email}</p> </div>
                    <div> <p>Celular:</p> <p>&nbsp;{user.cellPhone}</p> </div>
                </div>
                <div>
                    bloque derecha
                </div>
            </div>

            <div>
                bloque inferior con politicas
            </div>
        </div>
    )
}

export default FormBooking