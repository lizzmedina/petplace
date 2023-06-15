import { createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    
    const validationUserUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/validation/`;
    const sendEmailUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/mail/send/`;
    const urlGetUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user/all`;
    const urlPostUsers = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/user`;
    const urlGetCities = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/cities`;
    const urlPostCities = `http://localhost:8080/api/v1/cities`; // corregir se tiene asi para pruebas en local
    const urlGetProducts = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;
    const urlPostProducts = `http://localhost:8080/api/v1/petDayCare`; // corregir se tiene asi para pruebas en local

    const [selectedCity, setSelectedCity] = useState(null);
    const [selectedDates, setSelectedDates] = useState([null, null]);
    const [recommends, setRecommends] = useState([]);
    const [searchResults, setSearchResults] = useState([]);
    const [title, setTitle] = useState('Recomendaciones');
    
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
        <ContextGlobal.Provider value={{searchResults, setSearchResults, title, setTitle, recommends, setRecommends, selectedDates, setSelectedDates, selectedCity, setSelectedCity, urlGetProducts, urlPostProducts, urlGetCities,urlPostCities,urlGetUsers, sendEmailUrl, urlPostUsers, validationUserUrl, getAllCategories, places,setPlaces, url, setUrl, dataCategory, setDataCategory}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)