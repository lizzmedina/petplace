import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChevronLeft, faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot } from '@fortawesome/free-solid-svg-icons'
import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import ImageModal from './ImageModal';
import { CalendarDetail } from "./CalendarDetail.jsx";



export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, features, houseRules, healthAndSecurity, cancellationPolicy }) => {

    const navigate = useNavigate()
    const [isModalOpen, setModalOpen] = useState(false);

    const generateLocationURL = () => {
        const concatenatedValue = `${city.name} ${address}`;
        const encodedValue = encodeURIComponent(concatenatedValue.replace(/ /g, '+'));
        return `https://maps.google.com/maps?q=${encodedValue}&t=&z=13&ie=UTF8&iwloc=&output=embed`;
        
      };
      

    if (!image) {
        return image;
    }

    const renderFeatures = () => {
        const icons = {
            Paseo: faPersonWalkingWithCane,
            Baño: faShower,
            Alimentación: faCarrot,
            Veterinaria: faStethoscope,
            Entrenamiento: faBaseball,
        };



        return features.map((option, index) => {
            const icon = icons[option];

            if (icon && (option === 'Paseo' || option === 'Baño' || option === 'Alimentación' || option === 'Veterinaria' || option === 'Entrenamiento')) {

                return (
                    <div key={index}>
                        <FontAwesomeIcon icon={icon} className='icon-service' />
                        {'  '}
                        {option}
                    </div>
                );
            }
        });
    };

    const handleOpenModal = () => {
        setModalOpen(true);
    };
    const handleCloseModal = () => {
        setModalOpen(false);
    };




    return (

        <div key={id} className="product-container">
            <div className="product-header">
                <span className="product-location"><FontAwesomeIcon icon={faLocationDot} className='icon-service' /> {city.name}, {address}</span>  <a onClick={() => navigate(-1)}><FontAwesomeIcon icon={faChevronLeft} className='back-icon' /></a>
            </div>
            <div className="product-galery">
                <div className='leading-image'>
                    <img src={image[0]} alt="Imagen principal" className="product-image" />
                </div>
                <div className='underling-images'>
                    <div className='two-image'>
                        <img src={image[1]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='three-image'>
                        <img src={image[2]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='four-image'>
                        <img src={image[3]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                    <div className='five-image'>
                        <img src={image[4]} alt="imagen scundaria 1" className="product-image" />
                    </div>
                </div>

            </div>
            <Link onClick={handleOpenModal}><p className='more-images'> Ver más</p></Link>


            <div className="product-info-container">

                <span className="product-info-left">
                    <h2>{name}</h2>
                    <p className="text-info">Aloja {type.title}</p>
                    <p className="detail-info">{detail}</p>
                    <div className='features'>
                        <h3>Servicios más populares</h3>
                        <div className='feature-list'>
                            {renderFeatures()}
                        </div>
                    </div>
                </span>

                <span className="product-info-rigth">
                    <p className="text-info"> Capacidad: {capacity} {type.title}</p>
                    <p className="text-info">Precio: $ {basicPrice}</p>
                    <button className="buttonDetail button-1">Reservar</button>
                </span>


            </div>

            <CalendarDetail />

            <div className='location-container'>
                <h3>Ubicación</h3>
                <div class="mapouter">
                    <div class="gmap_canvas">
                        <iframe width="100%" height="300px" id="gmap_canvas"
                        src={generateLocationURL()}
                        frameborder="0" scrolling="no" marginheight="0" marginwidth="0">
                        </iframe>
                    </div>
                </div>
            </div>

            {isModalOpen && (<ImageModal images={image} onClose={handleCloseModal} />)}

        </div>
    )

}