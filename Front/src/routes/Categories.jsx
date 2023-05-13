import { useState } from "react";
import { Card } from "../components/CardCategories";
//import { useContextGlobal } from "../components/utils/global.constext";


export const Categories = ({categories}) => {

  // const {categories, images} = useContextGlobal();
  //const categories = useState['finca', 'casa De Familia', 'guardería multiple' ];
  const [places, setPlaaces] = useState([
    {
      id: 1,
      category: 'finca',
      image: './images/hermoso-labrador.avif',
      quantity: 10,
    },
    {
      id: 2,
      category: 'casa De Familia',
      image: './images/hermoso-labrador.avif',
      quantity: 20,
    },
    {
      id: 3,
      category: 'guardería multiple',
      image: './images/hermoso-labrador.avif',
      quantity: 5,
    }
  ])

  return (
    <div className="categories-container">
      <h3>Buscar por tipo de alojamiento </h3>
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
