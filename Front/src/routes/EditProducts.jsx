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
                                    title: 'Editar Producto',
                                    input: 'select',
                                    inputOptions: {
                                        places,
                                    },
                                    inputPlaceholder: 'Selecciona un producto',
                                    
                                    showCancelButton: true,
                                    inputValidator: (value) => {
                                        if (!value) {
                                            return 'Debes seleccionar un producto';
                                        }
                                    },
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        
                                        Swal.fire(`Seleccionaste: ${result.value}`);
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