import { useState } from "react";
import { Product } from "../components/Product.jsx";


const Details = () => {

  const [details, setDetails] = useState([
    {
      id: 1,
      name: "Huellas de amor",
      type: "Perros",
      capacity: 50,
      city: "Cali",
      address: "Calle 3, via Yumbo",
      detail: " Nuestra guardería está ubicada en un entorno natural y tranquilo, ideal para el descanso de los animales.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 2,
      name: "Pet Resort",
      type: "Gatos",
      capacity: 50,
      city: "Medellín",
      address: "Calle 3, via Yumbo",
      detail: " Disponemos de un área de descanso cómoda y segura para que tu perro pueda relajarse.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 3,
      name: "Paw Palace",
      type: "Conejos",
      capacity: 50,
      city: "Barranquilla",
      address: "Calle 3, via Yumbo",
      detail: " sOfrecemos servicio de baño y peluquería para que tu perro vuelva a casa limpio y fresco.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 4,
      name: "Furry Friends Retreat",
      type: "Canarios",
      capacity: 50,
      city: "Cúcuta",
      address: "Calle 3, via Yumbo",
      detail: " Nuestra guardería cuenta con un sistema de vigilancia 24/7 para garantizar la seguridad de todos los animales.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 5,
      name: "Critter Care Center",
      type: "Perros",
      capacity: 50,
      city: "Cartagena de Indias",
      address: "Calle 3, via Yumbo",
      detail: " Nuestra guardería está ubicada en un entorno natural y tranquilo, ideal para el descanso de los animales.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 6,
      name: "Paws and Claws Lodge",
      type: "Gatos",
      capacity: 50,
      city: "Bucaramanga",
      address: "Calle 3, via Yumbo",
      detail: " Tenemos un espacio para que los perros más tímidos puedan estar en un ambiente tranquilo",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 7,
      name: "Happy Tails Inn",
      type: "Conejos",
      capacity: 50,
      city: "Pereira",
      address: "Calle 3, via Yumbo",
      detail: " Ofrecemos la posibilidad de reservar una habitación privada para tu perro si necesita un espacio más tranquilo.",
      image: "/images.png",
      basicPrice: 30.000
},
{
      id: 8,
      name: "Bark Avenue Boarding",
      type: "Canarios",
      capacity: 50,
      city: "Santa Marta",
      address: "Calle 3, via Yumbo",
      detail: " Tenemos un espacio para que los perros más tímidos puedan estar en un ambiente tranquilo.",
      image: "/images.png",
      basicPrice: 30.000
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
    <div className="detail-container">  
      {shuffleArray(details).slice(0, 1).map(details => (
          
          <Product
            id={details.id}
            type={details.type}
            name={details.name}
            image={details.image}
            capacity={details.capacity}
            address={details.address}
            city={details.city}
            detail={details.detail}
            basicPrice={details.basicPrice}
          />
          ))}
      

    </div>
  )
}

export default Details;