import { ReservationCalendar } from "./ReservationCalendar"
import { SearcherByLocation } from "./SearcherByLocation"


export const Searcher = () => {

  
  return (
    <div className="searcher-container">
      
        <h2 className="searcher-title"> Busca el alojamiento ideal para tu mascota</h2>
      
    <div className="searchercontainer2">
        <div className="searcherLogo-container">
          <img 
            src='./images/PpLogo.png'  
            alt="icono"
            className="searcherLogo"
          />
      </div>
      <div className="searcher">
        <SearcherByLocation/>
        <ReservationCalendar/>
        <button className="searcher-button">Buscar</button>
      </div>
    </div>
      
    </div>
  )
}
