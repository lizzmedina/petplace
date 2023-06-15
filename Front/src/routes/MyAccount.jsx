import React from 'react'
import { Link } from 'react-router-dom';
import { LuDog } from 'react-icons/lu';

function MyAccount() {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

    return (
        <div className='space-section'>
            <h2>Mi Cuenta</h2>
            <br/>
            <div className="account-container">
            <div className="icon-container">
                <LuDog size={100} className="circle-icon"/>
                <p>{userConnected.name}</p><p>{userConnected.lastName}</p>
            </div>
                <div className="form-container">
                    <div className="datoUser"><h4>Documento de Identidad: </h4> <p>&nbsp;{userConnected.id}</p></div>
                    <br />
                    <div className="datoUser"><h4>Celular: </h4> <p>&nbsp;{userConnected.cellPhone}</p></div>
                    <br />
                    <div className="datoUser"><h4>Email: </h4> <p>&nbsp;{userConnected.email}</p></div>
                    <br />
                    <div className="datoUser"><h4>Tipo de Usuario: </h4> <p>&nbsp;{userConnected.type}</p></div>
                    <br />
                </div>
            </div>
            

            {userConnected.type === "Manager" && (
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
                            <h3>Ciudad</h3>
                            <button><Link to='/cityRegister' style={{ color: 'inherit' }}>Crear Ciudad</Link></button>
                            <button><Link to='/editCity' style={{ color: 'inherit' }}>Editar Ciudad</Link></button>
                        </div>
                        <div className="options">
                            <h3>Categorias</h3>
                            <button><Link to='/newCategory' style={{ color: 'inherit' }}>Crear Categoria</Link></button>
                            <button><Link to='/editCategory' style={{ color: 'inherit' }}>Editar Categoria</Link></button>
                        </div>
                        <div className="options">
                            <h3>Usuarios</h3>
                            <button><Link to='/EditUsers' style={{ color: 'inherit' }}>Editar Usuario</Link></button>
                        </div>
                    </div>
                </div>
            )}

        </div>
    )
}

export default MyAccount