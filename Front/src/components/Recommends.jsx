import { useState, useEffect } from "react";
import { CardRecomends } from "./CardRecomends";
import { Link } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import { Pagination} from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";

export const Recommends = () => {
  
  const {recommends, setRecommends,  title, selectedCity, searchResults, setSearchResults} = useContextGlobal();
  const [noResults, setNoResults] = useState(false);
  
  useEffect(() => {
    if (searchResults.length > 0) {
      setRecommends(searchResults);
      setNoResults(false);
    } else {
      fetchRecommends();
    }
  }, [searchResults]);
  
  useEffect(() => {
    setNoResults(recommends.length === 0);
  }, [recommends]);


  const fetchRecommends = () => {
    const url = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setRecommends(data);
        setSearchResults(data);
        setNoResults(data.length === 0);
      })
      .catch((error) => {
        console.error("Error al obtener las recomendaciones:", error);
      });
  };
  // Determina la cantidad de tarjetas a mostrar en función del tamaño de la pantalla
  let cardsPerRow =10;

  // Cantidad de tarjetas por página
  const cardsPerPage = cardsPerRow;

  // Número total de páginas
  const totalPages = Math.ceil(recommends.length / cardsPerPage);
  const [currentPage, setCurrentPage] = useState(1); // Página actual

  // Calcula el índice inicial y final de los items a mostrar en la página actual
  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;

  // Obtén las tarjetas para la página actual
  const currentCards = recommends.slice(startIndex, endIndex);

  // Función para cambiar la página
  const handlePageChange = (event, page) => {
    setCurrentPage(page);
  };

// Función para ordenar aleatoriamente el array
  function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  return (
    <div className="recommends-container">
      <h2 className="home-titles">{title}</h2>
      {noResults && (
        <p style={{ fontSize: '1.5rem', textAlign: 'center', margin: '2rem', color: '#b94242', fontWeight:'bold' }}>
          No hay resultados disponibles en las fechas seleccionadas.
        </p>
      )}
      {!noResults && (
      <div className="render-cards-recommends">
      {currentCards.map((recommend) => (
        <Link key={recommend.id} to={"/Detail/" + recommend.id}>
          <CardRecomends
            key={recommend.id}
            type={recommend.type.title}
            name={recommend.name}
            image={recommend.images}
            capacity={recommend.capacity}
            rating={recommend.rating}
            ratingText={recommend.ratingText}
            city={recommend.city}
            address={recommend.address}
            detail={recommend.detail}
            basicPrice={recommend.basicPrice}
            characteristics={recommend.characteristics}
          />
        </Link>
      ))}
      </div>
      )}

      <Stack spacing={5} direction="row" justifyContent="center" mt={4}>
        <Pagination
          count={totalPages}
          page={currentPage}
          onChange={handlePageChange}
        />
      </Stack>
    </div>
  );
}; 