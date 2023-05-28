import React from 'react'
import { useState, useEffect } from "react";

function Products() {

    const [allProducts, setAllProducts] = useState([]);
    const getAllProducts = async()=> {
        const res = await fetch('http://localhost:8080/api/v1/petDayCare/all');
        const data = await res.json();
        setAllProducts(data)
    }
    
    useEffect(() => {
        getAllProducts();
    }, []);

    return (
        <div className='space-section'>
            <h2>Lista de Productos</h2>
            <br /><br />
            {allProducts.map((product) => (
                <div className='ListItem'>
                    {product.name}

                    <div className='ListButtons'>
                        <button className='edit-button'> Editar </button>
                        <button className='delete-button'> Eliminar </button>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Products