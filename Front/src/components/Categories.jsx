import { useState, useEffect } from "react";
import { Card } from "./CardCategories";
import Stack from '@mui/material/Stack';
import { Pagination, useMediaQuery } from "@mui/material";
import { useContextGlobal } from "./utils/global.constext";
import { Link } from 'react-router-dom';

export const Categories = () => {
  const { places } = useContextGlobal();
  const [isLoading, setIsLoading] = useState(true);

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

  useEffect(() => {
    setIsLoading(false);
  }, []);

  return (
    <div className="categories-container">
      <h2 className="home-titles">Alojamientos por tipo de mascota</h2>
      {isLoading ? (
        <p style={{ fontSize: '1.5rem', textAlign: 'center', margin: '2rem' }}>
          Cargando...
        </p>
      ) : (
        <div className="render-cards-categories">
          {currentCards.map((place) => (
            <Link to={"/category/" + place.id} key={place.id}>
              <Card
                title={place.title}
                image={place.image}
                description={place.description}
                icon={place.icon}
              />
            </Link>
          ))}
        </div>
      )}
      {!isLoading && (
        <Stack spacing={5} direction="row" justifyContent="center" mt={4}>
          <Pagination
            count={totalPages}
            page={currentPage}
            onChange={handlePageChange}
          />
        </Stack>
      )}
    </div>
  );
};
