import { ReservationCalendar } from "./ReservationCalendar"
import { SearcherByLocation } from "./SearcherByLocation"


export const Searcher = () => {

  
  return (
    <div className="searcher-container">
      <h2 className="searcher-title"> Busca el alojamiento ideal para tu mascota</h2>
      <div className="searcher">
        <SearcherByLocation/>
        <ReservationCalendar/>
        <button className="searcher-button">Buscar</button>
      </div>
    </div>
  )
}
