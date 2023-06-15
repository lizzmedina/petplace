import React, { useState, useEffect } from "react";
import { useContextGlobal } from '../components/utils/global.constext';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Swal from 'sweetalert2';

const FormProduct = () => {
    const { places, setPlaces, urlGetCities, urlGetProducts, urlPostProducts } = useContextGlobal();

    const userConnected = JSON.parse(localStorage.getItem('userConnected')) || null; //Para validad el tipo de usuario, si no esta logeado no cargara la pagina

    const [cities, setCities] = useState([]);
    useEffect(() => {
        fetch(urlGetCities)
            .then((res) => res.json())
            .then((data) => {
                setCities(data);
            });
    }, []);

    const [product, setProduct] = useState({
        name: "",
        type: { title: "" },
        capacity: "",
        city: { name: "" },
        address: "",
        detail: "",
        images: [],
        characteristics: [],
        basicPrice: "",
        houseRules: [],
        healthAndSecurity: [],
        cancellationPolicy: []
    });

    const [isLoading, setIsLoading] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);

    //const url = urlGetProducts;
    const [allProducts, setAllProducts] = useState([]);
    useEffect(() => {
        fetch(urlGetProducts)
            .then((res) => res.json())
            .then((data) => {
                setAllProducts(data);
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        const result = allProducts.find((item) => item.name === product.name);

        if (result === undefined) {
            if (product.name.length > 2 && product.detail.length > 5) {
                setIsLoading(true);
                setProduct({
                    name: "",
                    type: { title: "" },
                    capacity: "",
                    city: { name: "" },
                    address: "",
                    detail: "",
                    images: [],
                    characteristics: [],
                    basicPrice: "",
                    houseRules: [],
                    healthAndSecurity: [],
                    cancellationPolicy: []
                });
                console.log(product);

                fetch(urlPostProducts, {
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
                                type: { title: "" },
                                capacity: "",
                                city: { name: "" },
                                address: "",
                                detail: "",
                                images: [],
                                characteristics: [],
                                basicPrice: "",
                                houseRules: [],
                                healthAndSecurity: [],
                                cancellationPolicy: []
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
                        setProduct((product) => ({ ...product, type: "" }));
                    });
            } else {
                Swal.fire({icon: 'error',title:`Parece que algo salió mal, recuerda llenar los campos.`});
            }
        } else {
            Swal.fire({icon: 'warning',title:"Ese nombre ya está tomado, intenta con uno nuevo."});
        }
    };

    /* -- Lo siguiente es la logica para agregar Imagenes -- */
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

    /* -- Lo siguiente es la logica para agregar politicas de la casa -- */
    const handleAddHouseRules = () => {
        setProduct((prevState) => ({ ...prevState,houseRules: [...prevState.houseRules, ""]}));
    };
    const handleHouseRulesChange = (index, value) => {
        const updatedPolicy = [...product.houseRules];
        updatedPolicy[index] = value;
        setProduct((prevState) => ({
            ...prevState,
            houseRules: updatedPolicy,
        }));
    };

    /* -- Lo siguiente es la logica para agregar politicas de salud y seguridad -- */
    const handleAddHealthAndSecurity = () => {
        setProduct((prevState) => ({ ...prevState,healthAndSecurity: [...prevState.healthAndSecurity, ""]}));
    };
    const handleHealthAndSecurityChange = (index, value) => {
        const updatedPolicy = [...product.healthAndSecurity];
        updatedPolicy[index] = value;
        setProduct((prevState) => ({
            ...prevState,
            healthAndSecurity: updatedPolicy,
        }));
    };

    /* -- Lo siguiente es la logica para agregar politicas de cancelacion -- */
    const handleAddCancellationPolicy = () => {
        setProduct((prevState) => ({ ...prevState,cancellationPolicy: [...prevState.cancellationPolicy, ""]}));
    };
    const handleHouseCancellationPolicy = (index, value) => {
        const updatedPolicy = [...product.cancellationPolicy];
        updatedPolicy[index] = value;
        setProduct((prevState) => ({
            ...prevState,
            cancellationPolicy: updatedPolicy,
        }));
    };

    /* -- Lo siguiente es la logica para el checkbox de Servicios -- */
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

                    <div>
                        <label>Agregar Imagen: </label> <button type="button" onClick={handleAddImage} className="button-Add"> + </button>
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
                    <select name="type" onChange={(e) => setProduct({ ...product, type: { title: e.target.options[e.target.selectedIndex].value } })}>
                    <option value="" hidden>--- Elige una Opción ---</option>
                        {places.map((categoria) => (
                            <option key={categoria.title} value={categoria.title}> {categoria.title} </option>
                        ))}
                    </select>
                    <br />
                    <label>Ciudad: </label>
                    <select value={product.city.name} onChange={(e) => setProduct({ ...product, city: { name: e.target.value } })}>
                        <option value="" hidden>--- Elige una Ciudad ---</option>
                        {cities.map((city) => (
                            <option key={city.id} value={city.name}>{city.name}</option>
                        ))}
                    </select>
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
                    <br/>

                    <div>
                        <label>Agregar Regla de Casa: </label> <button type="button" onClick={handleAddHouseRules} className="button-Add"> + </button>
                    </div>
                    {product.houseRules.map((policyText, index) => (
                        <div>
                            <input type="text" value={policyText} onChange={(e) => handleHouseRulesChange(index, e.target.value)} 
                            className="input-Image" placeholder={"Regla de Casa " + (index + 1)}/>
                            <br />
                        </div>
                    ))}
                    <br />
                    <div>
                        <label>Agregar Politica de Salud y Seguridad: </label> <button type="button" onClick={handleAddHealthAndSecurity} className="button-Add"> + </button>
                    </div>
                    {product.healthAndSecurity.map((policyText, index) => (
                        <div>
                            <input type="text" value={policyText} onChange={(e) => handleHealthAndSecurityChange(index, e.target.value)} 
                            className="input-Image" placeholder={"Politica Salud&Seguridad " + (index + 1)}/>
                            <br />
                        </div>
                    ))}
                    <br />
                    <div>
                        <label>Agregar Politica de Cancelacion: </label> <button type="button" onClick={handleAddCancellationPolicy} className="button-Add"> + </button>
                    </div>
                    {product.cancellationPolicy.map((policyText, index) => (
                        <div>
                            <input type="text" value={policyText} onChange={(e) => handleHouseCancellationPolicy(index, e.target.value)} 
                            className="input-Image" placeholder={"Politica de Cancelacion " + (index + 1)}/>
                            <br />
                        </div>
                    ))}
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
