import { createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    const [places, setPlaces] = useState([]);
    const getAllCategories = async()=> {
        const res = await fetch('http://localhost:8080/api/v1/category/all');
        const data = await res.json();
        setPlaces(data)
    }

    useEffect(() => {
        getAllCategories();
    }, []);


const[url, setUrl] = useState("http://localhost:8080/api/v1/petDayCare/all")

    // Estados y funciones para paginado

    const [dataCategory, setDataCategory] = useState([])
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setDataCategory(data);
            })
            .catch((error) => {
            // Manejo de errores aquí
                error('Error al obtener los datos:', error);
            });
    }, [url]);

    return (
        <ContextGlobal.Provider value={{getAllCategories, places,setPlaces, url, setUrl, dataCategory, setDataCategory}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
