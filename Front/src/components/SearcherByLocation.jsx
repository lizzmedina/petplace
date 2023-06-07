import React, { useState } from "react";
import Select from "react-select";

export const SearcherByLocation = () => {
    
    const options = [
        { value: 'cali', label: 'Cali' },
        { value: 'medellin', label: 'Medellín' },
        { value: 'manizales', label: 'Manizales' },
        { value: 'bogota', label: 'Bogotá' },
        { value: 'barranquilla', label: 'Barranquilla' },
        { value: 'cucuta', label: 'Cúcuta' },
        { value: 'cartagena', label: 'Cartagena' },
        { value: 'bucaramanga', label: 'Bucaramanga' },
        { value: 'pereira', label: 'Pereira' },
        { value: 'santa marta', label: 'Santa Marta' },
        { value: 'ibague', label: 'Ibagué' },
    ];

    const [selectedOption, setSelectedOption] = useState(null);

    const handleSelectCity = (option) => {
        setSelectedOption(option);
    };
    console.log(selectedOption?.value);
    return (
        <Select
            className="search-width"
            placeholder={selectedOption ? selectedOption.label : "¿Dónde estarás?"}
            value={selectedOption}
            options={options}
            onChange={handleSelectCity}
        />
    );
};
