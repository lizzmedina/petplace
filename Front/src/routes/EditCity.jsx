import React, { useState, useEffect } from 'react';
import { Country, City } from 'country-state-city';
import Select from 'react-select';
import Swal from 'sweetalert2';
import * as yup from 'yup';
import { useContextGlobal } from '../components/utils/global.constext';
import Modal from 'react-modal';

function EditCity() {
    const { urlGetCities, urlPostCities } = useContextGlobal();

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;

    /* Logica de Paises */

    const worldCountries = [
        {
            value: 'CO',
            label: 'Colombia'
        }
    ];

    const [country, setCountry] = useState();
    const [cities, setCities] = useState([]);

    const [dataCity, setDataCity] = useState();
    const [selectedCity, setSelectedCity] = useState(null);

    const handleCountryChange = (selectedCountry) => {
        setCountry(selectedCountry.value);
        const countryCities = City.getCitiesOfCountry(selectedCountry.value).map((city) => ({
            value: city.name,
            label: city.name
        }));
        setCities(countryCities);
    };

    const [citiesInDataBase, setCitiesInDataBase] = useState([]);
    const getAllCitiesDataBase = async () => {
        const res = await fetch(urlGetCities);
        const data = await res.json();
        setCitiesInDataBase(data);
    };
    useEffect(() => {
        getAllCitiesDataBase();
    }, []);

    const schema = yup.object().shape({
        citySchema: yup.string().required('La ciudad es obligatoria')
    });
    const [validationErrors, setValidationErrors] = useState({});


    const deleteCity = async (id, name) => {
        const confirmDelete = await Swal.fire({
            title: `¿Seguro deseas eliminar el usuario ${name}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Eliminar',
            cancelButtonText: 'Cancelar',
            reverseButtons: true
        });
        if (confirmDelete.isConfirmed) {
            await fetch(`${urlPostCities}/force/${id}`, {
                method: 'DELETE'
            });
            getAllCitiesDataBase();
        }
    };

    /* Para editar los estilos en el componente Select */
    const colourStyles = {
        placeholder: (defaultStyles) => {
            return {
                ...defaultStyles,
                color: '#31363F',
            }
        }
    }

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedCity(null);
        setDataCity('');
        setCountry('');
    };

    const openEditForm = (city) => {
        setSelectedCity(city);
        setDataCity(city.name);
        openModal();
    };

    const modalStyles = {
        content: {
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: '90%',
            maxWidth: '30rem',
            margin: '10px'
        }
    };

    const handleModalSubmit = () => {
        if (!country || !dataCity) {
            Swal.fire('Debes completar todos los campos');
            return;
        } 
        const cityExists = citiesInDataBase.some((city) => city.name === dataCity);
        if (cityExists) {
            Swal.fire({
                icon: 'error',
                title: "La ciudad elegida ya ha sido creada previamente, intenta con otra."
            });
        } else {
            const updatedCity = {
                id: selectedCity.id,
                name: dataCity
            };
    
            fetch(urlPostCities, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedCity)
            })
                .then((response) => response.json())
                .then((data) => {
                    Swal.fire({ icon: 'success', title: `Ciudad ${dataCity} actualizada con éxito` });
                    getAllCitiesDataBase();
                    closeModal();
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        }
    };

    return (
        <div className="space-section">
            <h2>{userConnected.type !== 'Manager' ? 'Página no encontrada' : 'Lista de Ciudades'}</h2>
            <br />
            <br />

            {userConnected.type === 'Manager' &&
                citiesInDataBase.map((city) => (
                    <div className="ListItem" key={city.id}>
                        {city.name}
                        <div className="ListButtons">
                            <button className="edit-button" onClick={() => openEditForm(city)}>
                                Editar
                            </button>
                            <button className="delete-button" onClick={() => deleteCity(city.id, city.name)}>
                                Eliminar
                            </button>
                        </div>
                    </div>
                ))}

            <Modal isOpen={isModalOpen} onRequestClose={closeModal} style={modalStyles}>
                <div className="city-modal">
                    <h2>Formulario de Edición</h2>
                    <br />
                    <p>Pais:</p>
                    <Select
                        id="pais"
                        className="selectFormCity"
                        placeholder={
                            country
                                ? worldCountries.find((c) => c.value === country)?.label ||
                                "Seleccione un país"
                                : "Seleccione un país"
                        }
                        value={country}
                        options={worldCountries}
                        onChange={handleCountryChange}
                        styles={colourStyles}
                    />
                    <p>Ciudad:</p>
                    <Select
                        id="ciudad"
                        className="selectFormCity"
                        placeholder='Seleccione una ciudad'
                        options={cities}
                        onChange={(e) => setDataCity(e.value)}
                        styles={colourStyles}
                    />
                    {validationErrors.citySchema && <span className="error-message">{validationErrors.citySchema}</span>}
                    <div>
                        <button onClick={handleModalSubmit} className="ok">OK</button>
                        <button onClick={closeModal} className="cancel">Cancel</button>
                    </div>
                </div>
            </Modal>
        </div>
    );
}

export default EditCity;
