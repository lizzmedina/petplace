import React, { useState, useEffect } from "react";
import { CardRecomends } from "../components/CardRecomends";

export const Favorites = () => {
    const [favorites, setFavorites] = useState([]);

    useEffect(() => {
        // Obtener el ID del usuario del localStorage
        const userConnected = JSON.parse(localStorage.getItem("userConnected"));
        const userId = userConnected.id;

        // Realizar la solicitud GET con el ID del usuario
        fetch(`http://localhost:8080/api/v1/favorite/list?id=${userId}`)
        .then((response) => response.json())
        .then((data) => {
            // Manejar la respuesta
            setFavorites(data);
        })
        .catch((error) => {
            // Manejar el error
            console.error(error);
        });
    }, []);

    return (
        <div className="space-section">
        <h2>Favorites</h2>
        <div className="render-cards-recommends">
            {favorites.map((favorite) => {
            const petDayCare = favorite.petDayCare;
            return (
            <CardRecomends
                key={petDayCare.id}
                number={petDayCare.id}
                type={petDayCare.type.title}
                name={petDayCare.name}
                image={petDayCare.images}
                capacity={petDayCare.capacity}
                rating={petDayCare.average}
                city={petDayCare.city.name}
                address={petDayCare.address}
                detail={petDayCare.detail}
                basicPrice={petDayCare.basicPrice}
                characteristics={petDayCare.characteristics}
            />
            );
        })}
        </div>
        
        </div>
    );
};
