import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot } from '@fortawesome/free-solid-svg-icons';

export const CardRecomends = ({ image, type, name, rating, ratingText, characteristics, city, address, detail, capacity, basicPrice, }) => {

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
                    <span key={index}>
                        <FontAwesomeIcon icon={icon} className='card-services-recommends' />
                    </span>
                );
            }
        });
    };
    return (
        
        <div className="card-recomends">
            <div className="card-content">
                <div className="left-card-content">
                    <img
                        className="card-image-recommends"
                        src={image[0]}
                        alt={type.title}
                    />
                </div>
                <div className="right-card-content">
                    <h3 className="card-title-recommends">{name}</h3>
                    <span className="card-category-recommends">Habilitado para: {capacity} {type.title} </span>
                    <p className="card-location-recommends"><FontAwesomeIcon icon={faLocationDot} className='card-location-icon' />{city}, {address}</p>
                    <span className='card-services-list-recommends'>{renderCharacteristics()}</span>
                    <p className="card-descrption-recommends">{detail}</p>
                    <span>
                        <p className="rating"> {rating} </p>
                        <p> {ratingText} </p>
                    </span>
                    <button className='button-2'>Ver más</button>
                </div>
            </div>
        </div>
    )
}