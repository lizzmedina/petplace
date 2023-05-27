import React, { useState } from "react";

export const FormNewCategory = () => {

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
        setNewCategory({ ...newCategory, [name]: value });
        setErrors({ ...errors, [name]: "" }); // Limpiar el error cuando se modifica el campo
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
            // Limpiar los campos del formulario
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
        <div className="form-new-category-container">
        <h2 className="title-form-new-category">Crear nueva categoría</h2>

        <h3 className="instructions-form-new-category">Ingrese los datos correspondientes para crearla:</h3>
        <form onSubmit={handleSubmit} >
            <div className="title-input-container">
            <label>Título:</label>
            <input
                type="text"
                name="title"
                value={newCategory.title}
                onChange={handleInputChange}
            />
            {errors.title && <span className="error">{errors.title}</span>}
            </div>
            <div className="description-input-container">
            <label>Descripción:</label>
            <input
                type="text"
                name="description"
                value={newCategory.description}
                onChange={handleInputChange}
            />
            {errors.description && (
                <span className="error">{errors.description}</span>
            )}
            </div>
            <div className="image-input-container">
            <label>URL imagen:</label>
            <input
                type="text"
                name="image"
                value={newCategory.image}
                onChange={handleInputChange}
            />
            {errors.image && <span className="error">{errors.image}</span>}
            </div>
            <button 
                type="submit"
                className="button-form-new-category"
            >
                Crear categoría
            </button>
        </form>
        </div>
    );
};



