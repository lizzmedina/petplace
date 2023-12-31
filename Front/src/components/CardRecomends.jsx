import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {  faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot, faHeart} from "@fortawesome/free-solid-svg-icons";
import {  faHeart as fasHeart} from "@fortawesome/free-regular-svg-icons";
import { Grid, Rating } from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";
import { Link } from "react-router-dom";
import  { useEffect, useState,  } from "react";

export const CardRecomends = ({number,image,type,name,characteristics,city,address,detail,capacity,basicPrice,rating,}) => {

  const { favorites, setFavorites,  urlPostFavorites, urlFavorites } = useContextGlobal();
  const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null;
  //const [favoriteMap, setFavoriteMap] = useState({});
  const [isFavorite, setIsFavorite] = useState(null);

 //const updateFavoriteMap = () => {
 //  const isFavorite = favorites.some((fav) => fav.petDayCareId === number);
 //  setFavoriteMap((prevMap) => ({
 //    ...prevMap,
 //    [number]: isFavorite,
 //  }));
 //};

  if (!image) {
    return image;
  }
  const renderCharacteristics = () => {
    const icons = {
      Paseo: faPersonWalkingWithCane,
      Baño: faShower,
      Alimentación: faCarrot,
      Veterinaria: faStethoscope,
      Entrenamiento: faBaseball,
    };

    return characteristics.map((option, index) => {
      const icon = icons[option];
      if (
        icon &&
        (option === "Paseo" ||
          option === "Baño" ||
          option === "Alimentación" ||
          option === "Veterinaria" ||
          option === "Entrenamiento")
      ) {
        return (
          <span key={index}>
            <FontAwesomeIcon icon={icon} className="card-services-recommends" />
          </span>
        );
      }
    });
  };

  const truncateDetail = (text) => {
    if (text.length > 100) {
      return text.substring(0, 100) + "...";
    }
    return text;
  };

  const showRating = () => {
    return rating !== null && rating !== undefined;
  };

  //función auxiliar para buscar favoritos por userId:
  const fetchFavorites = async () => {
    const userConnected = JSON.parse(localStorage.getItem("userConnected"));

    if (userConnected) {
        const userId = userConnected.id;
        await fetch(`${urlFavorites}${userId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => response.json())
            .then((data) => {
                setFavorites(data);
            })
            .catch((error) => {
                console.error(error);
            });
    }
};

  const handleFavorite = async () => {
    const petDayCareId = number;
    const userConnected = JSON.parse(localStorage.getItem('userConnected'));
    const userId = userConnected.id;

    if (userId) {
        const newFavorite = {
            idFavorite: 0,
            userId: parseInt(userId),
            petDayCareId: petDayCareId,
        };
    
        try {
          const url = `${urlPostFavorites}${userId}&petDayCareId=${petDayCareId}`;
          
          const response = await fetch(url, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify(newFavorite),
          });
          if (response.ok) {
            await fetchFavorites();  
            setIsFavorite(null); 
          }else {
              console.error('Error al agregar o quitar el producto de favoritos');
          }
        } catch (error) {
              console.error('Error al hacer la solicitud POST de favoritos:', error);
        }
    } else {
        console.error('No se encontró el userId en el localStorage');
    }
};
  const checkFavorite = () => {
    return( favorites?.filter ( f => f?.petDayCare?.id === number)).length > 0;
  }

  useEffect(() => {
    if (isFavorite === null) 
      setIsFavorite(checkFavorite());
    
  }, [isFavorite, setIsFavorite ]);

  return (
    <div className="card-recomends">

      <div className="card-content">
      {userConnected ? (
          (
            <FontAwesomeIcon
              icon={ isFavorite ? faHeart : fasHeart }
              style={ isFavorite ? { color: "red" } :{ color: "#fff" }}
              className="card-favorite-icon"
              onClick={handleFavorite}
              beat = { isFavorite ? true : false}
            />
          ) 
        ) : null}

      <div className="left-card-content">
          <img
            className="card-image-recommends"
            src={image[0]}
            alt={type.title}
          />
      </div>
      <div className="right-card-content">
        <div className="card-head-recommends">
          <h3 className="card-title-recommends">{name}</h3>
        <div>
            {showRating() ? (
              <span className="card-head-recommends">
                <span className="rating-value">{rating.average}</span>
                <Rating
                  className="rating-value-star"
                  defaultValue={1}
                  max={1}
                  readOnly
                />
              </span>

            ) : null}
        </div>
      </div>

          <span className="card-category-recommends">
            Habilitado para: {capacity} {type.title}{" "}
          </span>
          <p className="card-location-recommends">
            <FontAwesomeIcon
              icon={faLocationDot}
              className="card-location-icon"
            />
            {city.name}, {address}
          </p>
          <span className="card-services-list-recommends">
            {renderCharacteristics()}
          </span>
          <p className="card-descrption-recommends">{truncateDetail(detail)}</p>
          <Link className="button-2" key={number} to={"/Detail/" + number}>
            <button className="button-2">Ver más</button>
          </Link>
        </div>
      </div>
    </div>
  );
};
