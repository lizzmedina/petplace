import React, { useState, useEffect } from "react";
import { Country, City } from 'country-state-city';
import Select from "react-select";
import Swal from 'sweetalert2';
import * as yup from "yup";
import { useContextGlobal } from '../components/utils/global.constext';

function FormCity() {

    const {urlGetCities, urlPostCities} = useContextGlobal();

    /* Lo siguiente comentado es en caso de querer que aparezca cualquier pais */
    // const worldCountries = Country.getAllCountries().map((country) => ({
    //     value: country.isoCode,
    //     label: country.name
    // }));

    const worldCountries = [
        {
            value: "CO",
            label: "Colombia"
        }
    ];
    

    const [country, setCountry] = useState();
    const [cities, setCities] = useState([]);

    const [dataCity, setDataCity] = useState();

    const handleCountryChange = (selectedCountry) => {
        setCountry(selectedCountry.value);
        const countryCities = City.getCitiesOfCountry(selectedCountry.value).map(
            (city) => ({
                value: city.name,
                label: city.name
            })
        );
        setCities(countryCities);
    };

    /* Funcion paa generar el mapa de la ciudad */
    const generateLocationURL = () => {
        const concatenatedValue = dataCity ? `Colombia ${dataCity}` : `Colombia`;
        const encodedValue = encodeURIComponent(concatenatedValue);
        return `https://maps.google.com/maps?q=${encodedValue}&t=&z=13&ie=UTF8&iwloc=&output=embed`;
    };

    useEffect(() => {
        // Update the map URL whenever dataCity changes
        const mapURL = generateLocationURL();
        const mapFrame = document.getElementById("google_map");
        if (mapFrame) {
            mapFrame.src = mapURL;
        }
        console.log(dataCity);
    }, [dataCity]);

    /* Para editar los estilos en el componente Select */
    const colourStyles = {
        placeholder: (defaultStyles) => {
            return {
                ...defaultStyles,
                color: '#31363F',
            }
        }
    }

    /* se trae la informacion de ciudades en la base de datos para no repetir una ciudad y se generan validaciones con yup*/ 
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
        citySchema: yup
            .string()
            .required("La ciudad es obligatoria")
    })
    const [validationErrors, setValidationErrors] = useState({});


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await schema.validate({ citySchema: dataCity });

            const cityExists = citiesInDataBase.some((city) => city.name === dataCity);
            if (cityExists) {
                Swal.fire({
                    icon: 'error',
                    title: "La ciudad elegida ya ha sido creada previamente, intenta con otra."
                });
            } else {
                fetch(urlPostCities, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(dataCity),
                })
                    .then((response) => {
                        if (response.ok) {
                            Swal.fire({
                                icon: 'success',
                                title: `La ciudad ${dataCity.name} ha sido creada exitosamente.`
                            }).then(() => {
                                setCountry(null)
                                setDataCity(null)
                            });
                        } else {
                            throw new Error('Error en la solicitud de creación de la ciudad');
                        }
                    })
                    .catch((error) => {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: error.message
                        });
                    });
            }
        } catch (error) {
            setValidationErrors({ citySchema: error.message });
        }
    };


    return (
        <div>
            <div className="map-section">
                <div>
                    <div>
                        <div>
                            <iframe className='mapFrame' id="google_map"
                                src={generateLocationURL()} />
                        </div>
                    </div>
                </div>

                <div><form className="data-city" onSubmit={handleSubmit}>
                    <Select
                        className="select-formCity"
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
                    <div>
                        <Select
                            className="select-formCity"
                            placeholder={dataCity ? dataCity : "Seleccione una ciudad"}
                            value= {dataCity}
                            options={cities}
                            onChange={(e) => setDataCity(e.value)}
                            styles={colourStyles}
                        />
                        {validationErrors.citySchema && (<span className="error-message">{validationErrors.citySchema}</span>)}
                    </div>
                    
                    <div className="section-button">
                        <button className="button-1">Enviar</button>
                    </div>
                </form></div>
            </div>
        </div>
    );
}

export default FormCity;

