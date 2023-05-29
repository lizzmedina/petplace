import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import {CardRecomends} from "../components/CardRecomends";
import Stack from '@mui/material/Stack';
import { Pagination } from "@mui/material";

export const CategoryFiltred = () =>  {
  const { id} = useParams();

  const [category, setCategory] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    const getCategory = async () => {
      try {
        const res = await fetch(`http://localhost:8080/api/v1/petDayCare/category/${id}`);
        const data = await res.json();
        setCategory(data);
      } catch (error) {
        console.error("Error fetching category data:", error);
      }
    };

    getCategory();
  }, [id]);

  // Determina la cantidad de tarjetas a mostrar en función del tamaño de la pantalla
  let cardsPerRow =10;

  // Cantidad de tarjetas por página
  const cardsPerPage = cardsPerRow;

  // Número total de páginas
  const totalPages = Math.ceil(category.length / cardsPerPage);

  // Calcula el índice inicial y final de los items a mostrar en la página actual
  const startIndex = (currentPage - 1) * cardsPerPage;
  const endIndex = startIndex + cardsPerPage;

  // Obtén las tarjetas para la página actual
  const currentCards = category.slice(startIndex, endIndex);

  // Función para cambiar la página
  const handlePageChange = (event, page) => {
    setCurrentPage(page);
  };

  return (
    <div className='space-section'>
      <h2>Categoría: {id}</h2>
      <p>A continuación nuestras opciones de hospedaje en esta categoría</p>

      <div className="render-cards-recommends">
        {currentCards.map((recommend) => (
          <Link key={recommend.id} to={"/Detail/" + recommend.id}>
            <CardRecomends
              key={recommend.id}
              type={recommend.type.name}
              name={recommend.name}
              image={recommend.images}
              capacity={recommend.capacity}
              rating={recommend.rating}
              ratingText={recommend.ratingText}
              characteristics={recommend.characteristics}
              city={recommend.city}
              address={recommend.address}
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
}

