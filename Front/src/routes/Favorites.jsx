import React, { useState, useEffect } from "react";
import { CardRecomends } from "../components/CardRecomends";
import { useContextGlobal } from "../components/utils/global.constext";

export const Favorites = () => {
    const [favorites, setFavorites] = useState([]);
    const {urlFavorites} = useContextGlobal;

    useEffect(() => {
      // Obtener el ID del usuario del localStorage
        const userConnected = JSON.parse(localStorage.getItem("userConnected"));
        const userId = userConnected.id;

      // Realizar la solicitud GET con el ID del usuario
    fetch(`${urlFavorites}${userId}`)
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
        <div className="space-section favorites-container">
            <h2>Favorites</h2>
            {favorites.length === 0 ? (
            <h3>Aún no se han agregado hospedajes a favoritos.</h3>
            ) : (
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
            )}
        </div>
    );
};