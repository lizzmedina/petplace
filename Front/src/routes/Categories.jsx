import { useState } from "react";
import { Card } from "../components/CardCategories";



export const Categories = ({categories}) => {

  const [places, setPlaaces] = useState([
    {
      id: 1,
      category: 'Finca',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Fincas',
    },
    {
      id: 2,
      category: 'Casa',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Casas',
    },
    {
      id: 3,
      category: 'Guarderia',
      image: './images/hermoso-labrador.avif',
      quantity: '20 Guarderias',
    }
  ])

  return (
    <div className="categories-container">
      <h2>Buscar por tipo de alojamiento </h2>
    <div className="render-cards-categories">
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
