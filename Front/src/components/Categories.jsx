import { useState} from "react";
import { Card } from "./CardCategories";
import Stack from '@mui/material/Stack';
import { Pagination, useMediaQuery } from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";
import { Link } from 'react-router-dom';

export const Categories = () => {

  const {places} = useContextGlobal();

  const isMobile = useMediaQuery('(max-width: 767px)'); 
  const isTablet = useMediaQuery('(max-width: 1024px)'); 
  
  const deviceConfigurations = {
    mobile: {
      cardsPerRow: 1,
      cardsPerPage: 1
    },
    tablet: {
      cardsPerRow: 2,
      cardsPerPage: 2
    },
    desktop: {
      cardsPerRow: 4,
      cardsPerPage: 4
    }
  };
  const { cardsPerRow, cardsPerPage } = isMobile
    ? deviceConfigurations.mobile : isTablet
    ? deviceConfigurations.tablet : deviceConfigurations.desktop;

    const totalPages = Math.ceil(places.length / cardsPerPage);
    const [currentPage, setCurrentPage] = useState(1);
  
    const currentCards = places.slice(
      (currentPage - 1) * cardsPerRow,
      (currentPage - 1) * cardsPerRow + cardsPerRow
    );
  
    const handlePageChange = (event, page) => {
      setCurrentPage(page);
    };
  
  
    return (
      <div className="categories-container">
        <h2 className="home-titles">Buscar según el tipo de mascota</h2>
        <div className="render-cards-categories">
          {currentCards.map((place) => (
            <Link to={"/category/" + place.id} key={place.id}>
              <Card
                title={place.title}
                image={place.image}
                description={place.description}
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
