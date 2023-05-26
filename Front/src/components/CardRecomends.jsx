export const CardRecomends = ({ image, type, name, rating, ratingText, servicio1, servicio2, 
    iconoLocation, city, address, detail, capacity, basicPrice, }) => {

      if (!image) {
        return image;
    }
    return (
        
        <div className="card-recomends">
            <div className="card-content">
                <div className="left-card-content">
                    <img
                        className="card-image-recommends"
                        src={image[0]}
                        alt={type}
                    />
                  
                    
                </div>
                <div className="right-card-content">
                    <h3 className="card-title-recommends">{name}</h3>
                    <p className="card-category-recommends">Habilitado para: {type}</p>
                    <p className="card-category-recommends">Capacidad: {capacity}</p>
                    <p className="card-location-recommends">{city}, {address}</p>
                    <p className="card-descrption-recommends">{detail}</p>
                    <a className="see-more-link" href="">...Leer más</a>
                    <span >
                        <p className="rating"> {rating} </p>
                        <p> {ratingText} </p>
                    </span>
                    <p>${basicPrice}</p>
                </div>
            </div>
        </div>
    )
}