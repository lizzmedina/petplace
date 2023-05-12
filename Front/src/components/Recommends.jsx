import { useState } from "react";
import { CardRecomends } from "./CardRecomends";


export const Recommends = () => {

  const [recommends, setRecommends] = useState([
    {
      id: 123456,
      category: 'finca',
      image: './images/finca1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      iconoLocation: './images/iconolocation.png',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 36576,
      category: 'casa de familia',
      image: './images/casa1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    },
    {
      id: 654689,
      category: 'guardería multiple',
      image: './images/guarderia1.jpg',
      rating: 10,
      ratingText: 'muy bueno',
      location: 'A 940 m del centro MOSTRAR EN EL MAPA',
      service1: './images/iconoServicioBaño.png',
      service2: './images/iconoServicioPisina.png',
      description: 'En el corazón de San Telmo, disfruta de un albergue inspirado en las pasiones de Buenos Aires. más...',
    }
  ])

  return (
    <div className="recommends-container">
      <h3>Recomendaciones </h3>
    <div className="render-cards-recomends">
      {recommends.map(recommend => (
        <CardRecomends
          key={recommend.id}
          category={recommend.category}
          image={recommend.image}
          quantity={recommend.quantity}
          rating = {recommend.rating}
          ratingText= {recommend.ratingText}
          iconoLocation = {recommend.iconoLocation}
          location = {recommend.location}
          service1 = {recommend.service1}
          service2 = {recommend.service2}
          description = {recommend.description}
        />
      ))}
    </div>
      
    </div>
  )
}
