import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot } from '@fortawesome/free-solid-svg-icons'



export const Product = ({ id, name, type, capacity, city, address, detail, image, basicPrice, characteristics }) => {

    const navigate = useNavigate()

    if (!image) {
        return image;
    }

    const renderCharacteristics = () => {
        const icons = {
            Paseo: faPersonWalkingWithCane,
            Baño: faShower,
            Alimentación: faCarrot,
            Veterinaria: faStethoscope,
            Entrenamiento: faBaseball,
        };

        return characteristics.map((option, index) => {
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






    return (

        <div key={id} className="product-container">
            <div className="product-header">
            <span className="product-location"><FontAwesomeIcon icon={faLocationDot} className='icon-service' /> {city}, {address}</span>  <a onClick={() => navigate(-1)}><img className='back-icon' src="..\images\backRecurso 2.png" alt="" /></a>
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
            <p className='more-images'> Ver más</p>


            <div className="product-info-container">

                <span className="product-info-left">
                    <h2>{name}</h2>
                    <p className="text-info">Aloja {type.title}</p>
                    <p className="detail-info">{detail}</p>
                    <div className='features'>
                        <h3>Servicios más populares</h3>
                        <div className='feature-list'>
                            {renderCharacteristics()}
                        </div>
                    </div>
                </span>

                <span className="product-info-rigth">
                    <p className="text-info"> Capacidad: {capacity} {type.title}</p>
                    <p className="text-info">Precio: $ {basicPrice}</p>
                    <button className="buttonDetail button-1">Reservar</button>
                </span>


            </div>
            

        </div>
    )

}