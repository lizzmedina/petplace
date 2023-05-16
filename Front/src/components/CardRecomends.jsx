export const CardRecomends = ({ image, type, name, rating, ratingText, servicio1, servicio2, iconoLocation, city, address, detail, }) => {

    return (

        <div className="card-recomends">
            <div className="card-content">
                <div className="left-card-content">
                    <img
                        className="card-image-recommends"
                        src={image}
                        alt={type}
                    />
                    <a href=""><img className="card-like-recommends" src="./images/unlike.png" alt="" /></a>
                </div>
                <div className="right-card-content">
                    <h3 className="card-title-recommends">{name}</h3>
                    <p className="card-category-recommends">{type}</p>

                    <p className="card-location-recommends">{city}, {address}</p>

                    <p className="card-descrption-recommends">{detail}</p>
                    <a className="see-more-link" href="">...Leer más</a>
                    <span >
                        <p className="rating"> {rating} </p>
                        <p> {ratingText} </p>
                    </span>

                </div>
            </div>


        </div>
    )

}