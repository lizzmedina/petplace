import { useState } from "react";
import { CardRecomends } from "../components/CardRecomends";


export const Recommends = () => {

  const [recommends, setRecommends] = useState([
    {
      category: 'finca',
      image: './images/field.png',
      quantity: 10,
    },
    {
      category: 'casa De Familia',
      image: './images/house.png',
      quantity: 20,
    },
    {
      category: 'guardería multiple',
      image: './images/building.png',
      quantity: 5,
    }
  ])

  return (
    <div className="recommends-container">
      <h3>Recomendaciones </h3>
      {recommends.map(recommend => (
        <CardRecomends
          key={recommend.image}
          category={recommend.category}
          image={recommend.image}
          quantity={recommend.quantity}
        />
      ))}
    </div>
  )
}
