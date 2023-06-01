import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useContextGlobal } from '../components/utils/global.constext';
import Swal from 'sweetalert2';

function EditProducts() {
    const { places, setPlaces } = useContextGlobal();

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;  //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    const [allProducts, setAllProducts] = useState([]);
    const getAllProducts = async () => {
        const res = await fetch('http://localhost:8080/api/v1/petDayCare/all');
        const data = await res.json();
        setAllProducts(data);
    };
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
    };

    return (
        <div className='space-section'>
            
            <h2>{userConnected.type !== "Manager" ? "Página no encontrada" : "Lista de Productos"}</h2>

            {userConnected.type === "Manager" && (
                <div className='ListItem-CreateProduct'>
                    <button className='create-button'>
                        {' '}
                        <Link to='/productRegister' style={{ color: 'inherit' }}>
                            Crear Producto
                        </Link>{' '}
                    </button>
                </div>
            )}

            {userConnected.type === "Manager" && (
                allProducts.map((product) => (
                    <div className='ListItem'>
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
                                            `<input id="precio" class="swal2-input" value="${product.basicPrice}" required>` +
                                            '<p>Categoria: </p> ' +
                                            `<select id="categoria" class="swal2-input">
                                            ${places.map(
                                                (categoria) =>
                                                    `<option value="${categoria.title}" ${categoria.title === product.type.title ? 'selected' : ''
                                                    }>${categoria.title}</option>`
                                            )}
                                            </select>` +
                                            '<p>Ciudad: </p> ' +
                                            `<input id="ciudad" class="swal2-input" value="${product.city}" required>` +
                                            '<p>Direccion: </p> ' +
                                            `<input id="direccion" class="swal2-input" value="${product.address}" required>` +
                                            '<p>Descripcion: </p> ' +
                                            `<input id="detail" class="swal2-input" value="${product.detail}" required>`,
                                        focusConfirm: false,
                                        showCancelButton: true,
                                        preConfirm: () => {
                                            const nombre = document.getElementById('nombre').value;
                                            const capacidad = document.getElementById('capacidad').value;
                                            const precio = document.getElementById('precio').value;
                                            const categoria = document.getElementById('categoria').value;
                                            const ciudad = document.getElementById('ciudad').value;
                                            const direccion = document.getElementById('direccion').value;
                                            const detail = document.getElementById('detail').value;

                                            if (!nombre || !capacidad || !precio || !categoria || !ciudad || !direccion || !detail) {
                                                Swal.showValidationMessage('Debes completar todos los campos');
                                            }

                                            return { nombre, capacidad, precio, categoria, ciudad, direccion, detail };
                                        },
                                        customClass: {
                                            container: 'my-swal-container',
                                            popup: 'my-swal-popup',
                                        },
                                    }).then((result) => {

                                        const { nombre, capacidad, precio, categoria, ciudad, direccion, detail } = result.value;

                                        const updatedProduct = {
                                            id: product.id,
                                            name: nombre,
                                            capacity: capacidad,
                                            basicPrice: precio,
                                            categoryName: categoria,
                                            city: ciudad,
                                            address: direccion,
                                            detail: detail,
                                            images: product.images,
                                            characteristics: product.characteristics,
                                        };

                                        fetch('http://127.0.0.1:8080/api/v1/petDayCare/edit', {
                                            method: 'PUT',
                                            headers: {
                                                'Content-Type': 'application/json',
                                            },
                                            body: JSON.stringify(updatedProduct),
                                        })
                                            .then((response) => response.json())
                                            .then((data) => {
                                                Swal.fire({icon: 'success',title:`Producto ${data.name} actualizado con exito`});
                                                getAllProducts(); // Actualiza la lista de productos después de la edición
                                            })
                                            .catch((error) => {
                                                console.error('Error:', error);
                                            });
                                    });
                                }}
                            >
                                Editar
                            </button>

                            <button className='delete-button' onClick={() => deleteProduct(product.id, product.name)}>
                                Eliminar
                            </button>
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}

export default EditProducts;
