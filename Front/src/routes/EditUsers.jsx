import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';
import { element } from 'prop-types';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faPenToSquare, faTrash} from "@fortawesome/free-solid-svg-icons";

function EditUsers() {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;  //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    const [allUsers, setAllUsers] = useState([]);
    const getAllUsers = async () => {
        const res = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`);
        const data = await res.json();
        setAllUsers(data);
    };
    useEffect(() => {
        getAllUsers();
    }, []);

    const deleteUser = async (id, name) => {
        const confirmDelete = window.confirm(`¿Seguro deseas eliminar el usuario ${name}?`);
        if (confirmDelete) {
            await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/${id}`, {
                method: 'DELETE',
            });
            getAllUsers();
        }
    };

    return (
        <div className='space-section'>

            <h2>{userConnected.type !== "Manager" ? "Página no encontrada" : "Lista de Usuarios"}</h2>
            <br/><br/>

            {userConnected.type === "Manager" && (
                allUsers.map((user) => (
                    <div className='ListItem'>
                        {user.name} ({user.email})   |  |  {user.type}
                        <div className='ListButtons'>
                            <p
                                className='option-edit-button'
                                onClick={() => {
                                    Swal.fire({
                                        title: 'Formulario de Edición',
                                        html:
                                            '<p>Nombre: </p> ' +
                                            `<input id="nombre" class="swal2-input" value="${user.name}" required>` +
                                            '<p>Apellido: </p> ' +
                                            `<input id="apellido" class="swal2-input" value="${user.lastName}" required>` +
                                            '<p>Celular: </p> ' +
                                            `<input id="celular" class="swal2-input" value="${user.cellPhone}" required>` +
                                            '<p>Rol: </p> ' +
                                            `<select id="rol" class="swal2-input">
                                                <option value="Customer" ${user.type === 'Customer' ? 'selected' : ''}>Customer</option>
                                                <option value="Manager" ${user.type === 'Manager' ? 'selected' : ''}>Manager</option>
                                            </select>` +
                                            '<p>Direccion: </p> ' +
                                            `<input id="direccion" class="swal2-input" value="${user.address}" required>`,
                                        focusConfirm: false,
                                        showCancelButton: true,
                                        preConfirm: () => {
                                            const nombre = document.getElementById('nombre').value;
                                            const apellido = document.getElementById('apellido').value;
                                            const celular = document.getElementById('celular').value;
                                            const rol = document.getElementById('rol').value;
                                            const direccion = document.getElementById('direccion').value;

                                            if (!nombre || !apellido || !celular || !rol || !direccion) {
                                                Swal.showValidationMessage('Debes completar todos los campos');
                                            }

                                            return { nombre, apellido, celular, rol, direccion };
                                        },
                                        customClass: {
                                            container: 'my-swal-container',
                                            popup: 'my-swal-popup',
                                        },
                                    }).then((result) => {

                                        const { nombre, apellido, celular, rol, direccion } = result.value;

                                        const updatedUser = {
                                            id: user.id,
                                            name: nombre,
                                            lastName: apellido,
                                            cellPhone: celular,
                                            type: rol,
                                            address: direccion,
                                            email: user.email,
                                            password: user.password
                                        };

                                        fetch('http://127.0.0.1:8080/api/v1/user', {
                                            method: 'PUT',
                                            headers: {
                                                'Content-Type': 'application/json',
                                            },
                                            body: JSON.stringify(updatedUser),
                                        })
                                            .then((response) => response.json())
                                            .then((data) => {
                                                Swal.fire({icon: 'success',title:`Usuario ${data.name} actualizado con exito`});
                                                getAllUsers(); // Actualiza la lista de usuarios después de la edición
                                            })
                                            .catch((error) => {
                                                console.error('Error:', error);
                                            });
                                    });
                                }}
                            >
                                <FontAwesomeIcon icon={faPenToSquare} size="lg" style={{color: "#8ed1b9",}} /> 
                            </p>

                            <p /* className='delete-button' */ onClick={() => deleteUser(product.id, product.name)}>
                                <FontAwesomeIcon icon={faTrash} size="lg" style={{color: "#ff1117",}} />
                            </p>
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}

export default EditUsers;
