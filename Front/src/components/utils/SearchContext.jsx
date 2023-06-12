import React, { createContext, useState } from "react";

export const SearchContext = createContext();

export const SearchProvider = ({ children }) => {
    const [selectedOption, setSelectedOption] = useState(null);
    const [dates, setDates] = useState([]);
    const [searchResults, setSearchResults] = useState([]);
    const [searchTitle, setSearchTitle] = useState("Recomendaciones");

    const handleSelectCity = (option) => {
        setSelectedOption(option);
    };

    const handleDateChange = (dates) => {
        setDates(dates);
    };

    const handleSearch = async () => {
        if (!selectedOption) {
        // No se ha seleccionado una ciudad, realizar alguna validación o mostrar un mensaje de error
        return;
        }

        try {
        const city = selectedOption.value;
        const startDate = dates[0] ? dates[0].format("YYYY-MM-DD") : null;
        const endDate = dates[1] ? dates[1].format("YYYY-MM-DD") : null;

        // Realizar la solicitud a la API con los parámetros de búsqueda
        const response = await fetch(
            `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/${city}?startDate=${startDate}&endDate=${endDate}`
        );

        if (response.ok) {
            const data = await response.json();
            setSearchResults(data);
            setSearchTitle("Resultados de búsqueda");
        } else {
            console.error("Error al realizar la búsqueda");
        }
        } catch (error) {
        console.error("Error al realizar la búsqueda:", error);
        }
    };

    const searchContextValue = {
        selectedOption,
        handleSelectCity,
        dates,
        handleDateChange,
        searchResults,
        searchTitle,
        handleSearch,
    };

    return (
        <SearchContext.Provider value={searchContextValue}>
        {children}
        </SearchContext.Provider>
    );
};
