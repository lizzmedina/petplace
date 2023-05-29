import React from 'react'
import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import { useContextGlobal } from '../components/utils/global.constext'
import Swal from 'sweetalert2';

function EditProducts() {
    const {places, setPlaces} = useContextGlobal()

    const [allProducts, setAllProducts] = useState([]);
    const getAllProducts = async()=> {
        const res = await fetch('http://localhost:8080/api/v1/petDayCare/all');
        const data = await res.json();
        setAllProducts(data)
    }
    useEffect(() => {
        getAllProducts();
    }, []);

    const deleteProduct = async (id, name) => {
        const confirmDelete = window.confirm(`¿Seguro deseas eliminar el producto ${name}?`);
        if (confirmDelete) {
            await fetch(`http://localhost:8080/api/v1/petDayCare/${id}`, {
                method: 'DELETE',
            });
            getAllProducts();
        }
    }


    return (
        <div className='space-section'>
            <h2>Lista de Productos</h2>
            <div className='ListItem-CreateProduct'>
                <button className='create-button'> <Link to='/productRegister' style={{ color: 'inherit' }}>Crear Producto</Link> </button>
            </div>
            {allProducts.map((product) => (
                <div className='ListItem'>   {/*El map mostrara 2 cosas: el nombre y los botones al final*/}
                    {product.name}

                    <div className='ListButtons'>
                        <button
                            className='edit-button'
                            onClick={() => {
                                Swal.fire({
                                    title: 'Formulario de Edición',
                                    html:
                                        '<p>Nombre: </p> ' +
                                        `<input id="nombre" class="swal2-input" value="${product.name}" required>` +
                                        '<p>Capacidad: </p> ' +
                                        `<input id="capacidad" class="swal2-input" value="${product.capacity}" required>` +
                                        '<p>Precio: </p> ' +
                                        `<input id="precio" class="swal2-input" value="${product.basic_price}" required>` +
                                        '<p>Categoria: </p> ' +
                                        `<select name="type" value="${product.type.title}">
                                            ${places.map((categoria) => (
                                                `<option value="${categoria.title}">${categoria.title}</option>`
                                            ))}
                                        </select>` +

                                        '<p>Ciudad: </p> ' +
                                        `<input id="ciudad" class="swal2-input" value="${product.city}" required>` +
                                        '<p>Direccion: </p> ' +
                                        `<input id="direccion" class="swal2-input" value="${product.address}" required>` +
                                        '<p>Descripcion: </p> ' +
                                        `<input id="descripcion" class="swal2-input" value="${product.detail}" required>`,
                                    focusConfirm: false,
                                    showCancelButton: true,
                                    preConfirm: () => {
                                        const nombre = document.getElementById('nombre').value;
                                        const descripcion = document.getElementById('descripcion').value;
                                        if (!nombre || !descripcion) {
                                            Swal.showValidationMessage('Debes completar todos los campos');
                                        }
                                        return { nombre, descripcion };
                                    },
                                    customClass: {
                                        container: 'my-swal-container',
                                        popup: 'my-swal-popup',
                                    },
                                }).then((result) => {
                                    if (!result.dismiss) {
                                        const { nombre, descripcion } = result.value;
                                        Swal.fire(`Nuevo nombre: ${nombre}, Nueva descripción: ${descripcion}`);
                                    }
                                });

                            }}
                        >
                            Editar
                        </button>

                        <button className='delete-button' onClick={() => deleteProduct(product.id, product.name)}> Eliminar </button>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default EditProducts