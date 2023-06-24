import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { useNavigate, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChevronLeft, faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot } from '@fortawesome/free-solid-svg-icons'
import { ReservationCalendar } from "./ReservationCalendar";
import { Link as ScrollLink, Element } from 'react-scroll';
import ImageModal from './ImageModal';
import { CalendarDetail } from "./CalendarDetail.jsx";
import { useContextGlobal } from './utils/global.constext';

function FormBooking() {

    const {selectedDates, setSelectedDates} = useContextGlobal();

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
    const startDate = new Date(selectedDates[0]);
    const endDate = new Date(selectedDates[1]);

    const timeDifference = endDate.getTime() - startDate.getTime();
    const numberofDays = Math.floor(timeDifference / (1000 * 3600 * 24));

    const totalPayment = product.basicPrice * numberofDays


    return (
        <div>
            <div className="booking-upcontainer">
                <div className="booking-upleftcontainer">
                    <div className="booking-upleftsection">
                        <h3>Información de Usuario</h3><br/>
                        <div className='form-infoLine'> <p>id:&nbsp;{user.id}</p> </div>
                        <div className='form-infoLine'> <p>Nombre(s):</p> <p>&nbsp;{user.name}</p></div>
                        <div className='form-infoLine'> <p>Apellido(s):</p> <p>&nbsp;{user.lastName}</p> </div>
                        <div className='form-infoLine'> <p>email:</p> <p>&nbsp;{user.email}</p> </div>
                        <div className='form-infoLine'> <p>Celular:</p> <p>&nbsp;{user.cellPhone}</p> </div>
                    </div>
                    <div className="booking-upleftsection">
                        <h3>Información de Reserva</h3><br/>
                        <div className="booking-calendar"><ReservationCalendar /></div>
                        <div className='form-infoLine'> <p>Precio base x dia(s):</p> <p>&nbsp;${product.basicPrice}</p> </div>
                        <div className='form-infoLine'> <p>Dia(s):</p> <p>&nbsp; {numberofDays}</p> </div>
                        <div className='form-infoLine'> <p>Total a Pagar:</p> <p>&nbsp; {totalPayment}</p> </div>
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
        </div>
    )
}

export default FormBooking