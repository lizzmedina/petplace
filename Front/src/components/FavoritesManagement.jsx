import React, { useContext, useEffect } from 'react';
import { GlobalContext } from './utils/global.constext';

export const FavoritesManagement = () => {
    const { favorites } = useContext(GlobalContext);



    return (
        <div>
        <h2>gestionando favoritos ...</h2>
       
        </div>
    );
};
