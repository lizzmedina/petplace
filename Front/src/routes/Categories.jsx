import { useState } from "react";
import { Card } from "../components/CardCategories";
//import { useContextGlobal } from "../components/utils/global.constext";


export const Categories = ({categories}) => {

  // const {categories, images} = useContextGlobal();
  //const categories = useState['finca', 'casa De Familia', 'guardería multiple' ];
  const [places, setPlaaces] = useState([
    {
      category: 'finca',
      image: './images/finca1.jpg',
      quantity: 10,
    },
    {
      category: 'casa De Familia',
      image: './images/casa1.jpg',
      quantity: 20,
    },
    {
      category: 'guardería multiple',
      image: './images/guarderia1.jpg',
      quantity: 5,
    }
  ])

  return (
    <div className="categories-container">
      <h3>Buscar por tipo de alojamiento </h3>
    <div className="render-cards-categories">
      {places.map(place => (
        <Card 
          key={place.image}
          category={place.category}
          image={place.image}
          quantity={place.quantity}
        />
      ))}
    </div>
      
    </div>
  )
}
