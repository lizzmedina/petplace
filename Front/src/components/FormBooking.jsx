import React, { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import { useNavigate, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChevronLeft, faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot } from '@fortawesome/free-solid-svg-icons'
import { ReservationCalendar } from "./ReservationCalendar";
import ImageModal from './ImageModal';
import { useContextGlobal } from './utils/global.constext';
import dayjs from 'dayjs';
import { FaPaypal, FaRegCreditCard,FaMoneyBillAlt } from 'react-icons/fa';
import Swal from 'sweetalert2';
import * as yup from "yup";


function FormBooking() {

    const {selectedDates, setSelectedDates, urlPostBooking, urlEmailBooking} = useContextGlobal();

    const user = JSON.parse(localStorage.getItem("userConnected"));
    const product = JSON.parse(sessionStorage.getItem("productDetail"));

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const idProduct = searchParams.get('idProduct');

    const navigate = useNavigate()
    const [isModalOpen, setModalOpen] = useState(false);

    // map develop
    const generateLocationURL = () => {
        const concatenatedValue = `${product.city.name} ${product.address}`;
        const encodedValue = encodeURIComponent(concatenatedValue);
        return `https://maps.google.com/maps?q=${encodedValue}&t=&z=13&ie=UTF8&iwloc=&output=embed`;
    };

    // rules develop
    const renderRules = (rules) => {
        if (rules && rules.length > 0) {
            return rules.map((rule, index) => (
                <li key={index}>{rule}</li>
            ));
        } else {
            return <p>No hay reglas disponibles.</p>;
        }
    };

    // Features develop  
    const renderFeatures = () => {
        const icons = {
            Paseo: faPersonWalkingWithCane,
            Baño: faShower,
            Alimentación: faCarrot,
            Veterinaria: faStethoscope,
            Entrenamiento: faBaseball,
        };
        return product.characteristics.map((option, index) => {
            const icon = icons[option];
            if (icon && (option === 'Paseo' || option === 'Baño' || option === 'Alimentación' || option === 'Veterinaria' || option === 'Entrenamiento')) {
                return (
                    <div className='featureItem' key={index}>
                        <FontAwesomeIcon icon={icon} className='icon-service' />
                        {'  '}
                        {option}
                    </div>
                );
            }
        });
    };

    // Gallery modal develop
    if (!product.images) {
        return product.images;
    }
    const handleOpenModal = () => {
        setModalOpen(true);
    };
    const handleCloseModal = () => {
        setModalOpen(false);
    };

    //Calculo del numero de dias
    const localStartDate = localStorage.getItem('localStartDate');
    const localEndDate = localStorage.getItem('localEndDate');
    let timeDifference = 0
    let numberOfDays = 0
    let totalPayment = 0;

    if (localStartDate && localEndDate) {
        const startDate = dayjs(localStartDate).toDate();
        const endDate = dayjs(localEndDate).toDate();

        timeDifference = endDate.getTime() - startDate.getTime();
        numberOfDays = Math.floor(timeDifference / (1000 * 3600 * 24));

        totalPayment = product.basicPrice * numberOfDays;
    }

    //Informacion de la mascota
    const [pet, setPet] = useState(() => [null, null, null, null, null]);

    //Schema generado para validar
    const validationSchema = yup.object().shape({
        // paymentMethod: yup.string().required('Debe seleccionar un método de pago'),
        // localStartDate: yup.string().required('La fecha de inicio es requerida'),
        // localEndDate: yup.string().required('La fecha de fin es requerida'),
    });


    //Generacion de la Reserva
    const [booking, setBooking] = useState({
        userId: user.id,
        petDayCareId: product.id,
        checkInDate: localStorage.getItem('localStartDate'),
        checkOutDate: localStorage.getItem('localEndDate')
    });
    useEffect(() => {
        const newBooking = {
            ...booking,
            checkInDate: localStorage.getItem('localStartDate'),
            checkOutDate: localStorage.getItem('localEndDate'),
            dataPet: pet
        };
        setBooking(newBooking);
    }, [selectedDates, pet]);

    const [isSuccess, setIsSuccess] = useState(false);
    const handleSubmit = () => {
        validationSchema
            .validate(booking, { abortEarly: false })
            .then(() => {
                fetch(urlPostBooking, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(booking),
                })
                    .then((response) => {
                        if (response.ok) {
                            setIsSuccess(true);
                            Swal.fire({ icon: 'success', title: 'La reserva ha sido creada exitosamente.' });

                            return response.json(); // Devuelve la respuesta en formato JSON
                        } else {
                            return response.json().then((data) => {
                                throw new Error(data.message);
                            });
                        }
                    })
                    .then((data) => {
                        console.log("El id es: " + data.idBooking); // Imprime el ID de la reserva
                        fetch(urlEmailBooking + user.email + "/idBooking/" + data.idBooking, {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            //body: JSON.stringify(booking),
                        })

                    })
                    .catch((error) => {
                        Swal.fire({ icon: 'error', title: 'Error', text: error.message });
                    });
            })
            .catch((validationErrors) => {
                // Handle validation errors here
                console.log(validationErrors)
                console.log(booking);
            });
    };
      




    return (
        <div className="booking-form">
            <div className="booking-upcontainer">
                <div className="booking-upleftcontainer">
                    <div className="booking-upleftsection1">
                        <h3>Información del Usuario</h3><br/>
                        <div className='form-infoLine'> <p style={{ fontWeight: 'bold' }}>id:</p> <p>&nbsp;{user.id}</p> </div>
                        <div className='form-infoLine'> <p style={{ fontWeight: 'bold' }}>Nombre(s):</p> <p>&nbsp;{user.name}</p></div>
                        <div className='form-infoLine'> <p style={{ fontWeight: 'bold' }}>Apellido(s):</p> <p>&nbsp;{user.lastName}</p> </div>
                        <div className='form-infoLine'> <p style={{ fontWeight: 'bold' }}>email:</p> <p>&nbsp;{user.email}</p> </div>
                        <div className='form-infoLine'> <p style={{ fontWeight: 'bold' }}>Celular:</p> <p>&nbsp;{user.cellPhone}</p> </div><br/><br/>
                    </div>

                    <div className="booking-upleftsection2">
                        <h3>Información de Reserva</h3><br />
                        <div className="booking-calendar"><ReservationCalendar /></div><br/>
                        <div className='form-infoLine'> <p>Precio base x dia:</p> <p>&nbsp;${product.basicPrice}</p> </div>
                        <div className='form-infoLine'> <p>Dia(s):</p> <p>&nbsp; {numberOfDays}</p> </div>
                        <div className="text-total"> <p>Total a Pagar:</p> <p>&nbsp;{totalPayment}</p> </div>
                        <div className="text-info"> <p>Metodo de pago:</p> </div>
                        <form>
                            <input type="radio" id="paypal" name="pago" value="paypal" />
                            <label htmlFor="paypal"><FaPaypal className='icon-service'/>Paypal</label>
                            <br />
                            <input type="radio" id="efectivo" name="pago" value="efectivo" />
                            <label htmlFor="efectivo"><FaMoneyBillAlt className='icon-service'/>Efectivo</label>
                            <br />
                            <input type="radio" id="tarjeta" name="pago" value="tarjeta" />
                            <label htmlFor="tarjeta"><FaRegCreditCard className='icon-service'/>Tarjeta crédito</label>
                            <br />
                        </form>
                    </div>
                </div>

                <div className="booking-uprigthcontainer">
                    <h3>Información del Producto</h3><br/>
                    <div className="product-galery">
                        <div className='leading-image'>
                            <img src={product.images[0]} alt="Imagen principal" className="product-image" />
                        </div>
                        <div className='underling-images'>
                            <div className='two-image'>
                                <img src={product.images[1]} alt="imagen scundaria 1" className="product-image" />
                            </div>
                            <div className='three-image'>
                                <img src={product.images[2]} alt="imagen scundaria 1" className="product-image" />
                            </div>
                            <div className='four-image'>
                                <img src={product.images[3]} alt="imagen scundaria 1" className="product-image" />
                            </div>
                            <div className='five-image'>
                                <img src={product.images[4]} alt="imagen scundaria 1" className="product-image" />
                            </div>
                        </div>
                    </div>
                    <Link onClick={handleOpenModal}><p className='more-images'> Ver más</p></Link>
                    {isModalOpen && (<ImageModal images={product.images} onClose={handleCloseModal} />)}

                    <p className="text-info">{product.name} (Aloja {product.type.title})</p>
                    <p className="detail-info">{product.detail}</p>

                    <p className="text-info">Servicios más populares</p>
                    <div className='feature-list'> {renderFeatures()} </div>

                    <div className="booking-ubication">
                        <div className="booking-address">
                            <p className="text-info">Ubicación</p>
                            <p className="detail-info">{product.address}</p>
                            <p className="detail-info">{product.city.name}</p>
                        </div>

                        <div className="booking-map">
                            <div className="gmap_canvas">
                                <iframe className='mapFrame' id="gmap_canvas"
                                    src={generateLocationURL()} />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="booking-downcontainer">
                <div className='booking-pet'>
                    <h3>Información de la Mascota</h3><br />
                    <div className='booking-petInfo'>
                        <div>
                            <p className="text-info">Nombre:</p>
                            <input type="petName" onChange={(e) => setPet([e.target.value, ...pet.slice(1)])} />
                        </div>
                        <div>
                            <p className="text-info">Raza:</p>
                            <input type="petType" onChange={(e) => setPet([pet[0], e.target.value, ...pet.slice(2)])} />
                        </div>
                        <div>
                            <p className="text-info">Tamaño:</p>
                            <select value={pet[2]} onChange={(e) => setPet([pet[0], pet[1], e.target.value, pet[3]])}>
                                <option value={null}>Seleccionar opción</option>
                                <option value="small">Pequeña</option>
                                <option value="medium">Mediana</option>
                                <option value="big">Grande</option>
                            </select>
                        </div>
                        <div>
                            <p className="text-info">Vacunas al día:</p>
                            <select value={pet[3]} onChange={(e) => setPet([pet[0], pet[1], pet[2], e.target.value, pet[4]])}>
                                <option value={null}>Seleccionar opción</option>
                                <option value="sí">Sí</option>
                                <option value="no">No</option>
                            </select>
                        </div>
                        <div>
                            <p className="text-info">Comentarios adicionales:</p>
                            <input type="petExtraInfo" onChange={(e) => setPet([pet[0], pet[1], pet[2], pet[3], e.target.value])} />
                        </div>
                    </div>
                </div>
            </div>

            {console.log(pet)}

            <div className="booking-downcontainer">
                <div className='rulesContainer'>
                    <div className='rulesProduct'>
                        <p className="text-info">Normas de la casa</p>
                        <ul>{renderRules(product.houseRules)}</ul>
                    </div>
                    <div className='rulesProduct'>
                        <p className="text-info">Salud y seguridad</p>
                        <ul>{renderRules(product.healthAndSecurity)}</ul>
                    </div>
                    <div className='rulesProduct'>
                        <p className="text-info">Política de cancelación</p>
                        <ul>{renderRules(product.cancellationPolicy)}</ul>
                    </div>
                </div>
            </div>

            <button onClick={handleSubmit} className="button-2">Enviar Reserva</button>
        </div>
    )
}

export default FormBooking