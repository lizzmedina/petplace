

export const Searcher = () => {
  return (
    <div className="searcher-container">
      <h2 className="searcher-title"> Busca el alojamiento ideal para tu mascota</h2>
      <div className="searcher">
        <input 
        className="searcher-input-location"
        type="text" 
        placeholder="ciudad, Pais"
      />
      <input 
        className="searcher-input-date"
        type="date"
        placeholder="CheckIn - CheckOut"
      />
    <button className="searcher-button">Buscar</button>
      </div>
      
    </div>
  )
}
