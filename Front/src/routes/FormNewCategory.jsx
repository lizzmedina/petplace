import React, { useState } from "react";

import Swal from 'sweetalert2';

export const FormNewCategory = () => {

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    const [newCategory, setNewCategory] = useState({
        title: "",
        description: "",
        image: ""
    });
    const [errors, setErrors] = useState({
        title: "",
        description: "",
        image: ""
    });
    
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewCategory((prevCategory) => ({
            ...prevCategory,
            [name]: value
        }));
        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: ""
        }));
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        const urlPost = "http://localhost:8080/api/v1/category";

        // Validar los campos antes de enviar la solicitud
        let formIsValid = true;
        const newErrors = { ...errors };

        if (!newCategory.title.match(/^[a-zA-Z\s]+$/)) {
        formIsValid = false;
        newErrors.title = "Ingrese un nombre válido (solo letras)";
        }

        if (!newCategory.description || newCategory.description.trim() === "") {
        formIsValid = false;
        newErrors.description = "Ingrese una descripción válida";
        }

        if (!newCategory.image?.match(/^(ftp|http|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&:/~+#-]*[\w@?^=%&/~+#-])?$/
        )) {
            formIsValid = false;
            newErrors.image = "Ingrese una URL de imagen válida";
        }

        if (formIsValid) {
            // Enviar los datos de newCategory al endpoint de creación en el backend
            fetch(urlPost, {
                method: "POST",
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(newCategory)
            })
            .then((response) => response.json())
            .then(() => {
                Swal.fire( {
                    text: "¡Categoría creada exitosamente!",
                    icon: "success",
                });
                setNewCategory({
                    title: "",
                    description: "",
                    image: ""
                });
            })
            .catch((error) => {
                console.error("Error creating category:", error);
            });
        } else {
            setErrors(newErrors);
        }
    };

    return (

        <div className=" space-section">

            
            <div className="titles-new-category">
                <h2 className="title-form-new-category">{userConnected.type !== "Manager" ? "Página no encontrada" : "Crear nueva categoría"}</h2>
                <h3 className="instructions-form-new-category">{userConnected.type !== "Manager" ? "" : "Crear nueva categoría"}</h3>
            </div>
            

            {userConnected.type === "Manager" && (
                <form onSubmit={handleSubmit} className="form-new-category" >
                    <div className="title-input-container">
                        <label htmlFor="title">Titulo:</label>
                        <input
                            className="input-category"
                            type="text"
                            id="title"
                            name="title"
                            value={newCategory.title}
                            onChange={handleInputChange}
                        />
                        {errors.title && <span className="error">{errors.title}</span>}
                    </div>
                    <div className="description-input-container">
                        <label htmlFor="description">Descripción:</label>
                        <input
                            className="input-category"
                            type="text"
                            id="description"
                            name="description"
                            value={newCategory.description}
                            onChange={handleInputChange}
                        />
                        {errors.description && (
                            <span className="error">{errors.description}</span>
                        )}
                    </div>
                    <div className="image-input-container">
                        <label htmlFor="image">URL imagen:</label>
                        <input
                            className="input-category"
                            type="text"
                            name="image"
                            id="image"
                            value={newCategory.image}
                            onChange={handleInputChange}
                        />
                        {errors.image && <span className="error">{errors.image}</span>}
                    </div>
                    <div className="button-new-category-container">
                        <button 
                            type="submit"
                            className="button-form-new-category button-1"
                            role="button"
                        >
                            Crear categoría
                        </button>
                    </div>
                        
                </form>
            )}
        </div>
    );
};
