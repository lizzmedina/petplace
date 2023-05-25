import { useState, useEffect } from "react";
import { CardRecomends } from "./CardRecomends";
import { Link } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import { Pagination, useMediaQuery } from "@mui/material";

export const Recommends = () => {
  const url = "http://localhost:8080/api/v1/petDayCare/all";

  const [recommends, setRecommends] = useState([]);
  useEffect(() => {
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setRecommends(data);
      })
      .catch((error) => {
        console.error("Error fetching recommends:", error);
      });
  }, []);

  const isMobile = useMediaQuery('(max-width: 767px)'); // Verifica si es un dispositivo móvil
  const isTablet = useMediaQuery('(max-width: 1024px)'); // Verifica si es una tablet

  // Determina la cantidad de tarjetas a mostrar en función del tamaño de la pantalla
  let cardsPerPage;
  if (isMobile) {
    cardsPerPage = 1;
  } else if (isTablet) {
    cardsPerPage = 2;
  } else {
    cardsPerPage = 4;
  }

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

  function shuffleArray(array) {
    // Función para ordenar aleatoriamente el array
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  return (
    <div className="recommends-container">
      <h2 className="home-titles">Recomendaciones</h2>
      <div className="render-cards-recommends">
        {currentCards.map((recommend) => (
          <Link key={recommend.id} to={"/Detail/" + recommend.id}>
            <CardRecomends
              key={recommend.id}
              type={recommend.type}
              name={recommend.name}
              image={recommend.image}
              capacity={recommend.capacity}
              rating={recommend.rating}
              ratingText={recommend.ratingText}
              iconoLocation={recommend.iconoLocation}
              city={recommend.city}
              address={recommend.address}
              service1={recommend.service1}
              service2={recommend.service2}
              detail={recommend.detail}
              basicPrice={recommend.basicPrice}
            />
          </Link>
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
  );
};
