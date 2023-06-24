import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShower, faPersonWalkingWithCane, faCarrot, faBaseball, faStethoscope, faLocationDot, faHeart } from '@fortawesome/free-solid-svg-icons';
import { Grid } from '@mui/material';

export const CardRecomends = ({ image, type, name, characteristics, city, address, detail, capacity, basicPrice, ratingValue}) => {

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

    const truncateDetail = (text) => {
        if (text.length > 100) {
          return text.substring(0, 100) + '...';
        }
        return text;
      };


    const showRating = () => {
        return ratingValue !== null && ratingValue !== undefined;
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
                    <Grid container spacing={1} direction="row" className="space-content">
                      <Grid item xs={6}>
                        <h3 className="card-title-recommends">{name}</h3>
                      </Grid>
                    { showRating() ? (<Grid item xs={4}>
                            <p className="rating-value"> {ratingValue} / 5.0</p>
                          </Grid>) : null
                    }
                    <Grid item xs={2}>
                        <FontAwesomeIcon icon={faHeart} className='card-favorite-icon'/>
                    </Grid>
                    </Grid>
                    <span className="card-category-recommends">Habilitado para: {capacity} {type.title} </span>
                    <p className="card-location-recommends"><FontAwesomeIcon icon={faLocationDot} className='card-location-icon' />{city.name}, {address}</p>
                    <span className='card-services-list-recommends'>{renderCharacteristics()}</span>
                    <p className="card-descrption-recommends">{truncateDetail(detail)}</p>
                    <button className='button-2'>Ver más</button>
                </div>
            </div>
        </div>
    )
}