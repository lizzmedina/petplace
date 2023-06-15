import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import * as yup from 'yup';
import { useContextGlobal } from '../components/utils/global.constext';
import Modal from 'react-modal';

function EditCategory() {
    const { urlCategory } = useContextGlobal();

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

    const [categoryInDataBase, setCategoryInDataBase] = useState([]);
    const getAllCategoryDataBase = async () => {
        const res = await fetch(`${urlCategory}/all`);
        const data = await res.json();
        setCategoryInDataBase(data);
    };
    useEffect(() => {
        getAllCategoryDataBase();
    }, []);

    const schema = yup.object().shape({
        title: yup.string().required('El nombre de la categoría es obligatorio'),
        image: yup.string().required('Una imagen para la categoría es obligatoria'),
        description: yup.string().required('Una descripción es obligatoria'),
    });
    const [validationErrors, setValidationErrors] = useState({});

    const deleteCategory = async (id, title) => {
        const confirmDelete = await Swal.fire({
            title: `¿Seguro deseas eliminar la categoría ${title}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Eliminar',
            cancelButtonText: 'Cancelar',
            reverseButtons: true,
        });
        if (confirmDelete.isConfirmed) {
            await fetch(`${urlCategory}/${id}`, {
                method: 'DELETE',
            });
            getAllCategoryDataBase();
        }
    };

    const [selectedCategory, setSelectedCategory] = useState(null);
    const [categoryData, setCategoryData] = useState({
        title: '',
        description: '',
        image: '',
    });

    const openEditForm = (category) => {
        setSelectedCategory(category);
        setCategoryData({
            title: category.title,
            description: category.description,
            image: category.image,
        });
        openModal();
    };

    const [isModalOpen, setIsModalOpen] = useState(false);
    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedCategory(null);
        setCategoryData({
            title: '',
            description: '',
            image: '',
        });
    };

    const modalStyles = {
        content: {
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: '90%',
            maxWidth: '30rem',
            margin: '10px',
        },
    };

    const handleModalSubmit = () => {
        schema
            .validate(categoryData, { abortEarly: false })
            .then(() => {
                const categoryExists = categoryInDataBase.some((category) => category.title.toLowerCase() === categoryData.title.toLowerCase());
                if (categoryExists) {
                    Swal.fire({
                        icon: 'error',
                        title: 'La categoría elegida ya ha sido creada previamente, intenta con otra.',
                    });
                } else {
                    const updatedCategory = {
                        id: selectedCategory.id,
                        title: categoryData.title,
                        description: categoryData.description,
                        image: categoryData.image,
                    };

                    fetch(`${urlCategory}/edit`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(updatedCategory),
                    })
                        .then((response) => response.json())
                        .then((data) => {
                            Swal.fire({ icon: 'success', title: `Categoría ${categoryData.title} actualizada con éxito` });
                            getAllCategoryDataBase();
                            closeModal();
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
                }
            })
            .catch((error) => {
                const errors = {};
                error.inner.forEach((err) => {
                    errors[err.path] = err.message;
                });
                setValidationErrors(errors);
            });
    };

    return (
        <div className="space-section">
            <h2>{userConnected.type !== 'Manager' ? 'Página no encontrada' : 'Lista de Categorías'}</h2>
            <br />
            <br />

            {userConnected.type === 'Manager' &&
                categoryInDataBase.map((category) => (
                    <div className="ListItem" key={category.id}>
                        {category.title}
                        <div className="ListButtons">
                            <button className="edit-button" onClick={() => openEditForm(category)}>
                                Editar
                            </button>
                            <button className="delete-button" onClick={() => deleteCategory(category.id, category.title)}>
                                Eliminar
                            </button>
                        </div>
                    </div>
                ))}

            <Modal isOpen={isModalOpen} onRequestClose={closeModal} style={modalStyles}>
                <div className="city-modal">
                    <h2>Formulario de Edición</h2>
                    <br />
                    <p>Nombre de la categoría:</p>
                    <input
                        id="title"
                        className="swal2-input"
                        value={categoryData.title}
                        onChange={(e) => setCategoryData({ ...categoryData, title: e.target.value })}
                        required
                    />
                    {validationErrors.title && <span className="error-message">{validationErrors.title}</span>}
                    <br/>

                    <p>Descripción de la categoría:</p>
                    <input
                        id="description"
                        className="swal2-input"
                        value={categoryData.description}
                        onChange={(e) => setCategoryData({ ...categoryData, description: e.target.value })}
                        required
                    />
                    {validationErrors.description && <span className="error-message">{validationErrors.description}</span>}
                    <br/>

                    <p>Imagen de la categoría:</p>
                    <input
                        id="image"
                        className="swal2-input"
                        value={categoryData.image}
                        onChange={(e) => setCategoryData({ ...categoryData, image: e.target.value })}
                        required
                    />
                    {validationErrors.image && <span className="error-message">{validationErrors.image}</span>}
                    <br/>

                    <div>
                        <button onClick={handleModalSubmit} className="ok">OK</button>
                        <button onClick={closeModal} className="cancel">Cancel</button>
                    </div>
                </div>
            </Modal>
        </div>
    );
}

export default EditCategory;
