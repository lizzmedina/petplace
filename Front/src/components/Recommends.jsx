import { useState, useEffect } from "react";
import { CardRecomends } from "./CardRecomends";
import Stack from '@mui/material/Stack';
import { Pagination} from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";

export const Recommends = () => {
  
  const {recommends, setRecommends,  title, searchResults, setSearchResults} = useContextGlobal();
  const [noResults, setNoResults] = useState(false);
  const [search, setSearch] = useState(false);
  const [currentPage, setCurrentPage] = useState(1); 

  const [random, setRandom] = useState(false);


  let cardsPerRow = 10;
  const cardsPerPage = cardsPerRow;
  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;

  const totalPages = Math.ceil(recommends.length / cardsPerPage);
  
  const [currentCards, setCurrentCards] = useState([]);
  
  useEffect(() => {
    if(currentCards.length === 0)
      setCurrentCards(shuffleArray(recommends).slice(startIndex, endIndex));
    if (searchResults.length > 0) {
      setRecommends(searchResults);
      setNoResults(false);
      
    } else if(!search) {
      fetchRecommends();
      setSearch(true);
    }
  }, [searchResults, search, setSearch]);
  
 


  const fetchRecommends = () => {
    const url = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setRecommends(data);
        setSearchResults(data);
        setNoResults(data.length === 0);
        console.log(data);
      })
      .catch((error) => {
        console.error("Error al obtener las recomendaciones:", error);
      });
  };

  const shuffleArray = (array) => {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  
  

  

  const handlePageChange = (event, page) => {
    setCurrentPage(page);
  };



  return (
    <div className="recommends-container">
      <h2 className="home-titles">{title}</h2>
      {noResults && (
        <p style={{ fontSize: '1.5rem', textAlign: 'center', margin: '2rem', color: '#b94242', fontWeight:'bold' }}>
          No hay resultados disponibles.
        </p>
      )}
      {!noResults && (
      <div className="render-cards-recommends">
      {currentCards.map((recommend) => (
        // <Link key={recommend.id} to={"/Detail/" + recommend.id}>
          <CardRecomends
            key={recommend.id}
            number={recommend.id}
            type={recommend.type.title}
            name={recommend.name}
            image={recommend.images}
            capacity={recommend.capacity}
            rating={recommend.rating}
            city={recommend.city}
            address={recommend.address}
            detail={recommend.detail}
            basicPrice={recommend.basicPrice}
            characteristics={recommend.characteristics}
          />
        //</Link>
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