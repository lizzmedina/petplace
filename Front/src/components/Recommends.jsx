import { useState } from "react";
import { CardRecomends } from "./CardRecomends";


export const Recommends = () => {

  const [recommends, setRecommends] = useState([
    {
      id: 1,
      category: 'finca',
      name: "El ensueño",
      image: './images/hermoso-labrador.avif',
      rating: 10,
      ratingText: 'muy bueno',
      iconoLocation: './images/iconolocation.png',
      location: 'A 940 m del centro',
      service1: './images/iconoServicioBano.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 2,
      category: 'casa de familia',
      name: "Los Peréz",
      image: './images/hermoso-labrador.avif',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 3,
      category: 'guardería multiple',
      name: "Mi amigo fiel",
      image: './images/hermoso-labrador.avif',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 4,
      category: 'Finca',
      name: "Claroluna",
      image: './images/hermoso-labrador.avif',
      rating: 9,
      ratingText: 'muy bueno',
      location: 'A 2 km del centro',
      service1: './images/iconoServicioPiscina.png',
      service2: './images/iconoServicioBano.png',
      description: 'Hermoso hotel en la costa de Cancún, con vistas espectaculares y servicios de primera calidad.',
    },
    {
      id: 5,
      category: 'Casa de familia',
      name: "Los Spano",
      image: './images/hermoso-labrador.avif',
      rating: 8,
      ratingText: 'bueno',
      location: 'A 500 m del centro',
      service1: './images/iconoServicioCocina.png',
      service2: './images/iconoServicioLavanderia.png',
      description: 'Amplio apartamento con todas las comodidades, ideal para grupos y familias. Excelente ubicación en el centro de la ciudad.',
    },
    {
      id: 6,
      category: 'Gueraderia multiple',
      name: "Gorditos y bonitos",
      image: './images/hermoso-labrador.avif',
      rating: 7,
      ratingText: 'bueno',
      location: 'A 1 km del centro',
      service1: './images/iconoServicioWifi.png',
      service2: './images/iconoServicioRecepcion24.png',
      description: 'Acogedor hostal con ambiente joven y divertido. Buena ubicación y excelente relación calidad-precio.',
    },
  ])

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
      <h2>Recomendaciones </h2>
      
      <div className="render-cards-recomends">
        {shuffleArray(recommends).slice(0, 8).map(recommend => (
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
        ))}
      </div>

    </div>
  )
}
