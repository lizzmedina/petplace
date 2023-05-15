

export const CardRecomends = ({ image, category, name, rating, ratingText, servicio1, servicio2, iconoLocation, location, description, }) => {
    return (

        <div className="card-recomends">
            <div className="card-content">
             <div className="left-card-content">
                <img
                    className="card-recommends-image"
                    src={image}
                    alt={category}
                />
             </div>
             <div className="right-card-content">  
                <h3 className="card-title-name">{name}</h3>
                <p className="card-title-category">{category}</p>
                <span>
                    <p className="rating"> {rating} </p>
                    <p> {ratingText} </p>
                </span>
                
                <p>{location}</p>
                <p>{description}</p>
                
                </div>
             </div>
             

        </div>
    )
}