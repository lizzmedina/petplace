import { useState } from "react";
import { Card } from "../components/CardCategories";

export const Categories = ({categories}) => {

  const [places, setPlaaces] = useState([
    {
      id: 1,
      category: 'Perros',
      image: 'https://img.freepik.com/free-psd/group-portrait-adorable-puppies_53876-73962.jpg?w=1380&t=st=1684284771~exp=1684285371~hmac=112f48515e6ce16541da2e8d02b6e4d921e0e4b03d7a16778606b1aae672e3a6',
      quantity: '20 Fincas',
    },
    {
      id: 2,
      category: 'Gatos',
      image: 'https://img.freepik.com/premium-photo/birman-cats-looking-camera_191971-18018.jpg?w=900',
      quantity: '20 Casas',
    },
    {
      id: 3,
      category: 'Canarios',
      image: 'https://img.freepik.com/free-photo/cape-may-warbler_181624-6537.jpg?w=1060&t=st=1684283666~exp=1684284266~hmac=4954a4d4ea1ea030ce2203f072f4002700b8c9307719df86cf89c8671a902f9c',
      quantity: '20 Guarderias',
    },
    {
      id: 4,
      category: 'Conejos',
      image: 'https://img.freepik.com/free-photo/furry-cute-rabbit-isolated_78492-3950.jpg?w=996&t=st=1684283969~exp=1684284569~hmac=7d171e3194b330b91771c73920494d0fcac6fd4c9926091e3ebda94fb6d5d3b4',
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
