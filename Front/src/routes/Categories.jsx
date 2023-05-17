import { useState } from "react";
import { Card } from "../components/CardCategories";

export const Categories = ({categories}) => {

  const [places, setPlaaces] = useState([
    {
      id: 1,
      category: 'Perros',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Fincas',
    },
    {
      id: 2,
      category: 'Gatos',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Casas',
    },
    {
      id: 3,
      category: 'Canarios',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Guarderias',
    },
    {
      id: 4,
      category: 'Conejos',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Guarderias',
    }
  ])

  return (
    <div className = "categories-container">
      <h2 className="home-titles">Buscar según el tipo de mascota </h2>
      <div className = "render-cards-categories">
        {places.map(place => (
          <Card 
            key={place.id}
            category={place.category}
            image={place.image}
            quantity={place.quantity}
          />
        ))}
      </div>
    </div>
  )
}
