import React, { useState, useEffect } from "react";
import Select from "react-select";
import { useContextGlobal } from "./utils/global.constext";

export const SearcherByLocation = () => {
    const [cities, setCities] = useState([]);
    const {selectedCity, setSelectedCity} = useContextGlobal();

    useEffect(() => {
        const fetchCities = async () => {
            try {
                const response = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`);
                const data = await response.json();
                const mappedCities = data.map((city) => ({
                    value: city.name.toLowerCase(),
                    label: city.name
                }));
                setCities(mappedCities);
            } catch (error) {
                console.error("Error al obtener las ciudades:", error);
            }
        };
    
        fetchCities();
    }, []);
    

    const handleSelectCity = (option) => {
        setSelectedCity(option);
    };
    console.log(selectedCity);
    return (
        <Select
        className="search-width"
        placeholder={selectedCity ? selectedCity.label : "¿Dónde estarás?"}
        value={selectedCity}
        options={cities}
        onChange={handleSelectCity}
        />
    );
};
