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
        <div className="search-by-location">
          <label htmlFor="opciones"/>
          <select id="opciones" value={opcionSeleccionada} onChange={handleSeleccionarOpcion}>
            <option value="" disabled hidden>¿dónde estarás? </option>
            {opciones.map((opcion) => (
              <option key={opcion} value={opcion}>
                {opcion}
              </option>
            ))}
          </select>
        </div>
      <input 
        className = "searcher-input-date"
        type = "text"
        placeholder = "CheckIn - CheckOut"
      />
    <button className="searcher-button">Buscar</button>
      </div>
    </div>
      
    </div>
  )
}
