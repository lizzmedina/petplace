import { useState,  useContext } from "react";
import { ReservationCalendar } from "./ReservationCalendar";
import { SearcherByLocation } from "./SearcherByLocation";
import  {SearchContext}  from "../components/utils/SearchContext";

export const Searcher = () => {
  const {
    selectedOption,handleSelectCity, dates,handleDateChange} = useContext(SearchContext);
  const [searchResults, setSearchResults] = useState([]);
  const [searchTitle, setSearchTitle] = useState("Recomendaciones");

  const handleSearch = () => {
    if (selectedOption) {
      const city = selectedOption.value;
      let searchUrl = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/booking/${city}`;

      if (dates.length === 2) {
        const startDate = dates[0];
        const endDate = dates[1];
        searchUrl += `?startDate=${startDate}&endDate=${endDate}`;
      }

      fetch(searchUrl)
        .then((res) => res.json())
        .then((data) => {
          setSearchResults(data);
          setSearchTitle("Resultados de búsqueda");
        })
        .catch((error) => {
          console.error("Error al realizar la búsqueda:", error);
        });
    }
  };

  return (
    <div className="searcher-container">
      <h2 className="searcher-title">Busca el alojamiento ideal para tu mascota</h2>
      <p>Busca de acuerdo a la ciudad en la que te encuentres y/o la fecha en la que lo necesitas</p>

      <div className="searcher">
        <SearcherByLocation />
        <ReservationCalendar />
        <button className="searcher-button search-width" onClick={handleSearch}>Buscar</button>
      </div>
    </div>
  );
};
