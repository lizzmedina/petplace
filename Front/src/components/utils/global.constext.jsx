import { Children, createContext, useContext, useState } from "react";

const ContextGlobal = createContext();


const ContextProvider = ({children}) => {

    // const imagesCategories = useState[
    //     {
    //         finca: 'finca',
    //         url: './images/field.png',
    //     },
    //     {casaDeFamilia: 'casa De Familia',
    //     url: './images/house.png',
    //     },
    //     {
    //         guarderiaMultiple: 'guardería multiple',
    //         url: './images/building.png'
    //     }
    // ]


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
