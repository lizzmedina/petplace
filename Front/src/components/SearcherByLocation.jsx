import React, { useState, useEffect } from "react";
import Select from "react-select";

export const SearcherByLocation = () => {
    const [cities, setCities] = useState([]);
    const [selectedOption, setSelectedOption] = useState(null);

    useEffect(() => {
        const fetchCities = async () => {
        try {
            const response = await fetch(
            `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`
            );
            const data = await response.json();
            const mappedCities = data.map((city) => ({
            value: city.name.toLowerCase(),
            label: city.name,
            }));
            setCities(mappedCities);
        } catch (error) {
            console.error("Error al obtener las ciudades:", error);
        }
        };

        fetchCities();
    }, []);

    const handleSelectCity = (option) => {
        setSelectedOption(option);
    };

    return (
        <Select
            className="search-width"
            placeholder={selectedOption ? selectedOption.label : "¿Dónde estarás?"}
            value={selectedOption}
            options={cities}
            onChange={handleSelectCity}
        />
    );
};
