import React, { useState, useEffect } from "react";

const FormProduct = () => {
    const [product, setProduct] = useState({
        name: '',
        type: '',
        capacity: '',
        city: '',
        address: '',
        detail: '',
        image: '',
        services: [],
        basicPrice: ''
    })

    const url = "http://localhost:8080/api/v1/petDayCare/all"
    const [allProducts, setAllProducts] = useState([])
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setAllProducts(data);
            })
    }, []);
    console.log(allProducts);
    

    const handleSubmit = (event) => {
        event.preventDefault();
        const result = allProducts.find((item) => item.name.toLowerCase() === product.name.toLowerCase());
        console.log(result);
        if (result === undefined) {
            if (product.name.length > 2 && product.detail.length > 5) {
                setProduct({
                    name: "",
                    type: "",
                    capacity: "",
                    city: "",
                    address: "",
                    detail: "",
                    image: "",
                    services: [],
                    basicPrice: "",
                });

                fetch("http://127.0.0.1:8080/api/v1/petDayCare", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(product),
                })
                    .then((response) => {
                        if (response.ok) {
                            alert(
                                `El alojamiento ${product.name} ha sido creado exitosamente.`
                            );
                            setProduct({
                                name: "",
                                type: "",
                                capacity: "",
                                city: "",
                                address: "",
                                detail: "",
                                image: "",
                                services: [],
                                basicPrice: "",
                            });
                        } else {
                            throw new Error("Error en la solicitud");
                        }
                    })
            } else {
                alert("Parece que algo salió mal, recuerda llenar los campos.");
            }
        } else {
            alert("Ese nombre ya está tomado, intenta con uno nuevo.");
        }
    };

    const handleOnChange = () => {
        setIsChecked(!isChecked);
    };

    return (
        <div className="form-container">
            <form className="form-section" onSubmit={handleSubmit}>
                <div className="form-section-name">
                    <div className="box">
                        <label>Nombre: </label><br/>
                        <input type="text" value={product.name} onChange={(e) => setProduct({...product, name: e.target.value})}/>
                        <br/>
                    </div>
                    <div className="box">
                        <label>Capacidad: </label><br/>
                        <input type="text" value={product.capacity} onChange={(e) => setProduct({...product, capacity: e.target.value})}/>
                        <br/>
                    </div>
                </div><br/>
                <div className="form-section-name">
                    <div className="box">
                        <label>Imagen: </label><br/>
                        <input type="text" value={product.image} onChange={(e) => setProduct({...product, image: e.target.value})}/>
                        <br/>
                    </div>
                    <div className="box">
                        <label>Precio: </label><br/>
                        <input type="text" value={product.basicPrice} onChange={(e) => setProduct({...product, basicPrice: e.target.value})}/>
                        <br/>
                    </div>
                </div><br/>
                <label>Tipo de alojamiento: </label>
                <select name="type" onChange={(e) => setProduct({...product, type: e.target.value})}>
                    <option selected hidden>--- Elige una opción ---</option>
                    <option value="Perros">Perros</option>
                    <option value="Gatos">Gatos</option>
                    <option value="Canarios">Canarios</option>
                    <option value="Conejos">Conejos</option>
                </select>
                <br/>
                
                <label>Ciudad: </label>
                <input type="text" value={product.city} onChange={(e) => setProduct({...product, city: e.target.value})}/>
                <br/>
                
                <label>Direccion: </label>
                <input type="text" value={product.address} onChange={(e) => setProduct({...product, address: e.target.value})}/>
                <br/>

                <label>Descripción: </label>
                <textarea name="message" type="text" rows="5" value={product.detail} onChange={(e) => setProduct({...product, detail: e.target.value})}/>
                <br/>

                <br/>
                <div className="section-button">
                    <button className="button-1">Crear</button>
                </div>
            </form>
        </div>
    );
};

export default FormProduct