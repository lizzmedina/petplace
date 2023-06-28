import { useNavigate, Link } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChevronLeft, faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot, faHeart } from '@fortawesome/free-solid-svg-icons'
import { faHeart as fasHeart } from '@fortawesome/free-regular-svg-icons'
import { Link as ScrollLink, Element } from 'react-scroll';
import React, { useState } from 'react';
import ImageModal from './ImageModal';
import { CalendarDetail } from './CalendarDetail';
import { Rating, Typography, Grid } from '@mui/material';
import { useContextGlobal } from './utils/global.constext';
import PageModalWindows from './PageModalWindows';

export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, features, houseRules, healthAndSecurity, cancellationPolicy, rating }) => {

    const navigate = useNavigate()
    const [isModalOpen, setModalOpen] = useState(false);
    const {isFavorite, setIsFavorite} = useContextGlobal();


    // map develop
    const generateLocationURL = () => {
        const concatenatedValue = `${city.name} ${address}`;
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

        return features.map((option, index) => {
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
    if (!image) {
        return image;
    }
    const handleOpenModal = () => {
        setModalOpen(true);
    };
    const handleCloseModal = () => {
        setModalOpen(false);
    };

    const handleFavorite = () => {
        setIsFavorite(!isFavorite);
    }
    const getRatingText = () => {
        if (rating == null)
            return '';

        return (rating.amountOfReviews > 1)
            ? `${rating.amountOfReviews} calificaciones`
            : '1 calificación'
    }


    return (

        <div key={id} className="product-container">
            <div className="product-header">

                <ScrollLink to="locationContainer" smooth={true} duration={500}>
                    <span className="pinLocation-product">
                        <FontAwesomeIcon
                            icon={faLocationDot}
                            className='icon-service'
                        />
                        {city.name}, {address}
                    </span>
                </ScrollLink>
                <div className='icons-product-container'>
                    <div className='favorite-product-container'>
                        {isFavorite
                            ?
                            (
                                <FontAwesomeIcon
                                    icon={faHeart} style={{ color: "#a278f0", }}
                                    className=''
                                    onClick={handleFavorite}
                                    size='xl'
                                />
                            )
                            :
                            (
                                <FontAwesomeIcon
                                    icon={fasHeart} style={{ color: "#a278f0", }}
                                    className=''
                                    onClick={handleFavorite}
                                    size='xl'
                                />
                            )
                        }
                    </div>
                    <PageModalWindows name={name} basicPrice={basicPrice} city={city.name} address={address} image={image[0]}>Compartir</PageModalWindows>
                    <a onClick={() => navigate(-1)}>
                        <FontAwesomeIcon
                            icon={faChevronLeft}
                            className='back-icon'
                        />
                    </a>
                </div>
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

                <div className="product-info-rigth">
                    {rating !== null ?
                        <Grid container spacing={3} direction="row">
                            <Grid item>
                                <Typography component="legend"><p className="text-info">Calificación:</p></Typography>
                            </Grid>
                            <Grid item>
                                <p className="text-info">
                                    <Rating readOnly value={rating.average} precision={0.5} />
                                </p>
                            </Grid>
                            <Grid item>
                                <p className="text-info">
                                    {rating.average} / 5.0
                                </p>
                            </Grid>
                            <Grid item className="no-top-padding">
                                <p>
                                    {getRatingText()}
                                </p>
                            </Grid>
                        </Grid> : null
                    }
                    <Grid container spacing={2}>
                        <Grid item>
                            <p className="text-info">Capacidad:</p>
                        </Grid>
                        <Grid item>
                            <p className="text-info">{capacity} {type.title}</p>
                        </Grid>
                    </Grid>

                    <Grid container spacing={2}>
                        <Grid item>
                            <p className="text-info">Precio:</p>
                        </Grid>
                        <Grid item>
                            <p className="text-info">$ {basicPrice}</p>
                        </Grid>
                    </Grid>

                </div>

            </div>
            <div className='reserveDetail'>
                <CalendarDetail productId={id} />
                <div className='buttonDetailContent'>
                <button className="buttonDetail button-1"><Link to={`/bookingRegister?idProduct=${id}`} style={{ color: 'inherit' }}>Iniciar reserva</Link></button>
                </div>
                
            </div>

            <Element name="locationContainer">
                <div className='location-container' id='locationContainer'>
                    <h3>Ubicación</h3>
                    <div className="mapouter">
                        <div className="gmap_canvas">
                            <iframe className='mapFrame' id="gmap_canvas"
                                src={generateLocationURL()} />
                        </div>
                    </div>
                </div>
            </Element>

            <div className='rulesContainer'>
                <div className='rulesProduct'>
                    <h3>Normas de la casa</h3>
                    <ul>{renderRules(houseRules)}</ul>
                </div>
                <div className='rulesProduct'>
                    <h3>Salud y seguridad</h3>
                    <ul>{renderRules(healthAndSecurity)}</ul>
                </div>
                <div className='rulesProduct'>
                    <h3>Política de cancelación</h3>
                    <ul>{renderRules(cancellationPolicy)}</ul>
                </div>
            </div>

            {isModalOpen && (<ImageModal images={image} onClose={handleCloseModal} />)}
        </div>
    )

}