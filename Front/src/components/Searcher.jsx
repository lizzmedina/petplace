
import { ReservationCalendar } from "./ReservationCalendar";
import { SearcherByLocation } from "./SearcherByLocation";
import { useContextGlobal } from "./utils/global.constext";

export const Searcher = () => {
  const { selectedCity, selectedDates, setRecommends, setTitle } =
    useContextGlobal();

  const handleSearch = async () => {
    if (selectedCity) {
      let urlSearch = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/search/${selectedCity.value}`;

      if (selectedDates[0] && selectedDates[1]) {
        const startDate = selectedDates[0];
        const endDate = selectedDates[1];
        urlSearch += `?checkInCheckOut=${startDate},${endDate}`;
      }

      try {
        const response = await fetch(urlSearch);
        const data = await response.json();
        // Actualizar el estado de las recomendaciones con los resultados de búsqueda
        setRecommends(data);
        setTitle("Resultados de búsqueda");
      } catch (error) {
        console.error("Error al buscar:", error);
      }
    }
  };

  return (
    <div className="searcher-container">
      <h2 className="searcher-title">Busca el alojamiento ideal para tu mascota</h2>
      <p>Busca de acuerdo a la ciudad en la que te encuentres y/o la fecha en la que lo necesitas</p>

      <div className="searcher">
        <SearcherByLocation />
        <ReservationCalendar />
        <button className="searcher-button search-width" onClick={handleSearch}>
          Buscar
        </button>
      </div>
    </div>
  );
};