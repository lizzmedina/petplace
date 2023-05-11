import { Children, createContext, useContext, useState } from "react";

export const ContextGlobal = createContext(undefined);


export const ContextProvider = ({children}) => {

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

    return (
        <ContextGlobal.Provider value={{}}>
            {Children}
        </ContextGlobal.Provider>
    )
}
export default ContextProvider;
export const useContextGlobal = () => useContext(ContextGlobal)
