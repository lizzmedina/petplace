import React, { useState, useEffect } from "react";

export const FormNewCategory = () => {
    const [categories, setCategories] = useState([]);
    const [newCategory, setNewCategory] = useState({
        title: "",
        description: "",
        imageUrl: ""
    });

    useEffect(() => {
        fetch("http://localhost:8080/api/v1/category/all")
        .then((response) => response.json())
        .then((data) => {
            setCategories(data);
        })
        .catch((error) => {
            console.error("Error fetching categories:", error);
        });
    }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewCategory({ ...newCategory, [name]: value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        // Aquí enviarías los datos de newCategory al endpoint de creación en el backend
        // Cuando tengas el endpoint disponible, puedes completar esta parte del código

        // Agregar la nueva categoría a la lista existente
        setCategories([...categories, newCategory]);

        // Limpiar los campos del formulario
        setNewCategory({
        title: "",
        description: "",
        imageUrl: ""
        });
    };

    return (
        <div>
        <h2>Crear nueva categoría</h2>

        <h3>Formulario:</h3>
        <form onSubmit={handleSubmit}>
            <div>
            <label>Título:</label>
            <input type="text" name="title" value={newCategory.title} onChange={handleInputChange} />
            </div>
            <div>
            <label>Descripción:</label>
            <input type="text" name="description" value={newCategory.description} onChange={handleInputChange} />
            </div>
            <div>
            <label>URL imagen:</label>
            <input type="text" name="imageUrl" value={newCategory.imageUrl} onChange={handleInputChange} />
            </div>
            <button type="submit">Crear categoría</button>
        </form>
        </div>
    );
};


