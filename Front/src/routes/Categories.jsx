import { useState, useEffect} from "react";
import { Card } from "../components/CardCategories";
import Stack from '@mui/material/Stack';
import { Pagination, useMediaQuery } from "@mui/material";


export const Categories = ({categories}) => {

    const [places, setPlaces] = useState([]);
    const url = 'http://localhost:8080/api/v1/category/all';
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setPlaces(data);
            })
            .catch((error) => {
            // Manejo de errores aquí
                error('Error al obtener las categorias:', error);
            });
    }, [url]);

    

  const isMobile = useMediaQuery('(max-width: 767px)'); // Verifica si es un dispositivo móvil
  const isTablet = useMediaQuery('(max-width: 1024px)'); // Verifica si es una tablet
  
  // Determina la cantidad de tarjetas a mostrar en función del tamaño de la pantalla
  const cardsPerRow = isMobile ? 1 : isTablet ? 2 : 4;
  // Cantidad de tarjetas por página
  const cardsPerPage = isMobile ? 1 : isTablet ? 2 : 4; 
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
          <Card 
            key={place.id}
            title={place.title}
            image={place.image}
            description={place.description}
          />
        ))}
      </div>

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
