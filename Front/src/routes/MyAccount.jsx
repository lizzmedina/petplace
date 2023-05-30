import React from 'react'
import { Link } from 'react-router-dom';

function MyAccount() {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

    return (
        <div className='space-section'>
            <h2>Mi Cuenta</h2>
            <div className="form-container">
                <div className="datoUser"><h4>Nombres: </h4> <p>{userConnected.name}</p></div>
                <br />
                <div className="datoUser"><h4>Apellidos: </h4> <p>{userConnected.lastName}</p></div>
                <br />
                <div className="datoUser"><h4>Documento de Identidad: </h4> <p>{userConnected.id}</p></div>
                <br />
                <div className="datoUser"><h4>Celular: </h4> <p>{userConnected.cellPhone}</p></div>
                <br />
                <div className="datoUser"><h4>Email: </h4> <p>{userConnected.email}</p></div>
                <br />
                <div className="datoUser"><h4>Tipo de Usuario: </h4> <p>{userConnected.type}</p></div>
                <br />
            </div>

            {userConnected.type === "manager" && (
                <div className='space-section'>
                    <h2>Opciones de mi cuenta</h2>
                    <br />
                    <div className="options-container">
                        <div className="options">
                            <h3>Productos</h3>
                            <button><Link to='/productRegister' style={{ color: 'inherit' }}>Crear Producto</Link></button>
                            <button><Link to='/EditProducts' style={{ color: 'inherit' }}>Editar Producto</Link></button>

                        </div>
                        <div className="options">
                            <h3>Categorias</h3>
                            <button><Link to='/newCategory' style={{ color: 'inherit' }}>Crear Categoria</Link></button>
                        </div>
                        <div className="options">
                            <h3>Usuarios</h3>
                            <button>Editar Usuario</button>
                        </div>
                    </div>
                </div>
            )}

        </div>
    )
}

export default MyAccount