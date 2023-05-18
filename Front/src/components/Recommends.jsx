import { useState, useEffect } from "react";
import { CardRecomends } from "./CardRecomends";
import { Link } from 'react-router-dom'

export const Recommends = () => {

  const url = "http://localhost:8080/api/v1/petDayCare/all"

  const [recommends, setRecommends] = useState([])
  useEffect(() => {
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setRecommends(data);
      })
      .catch((error) => {
        console.error("Error fetching recommends:", error);
      });
  }, []);

  function shuffleArray(array) {
    // funcion para ordenar aleatoriamente el array
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }


  return (
    <div className="recommends-container">
      <h2 className="home-titles">Recomendaciones </h2>
      <div className="render-cards-recomends">
        {shuffleArray(recommends).slice(0, 8).map(recommend => (
          <Link key={recommend.id} to={'/Detail/' + recommend.id}>
            <CardRecomends
              key={recommend.id}
              type={recommend.type}
              name={recommend.name}
              image={recommend.image}
              capacity={recommend.capacity}
              rating={recommend.rating}
              ratingText={recommend.ratingText}
              iconoLocation={recommend.iconoLocation}
              city={recommend.city}
              address={recommend.address}
              service1={recommend.service1}
              service2={recommend.service2}
              detail={recommend.detail}
              basicPrice={recommend.basicPrice}
            />
          </Link>
        ))}
      </div>
    </div>
  )
}
