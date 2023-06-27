export const Card = ({ image, title, description, icon }) => {



    return (
        <div className="card-container" >
            <figure>
                <img
                    className="card-image"
                    src={image}
                    alt={title}
                />
                <div className="layer">
                <h3 className="card-title-category">{title}</h3>
                <img className="card-icon-category" src={icon} alt="" />
                <p className="card-quantity-categories">{description}</p>
                </div>
            </figure>
        </div>
    )
}