import { useState} from "react";
import { Card } from "./CardCategories";
import Stack from '@mui/material/Stack';
import { Pagination, useMediaQuery } from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";
import { Link } from 'react-router-dom';

export const Categories = () => {

  const {places} = useContextGlobal();

  const isMobile = useMediaQuery('(max-width: 767px)'); // Verifica si es un dispositivo móvil
  const isTablet = useMediaQuery('(max-width: 1024px)'); // Verifica si es una tablet
  
  let cardsPerRow;
  let cardsPerPage;
  if (isMobile) {
    cardsPerRow = 1;
    cardsPerPage = 1;
  } else if (isTablet) {
    cardsPerRow = 2;
    cardsPerPage = 2;
  } else {
    cardsPerRow = 4;
    cardsPerPage = 4;
  }
// Número total de páginas
  const totalPages = Math.ceil(places.length / cardsPerPage); 
  const [currentPage, setCurrentPage] = useState(1); // Página actual

  // Calcula el índice inicial y final de los items a mostrar en la página actual
  const startIndex = (currentPage - 1) * cardsPerRow;
  const endIndex = startIndex + cardsPerRow;

  // Obtén las tarjetas para la página actual
  const currentCards = places.slice(startIndex, endIndex);
  
  // Función para cambiar la página
  const handlePageChange = (event, page) => {
    setCurrentPage(page);
  };
  
  return (

    <div className = "categories-container">
      <h2 className="home-titles">Buscar según el tipo de mascota </h2>
      <div className = "render-cards-categories">
        {
          currentCards.map(place => (
            <Link to={"/category/" + place.id}>
              <Card
                key={place.id}
                title={place.title}
                image={place.image}
                description={place.description}
              />
            </Link>
          ))
        }
      </div>
      console.log(place.id);
      <Stack spacing={5} direction="row" justifyContent="center" mt={4}>
        <Pagination
          count={totalPages}
          page={currentPage}
          onChange={handlePageChange}
        />
      </Stack>
    </div>
  )
}
