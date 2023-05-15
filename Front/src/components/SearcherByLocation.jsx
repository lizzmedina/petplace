import { useState } from "react";


export const SearcherByLocation = () => {
    const opciones = ['Santa Fé de Bogotá, Colombia', 'Buenos Aires, Argentina', 'Cartagena, Colombia'];
  const [opcionSeleccionada, setOpcionSeleccionada] = useState('');

  const handleSeleccionarOpcion = (event) => {
    setOpcionSeleccionada(event.target.value);
  };
    return (
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
    )
}
