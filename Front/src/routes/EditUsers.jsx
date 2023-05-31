import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

function EditUsers() {

    const [allUsers, setAllUsers] = useState([]);
    const getAllUsers = async () => {
        const res = await fetch('http://localhost:8080/api/v1/user/all');
        const data = await res.json();
        setAllUsers(data);
    };
    useEffect(() => {
        getAllUsers();
    }, []);

    const deleteUser = async (id, name) => {
        const confirmDelete = window.confirm(`¿Seguro deseas eliminar el usuario ${name}?`);
        if (confirmDelete) {
            await fetch(`http://localhost:8080/api/v1/user/${id}`, {
                method: 'DELETE',
            });
            getAllUsers();
        }
    };

    return (
        <div className='space-section'>
            <h2>Lista de Usuarios</h2>
            <div className='ListItem-CreateProduct'>
            </div><br/><br/>
            {allUsers.map((user) => (
                <div className='ListItem'>
                    {user.name}
                    <div className='ListButtons'>
                        <button
                            className='edit-button'
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
                            Editar
                        </button>

                        <button className='delete-button' onClick={() => deleteUser(product.id, product.name)}>
                            Eliminar
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default EditUsers;
