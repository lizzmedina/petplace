import { useState } from "react";
import { CardRecomends } from "./CardRecomends";


export const Recommends = () => {

  const [recommends, setRecommends] = useState([
    {
      id: 1,
      category: 'finca',
      name: "El ensueño",
      image: './images/finca1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      iconoLocation: './images/iconolocation.png',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBano.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 2,
      category: 'casa de familia',
      name: "Los Peréz",
      image: './images/casa1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 3,
      category: 'guardería multiple',
      name: "Mi amigo fiel",
      image: './images/guarderia1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 4,
      category: 'Finca',
      name: "Claroluna",
      image: './images/guarderia2.jpg',
      rating: 9,
      ratingText: 'muy bueno',
      location: 'A 2 km del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioPiscina.png',
      service2: './images/iconoServicioBano.png',
      description: 'Hermoso hotel en la costa de Cancún, con vistas espectaculares y servicios de primera calidad.',
    },
    {
      id: 5,
      category: 'Casa de familia',
      name: "Los Spano",
      image: './images/casa2.jpg',
      rating: 8,
      ratingText: 'bueno',
      location: 'A 500 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioCocina.png',
      service2: './images/iconoServicioLavanderia.png',
      description: 'Amplio apartamento con todas las comodidades, ideal para grupos y familias. Excelente ubicación en el centro de la ciudad.',
    },
    {
      id: 6,
      category: 'Gueraderia multiple',
      name: "Gorditos y bonitos",
      image: './images/guarderia3.jpg',
      rating: 7,
      ratingText: 'bueno',
      location: 'A 1 km del centro MOSTRAR EN EL MAPA',
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
      <h3>Recomendaciones </h3>
      
      <div className="render-cards-recomends">
        {shuffleArray(recommends).slice(0, 3).map(recommend => (
          <CardRecomends
            key={recommend.id}
            category={recommend.category}
            name={recommend.name}
            image={recommend.image}
            quantity={recommend.quantity}
            rating={recommend.rating}
            ratingText={recommend.ratingText}
            iconoLocation={recommend.iconoLocation}
            location={recommend.location}
            service1={recommend.service1}
            service2={recommend.service2}
            description={recommend.description}
          />
        ))}
      </div>

    </div>
  )
}
