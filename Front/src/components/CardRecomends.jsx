export const CardRecomends = ({ image, category, name, rating, ratingText, servicio1, servicio2, iconoLocation, location, description, }) => {

    return (

        <div className="card-recomends">
            <div className="card-content">
                <div className="left-card-content">
                    <img
                        className="card-image-recommends"
                        src={image}
                        alt={category}
                    />
                    <a href=""><img className="card-like-recommends" src="./images/unlike.png" alt="" /></a>
                </div>
                <div className="right-card-content">
                    <h3 className="card-title-recommends">{name}</h3>
                    <p className="card-category-recommends">{category}</p>

                    <p className="card-location-recommends">{location}</p>

                    <p className="card-descrption-recommends">{description}</p>
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