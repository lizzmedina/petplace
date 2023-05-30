import { createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    
    const validationUserUrl = "http://localhost:8080/api/v1/user/validation/";
    const sendEmailUrl = "http://localhost:8080/api/v1/mail/send/";
    const urlGetUsers = "http://localhost:8080/api/v1/user/all";
    const urlPostUsers = "http://127.0.0.1:8080/api/v1/user";

    const [places, setPlaces] = useState([]); // categorias
    const getAllCategories = async()=> {
        const res = await fetch('http://localhost:8080/api/v1/category/all');
        const data = await res.json();
        setPlaces(data)
    }
    useEffect(() => {
        getAllCategories();
    }, []);


    const[url, setUrl] = useState("http://localhost:8080/api/v1/petDayCare/all") // productos/hospedajes
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
        <ContextGlobal.Provider value={{urlGetUsers, sendEmailUrl, urlPostUsers, validationUserUrl, getAllCategories, places,setPlaces, url, setUrl, dataCategory, setDataCategory}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
