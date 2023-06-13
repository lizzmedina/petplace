import { createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    
    const validationUserUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/validation/`;
    const sendEmailUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/mail/send/`;
    const urlGetUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`;
    const urlPostUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user`;
    const urlGetCities = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`;
    const urlPostCities = `http://localhost:8080/api/v1/cities`; // corregir
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

    const [places, setPlaces] = useState([]); // categorias
    const getAllCategories = async()=> {
        const res = await fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/category/all`);
        const data = await res.json();
        setPlaces(data)
    }
    useEffect(() => {
        getAllCategories();
    }, []);


    const[url, setUrl] = useState(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`) // productos/hospedajes
    const [dataCategory, setDataCategory] = useState([])
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setDataCategory(data);
            })
            .catch((error) => {
                error('Error al obtener los datos:', error);
            });
    }, [url]);

    return (
        <ContextGlobal.Provider value={{searchContextValue, urlGetCities,urlPostCities,urlGetUsers, sendEmailUrl, urlPostUsers, validationUserUrl, getAllCategories, places,setPlaces, url, setUrl, dataCategory, setDataCategory}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
