import { Children, createContext, useContext, useState, useEffect } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    // simulacion de llamada a la API para obtener productos
    const[url, setUrl] = useState("http://localhost:8080/api/v1/petDayCare/all")

    // Estados y funciones para paginado

    const [dataCategory, setDataCategory] = useState([])
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setDataCategory(data);
                setItems(data.slice(0, itemsPerPage));
            })
            .catch((error) => {
            // Manejo de errores aquí
                error('Error al obtener los datos:', error);
            });
    }, [url]);

    const itemsPerPage = 10
    const[items, setItems] = useState([...dataCategory].splice(0, itemsPerPage)) 

    const[currentPage, setCurrentPage] = useState(0)

    const nextHandler = () => {
        const totalElementos = dataCategory.length
        const nextPage = currentPage + 1
        const firstIndex = nextPage * itemsPerPage
        if (firstIndex >= totalElementos) return;
        
        setItems([...dataCategory].splice(firstIndex, itemsPerPage))
        setCurrentPage(nextPage)
    }

    const prevHandler = () => {
        const prevPage = currentPage -1;
        if (prevPage <0) return;
        const firstIndex = prevPage * itemsPerPage;

        setItems([...dataCategory].splice(firstIndex, itemsPerPage))
        setCurrentPage(prevPage)
    }

    const startHandler = () => {
        setItems([...dataCategory].splice(0, itemsPerPage))
        setCurrentPage(0)
    }

    const endHandler = () => {
        const lastPage = dataCategory.length % itemsPerPage ===0 
            ? Math.floor(dataCategory.length/itemsPerPage) -1 : Math.floor(dataCategory.length/itemsPerPage)
        const firstIndex = lastPage * itemsPerPage;

        setItems([...dataCategory].splice(firstIndex, itemsPerPage))
        setCurrentPage(lastPage)
    }

    return (
        <ContextGlobal.Provider value={{url, setUrl, dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage, prevHandler, nextHandler, startHandler, endHandler}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
