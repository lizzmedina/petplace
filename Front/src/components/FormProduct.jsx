import React, { useState, useEffect } from "react";
import { useContextGlobal } from '../components/utils/global.constext';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Swal from 'sweetalert2';

const FormProduct = () => {
    const { places, setPlaces } = useContextGlobal();

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    const [product, setProduct] = useState({
        name: "",
        categoryName: "",
        capacity: "",
        city: "",
        address: "",
        detail: "",
        images: [],
        characteristics: [],
        basicPrice: "",
    });

    const [isLoading, setIsLoading] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);

    const url = `${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare/all`;
    const [allProducts, setAllProducts] = useState([]);
    useEffect(() => {
        fetch(url)
            .then((res) => res.json())
            .then((data) => {
                setAllProducts(data);
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        const result = allProducts.find((item) => item.name === product.name);
        console.log(result);
        if (result === undefined) {
            // El nombre no existe en allProducts, proceder con el envío del formulario
            if (product.name.length > 2 && product.detail.length > 5) {
                setIsLoading(true);
                setProduct({
                    name: "",
                    categoryName: "",
                    capacity: "",
                    city: "",
                    address: "",
                    detail: "",
                    images: [],
                    characteristics: [],
                    basicPrice: "",
                });console.log(product);
                

                fetch(`${import.meta.env.VITE_REACT_APP_BACKEND_URL}/api/v1/petDayCare`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(product),

                })
                    .then((response) => {
                        if (response.ok) {
                            setIsSuccess(true);
                            Swal.fire({icon: 'success',title:`El alojamiento ${product.name} ha sido creado exitosamente.`});
                            setProduct({
                                name: "",
                                categoryName: "",
                                capacity: "",
                                city: "",
                                address: "",
                                detail: "",
                                images: [],
                                characteristics: [],
                                basicPrice: "",
                            });
                            const selectElement = document.querySelector("select[name='type']");
                            selectElement.selectedIndex = 0;
                        } else {
                            throw new Error("Error en la solicitud");
                        }
                    })
                    .catch((error) => {
                        console.error(error);
                        Swal.fire({icon: 'error',title:"Parece que algo va mal, verifica la información."});
                    })
                    .finally(() => {
                        setIsLoading(false);
                        setProduct((product) => ({ ...product, categoryName: "" }));
                    });
            } else {
                Swal.fire({icon: 'error',title:`Parece que algo salió mal, recuerda llenar los campos.`});
            }
        } else {
            Swal.fire({icon: 'warning',title:"Ese nombre ya está tomado, intenta con uno nuevo."});
        }
    };

    const handleAddImage = () => {
        setProduct((prevState) => ({...prevState,images: [...prevState.images, ""]}));
    };

    const handleImageChange = (index, value) => {
        const updatedImages = [...product.images];
        updatedImages[index] = value;
        setProduct((prevState) => ({
            ...prevState,
            images: updatedImages,
        }));
    };

    //Checkbox de servicios
    const [selectedCharacteristics, setSelectedCharacteristics] = useState([]);

    useEffect(() => {
        setProduct((prevProduct) => ({
          ...prevProduct,
          characteristics: selectedCharacteristics,
        }));
      }, [selectedCharacteristics]);


    const handleCheckboxChange = (option) => {
        if (selectedCharacteristics.includes(option)) {
            // La opción ya está seleccionada, hay que removerla del array
            setSelectedCharacteristics((prevSelected) =>
                prevSelected.filter((selected) => selected !== option)
            );
        } else {
            // La opción no está seleccionada, hay que agregarla al array
            setSelectedCharacteristics((prevSelected) => [...prevSelected, option]);
        }
    };21


    return (
        <div className="form-container">
            {userConnected.type === "Manager" && (
                <form className="form-section" onSubmit={handleSubmit}>
                    <div className="form-section-name">
                        <div className="box">
                            <label>Nombre: </label>
                            <br />
                            <input type="text" value={product.name} onChange={(e) => setProduct({ ...product, name: e.target.value })} />
                            <br />
                        </div>
                        <div className="box">
                            <label>Capacidad: </label>
                            <br />
                            <input type="text" value={product.capacity} onChange={(e) => setProduct({ ...product, capacity: e.target.value })} />
                            <br />
                        </div>
                    </div>
                    <br />


                    <div className="section-Image">
                        <label>Agregar Imagen: </label> <button type="button" onClick={handleAddImage} className="button-AddImage"> + </button>
                    </div>
                    {product.images.map((imageUrl, index) => (
                        <div>
                            <input type="text" value={imageUrl} onChange={(e) => handleImageChange(index, e.target.value)} 
                            className="input-Image" placeholder={"Imagen" + (index + 1)}/>
                            <br />
                        </div>
                    ))}


                    <br />
                    <label>Tipo de alojamiento: </label>
                    <select name="type" onChange={(e) => setProduct({ ...product, categoryName: e.target.options[e.target.selectedIndex].value })}>
                    <option value="" hidden>--- Elige una Opción ---</option>
                        {places.map((categoria) => (
                            <option key={categoria.title} value={categoria.title}> {categoria.title} </option>
                        ))}
                    </select>
                    <br />
                    <label>Ciudad: </label>
                    <input type="text" value={product.city} onChange={(e) => setProduct({ ...product, city: e.target.value })} />
                    <br />
                    <label>Direccion: </label>
                    <input type="text" value={product.address} onChange={(e) => setProduct({ ...product, address: e.target.value })} />
                    <br />
                    <label>Precio: </label>
                    <input type="text" value={product.basicPrice} onChange={(e) => setProduct({ ...product, basicPrice: e.target.value })}/>
                    <br />
                    <label>Descripción: </label>
                    <textarea name="message" type="text" rows="5" value={product.detail} onChange={(e) => setProduct({ ...product, detail: e.target.value })} />
                    <br />
                    <label>Servicios: </label>
                    <FormGroup aria-label="position" row>
                        <FormControlLabel
                            control={<Checkbox checked={selectedCharacteristics.includes("Alimentación")} onChange={() => handleCheckboxChange("Alimentación")}/>}
                            label="Alimentación"
                        />
                        <FormControlLabel
                            control={<Checkbox checked={selectedCharacteristics.includes("Baño")} onChange={() => handleCheckboxChange("Baño")}/>}
                            label="Baño"
                        />
                        <FormControlLabel
                            control={<Checkbox checked={selectedCharacteristics.includes("Entrenamiento")} onChange={() => handleCheckboxChange("Entrenamiento")}/>}
                            label="Entrenamiento"
                        />
                        <FormControlLabel
                            control={<Checkbox checked={selectedCharacteristics.includes("Paseo")} onChange={() => handleCheckboxChange("Paseo")}/>}
                            label="Paseo"
                        />
                        <FormControlLabel
                            control={<Checkbox checked={selectedCharacteristics.includes("Veterinaria")} onChange={() => handleCheckboxChange("Veterinaria")}/>}
                            label="Veterinaria" 
                        />
                    </FormGroup>

                    <br />
                    <div className="section-button">
                        <button className="button-1">Crear</button>
                    </div>
                </form>
            )}
        </div>
    );
};

export default FormProduct;
