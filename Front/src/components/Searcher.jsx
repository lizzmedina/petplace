import { useState } from "react";


export const Searcher = () => {

  const opciones = ['Santa Fé de Bogotá, Colombia', 'Buenos Aires, Argentina', 'Cartagena, Colombia'];
  const [opcionSeleccionada, setOpcionSeleccionada] = useState('');

  const handleSeleccionarOpcion = (event) => {
    setOpcionSeleccionada(event.target.value);
  };

  return (
    <div className="searcher-container">
      <img 
          src='./images/PpLogo.png'  
          alt="icono"
          className="searcherLogo"
        /> 
      <h2 className="searcher-title"> Busca el alojamiento ideal para tu mascota</h2>
      <div className="searcher">
        <div className="search-by-location">
          <label htmlFor="opciones"/>
          <select id="opciones" value={opcionSeleccionada} onChange={handleSeleccionarOpcion}>
            <option value="" disabled hidden>¿Donde cuaidarlo? </option>
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
  )
}
