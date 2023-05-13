
export const CardRecomends = ({ image, category, name, rating, ratingText, servicio1, servicio2, iconoLocation, location, description, }) => {
    return (
<<<<<<< Updated upstream
        <div className="card-recomends">
           
                <img
                    className="card-image"
                    src={image}
                    alt={category}
                />
                <h3 className="card-title-name">{name}</h3>
                <p className="card-title-category">{category}</p>
                <span>
                    <p className="rating"> {rating} </p>
                    <p> {ratingText} </p>
                </span>

                <img
                    className="icono-servicio1"
                    src={servicio1}
                    alt="icono de ubicación"
                />
                <img
                    className="icono-servicio2"
                    src={servicio2}
                    alt="icono de ubicación"
                />
                <img
                    className="icono-location"
                    src={iconoLocation}
                    alt="icono de ubicación"
                />
                <p>{location}</p>
                <p>{description}</p>
            

        </div>
    )
}