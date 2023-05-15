import React, { useState } from "react";

const FormProduct = () => {
    const [product, setProdcut] = useState({
      id: '',
      category: '',
      name: "",
      image: '',
      rating: '',
      ratingText: '',
      location: '',
      service1: '',
      service2: '',
      description: ''
   
    })

    const handleSubmit = (event) => {
        event.preventDefault()
        if (product.name.length > 2 && product.description.length> 20) {
            alert(`El alojamiento ${product.name} ha sido creado exitosamente.`)
            setProduct({
                id: '',
                category: '',
                name: "",
                image: '',
                rating: '',
                ratingText: '',
                location: '',
                service1: '',
                service2: '',
                description: ''
            })
        } else {
            alert('Parece que algo va mal, verifica la información.')
        }
    }

    const handleOnChange = () => {
        setIsChecked(!isChecked);
      };


    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <label>Nombre: </label>
                <input type="text" value={product.name} onChange={(e) => setProduct({...product, name: e.target.value})}/>
                <br/>
                <label>Tipo de alojamiento: </label>
                <select name="type" onChange={(e) => setProduct({...product, type: e.target.value})}>
                    <option selected hidden>- Elige una opción -</option>
                    <option value="farm">Finca</option>
                    <option value="house">Casa</option>
                    <option value="playgroup">Guarderia</option>
                </select>
                <br/>
                
                
                <label>Direccion: </label>
                <input type="text" value={product.location} onChange={(e) => setProduct({...product, location: e.target.value})}/>
                <br/>

                <label>Descripción: </label>
                <textarea name="message" type="text" rows="5" value={product.description} onChange={(e) => setProduct({...product, description: e.target.value})}/>
                <br/>

                <div className="services">
                <label>Selecciona los servicios que presta el alojamiento:</label><br/>
                <input type="checkbox" id="services" name="services" value="walk" />Paseo <br/> 
                <input type="checkbox" id="services" name="services" value="vet" />Veterinario
                </div>
                
                
                <br/>
                <button>Crear</button>
            </form>
        </div>
    );
};

export default FormProduct