import { Children, createContext, useContext, useState } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    // simulacion de llamada a la API para obtener productos
    const datosAPI = Array.from({length:60}, (value, index) => {
        return {id: index, title: `Item #${index}`}
    })


    // Estados y funciones para paginado

    const [dataCategory, setDataCategory] = useState(datosAPI)

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
        <ContextGlobal.Provider value={{dataCategory, setDataCategory, items, setItems, currentPage, setCurrentPage, prevHandler, nextHandler, startHandler, endHandler}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
