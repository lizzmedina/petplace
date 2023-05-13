import { Children, createContext, useContext, useState } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    
    const prevHandler = () => {
        console.log("prev");
    }

    const nextHandler = () => {
        console.log("next");
    }

    return (
        <ContextGlobal.Provider value={{prevHandler, nextHandler}}>
            {children}
        </ContextGlobal.Provider>
    )
}

export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
