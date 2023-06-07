
import { ReservationCalendar } from "./ReservationCalendar"
import { SearcherByLocation } from "./SearcherByLocation"


export const Searcher = () => {

  
  return (
    <div className="searcher-container">
      
      <h2 className="searcher-title"> Busca el alojamiento ideal para tu mascota</h2>
      <p>Busca de acuerdo a la ciudad en la que te encuentres y/o la fecha en la que lo necesitas</p>
      
      <div className="searcher">
        <SearcherByLocation/>
        <ReservationCalendar/>
        <button className="searcher-button search-width">Buscar</button>
      </div>
    </div>
  )
}
