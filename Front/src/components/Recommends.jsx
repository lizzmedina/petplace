import { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import { Pagination} from '@mui/material';
import { useContextGlobal } from "./utils/global.constext";
import {CardRecomends} from './CardRecomends';

export const Recommends = () => {
  const url = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;

  const [recommends, setRecommends] = useState([]);
  const { searchResults, searchTitle } = useContextGlobal();
  
  useEffect(() => {
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        const shuffledData = shuffleArray(data); 
        setRecommends(shuffledData);
      })
      .catch((error) => {
        console.error("Error al obtener las recomendaciones:", error);
      });
  }, []);

  useEffect(() => {
    if (searchResults?.length > 0) {
      setRecommends(searchResults);
    } else {
      setRecommends([]);
    }
  }, [searchResults]);

  let cardsPerRow = 10;

  const cardsPerPage = cardsPerRow;

  const totalPages = Math.ceil(recommends.length / cardsPerPage);
  const [currentPage, setCurrentPage] = useState(1); 

  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;

  const currentCards = recommends.slice(startIndex, endIndex);

  const handlePageChange = (event, page) => {
    setCurrentPage(page);
  };

  function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }
  return (
    <div className="recommends-container">
      <h2 className="home-titles">{searchTitle}</h2>
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

